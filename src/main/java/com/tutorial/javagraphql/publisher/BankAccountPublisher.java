package com.tutorial.javagraphql.publisher;

import com.tutorial.javagraphql.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountPublisher {
    private final Sinks.Many<BankAccount> sink;

    public BankAccountPublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }


    public void publish(BankAccount bankAccount) {
      sink.tryEmitNext(bankAccount);

    }
    public Publisher<BankAccount> getBankAccountPublisher() {
        return  sink.asFlux().map(bankAccount->{
            log.info("Publishing ...");
            return bankAccount;
        });
    }

    public Publisher<BankAccount> getBankAccountPublisherFor(UUID id) {
        return  sink.asFlux()
                .filter(bankAccount -> id.equals(bankAccount.getId()))
                .map(bankAccount->{
            log.info("Publishing ...");
            return bankAccount;
        });
    }


}
