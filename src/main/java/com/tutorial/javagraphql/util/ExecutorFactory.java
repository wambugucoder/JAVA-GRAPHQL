package com.tutorial.javagraphql.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.var;
//import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExecutorFactory {
/*
    public static Executor newExecutor() {
        var realExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var securityDelegatingExecutor = new DelegatingSecurityContextExecutorService(realExecutor);
        return CorrelationIdPropagationExecutor.wrap(securityDelegatingExecutor);
    }
*/
}
