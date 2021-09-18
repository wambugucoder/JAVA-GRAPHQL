package com.tutorial.javagraphql.resolvers.bank.subscription;


import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.publisher.BankAccountPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    private final BankAccountPublisher publisher;

    public Publisher<BankAccount> bankAccounts(){
        return publisher.getBankAccountPublisher();

    }
    public Publisher<BankAccount> bankAccount(UUID id, DataFetchingEnvironment environment){
        return publisher.getBankAccountPublisherFor(id);
    }

}
