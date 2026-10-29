package com.rasp.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.sql.Statement;
import java.util.Map;
import java.util.UUID;

public class AgentBootstrap {

    public static void premain(String args, Instrumentation instrumentation) {
        install(instrumentation);
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        install(instrumentation);
    }

    private static void install(Instrumentation instrumentation) {
        System.out.println("[RASP] Starting Java Agent");

        new AgentBuilder.Default()
            .type(ElementMatchers.isSubTypeOf(Statement.class))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.visit(Advice.to(SqlStatementAdvice.class)
                    .on(ElementMatchers.named("execute")
                        .or(ElementMatchers.named("executeQuery"))
                        .or(ElementMatchers.named("executeUpdate")))))
            .installOn(instrumentation);

        new AgentBuilder.Default()
            .type(ElementMatchers.named("java.lang.Runtime"))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.visit(Advice.to(RuntimeAdvice.class)
                    .on(ElementMatchers.named("exec"))))
            .installOn(instrumentation);

        System.out.println("[RASP] Java Agent installed successfully");
    }

    public static class SqlStatementAdvice {
        @Advice.OnMethodEnter
        public static void onEnter(@Advice.AllArguments Object[] args,
                                   @Advice.This Object thiz,
                                   @Advice.Origin String origin) {
            EventFactory.emit(
                "JDBC.execute",
                "java.sql.Statement",
                "execute",
                Map.of("args", args)
            );
        }
    }

    public static class RuntimeAdvice {
        @Advice.OnMethodEnter
        public static void onEnter(@Advice.AllArguments Object[] args,
                                   @Advice.This Object thiz,
                                   @Advice.Origin String origin) {
            EventFactory.emit(
                "Process.exec",
                "java.lang.Runtime",
                "exec",
                Map.of("args", args)
            );
        }
    }

    public static class EventFactory {
        public static void emit(String hookPoint, String className, String methodName, Map<String, Object> payload) {
            String eventId = UUID.randomUUID().toString();
            System.out.println("[RASP] Event: " + eventId + " hookPoint=" + hookPoint + " class=" + className + " method=" + methodName + " payload=" + payload);
        }
    }
}
