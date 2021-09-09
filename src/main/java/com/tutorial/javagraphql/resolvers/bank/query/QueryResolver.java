package com.tutorial.javagraphql.resolvers.bank.query;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Client;
import com.tutorial.javagraphql.model.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class QueryResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id){
        log.info("Account created with id:" +id);


       return BankAccount.builder().id(id).currency(Currency.USD).build();

    }
}
