package com.tutorial.javagraphql.instrumentation;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    private final Clock clock;
   public static  String CORRELATION_ID="correlation_id";

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var startTime = Instant.now(clock);
        var executionId=parameters.getExecutionInput().getExecutionId().toString();
        MDC.put(CORRELATION_ID,executionId);
        log.info("query:{}, variables:{}",parameters.getQuery(),parameters.getVariables());
        return SimpleInstrumentationContext.whenCompleted((ExecutionResult,Throwable)->{
            if (Throwable == null) {
                log.info("Execution  completed successfully within {}", Duration.between(startTime,Instant.now(clock)));
            }
            else {
                log.info("Execution  encountered errors {}",Throwable.toString());
            }
            MDC.clear();
        });
    }
}
