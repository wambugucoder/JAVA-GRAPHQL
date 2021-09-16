package com.tutorial.javagraphql.listener;

import com.tutorial.javagraphql.util.CorrelationIdPropagation;
import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingListener implements GraphQLServletListener {
    private final Clock clock;
    private static final Executor balanceToThreadPool= CorrelationIdPropagation.wrap(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        var startTime = Instant.now(clock);
        log.info("GraphQl Request Received");
        return new RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
                //No OP
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                //No OP
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                log.info("Time Taken to complete request={}", Duration.between(startTime,Instant.now(clock)));
            }
        };
    }
}
