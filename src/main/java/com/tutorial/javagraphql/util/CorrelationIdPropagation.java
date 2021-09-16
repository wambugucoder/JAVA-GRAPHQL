package com.tutorial.javagraphql.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import javax.validation.constraints.NotNull;
import java.util.concurrent.Executor;

import org.slf4j.MDC;

import static com.tutorial.javagraphql.instrumentation.RequestLoggingInstrumentation.CORRELATION_ID;

@RequiredArgsConstructor(staticName = "wrap")
public class CorrelationIdPropagation implements Executor {

    private final Executor delegate;

    @Override
    public void execute(@NotNull Runnable command) {
        var correlationId = MDC.get(CORRELATION_ID);
        delegate.execute(() -> {
            try {
                MDC.put(CORRELATION_ID, correlationId);
                command.run();
            } finally {
                MDC.remove(CORRELATION_ID);
            }
        });
    }

}
