package com.tutorial.javagraphql.resolvers.bank.mutation;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import com.tutorial.javagraphql.request.BankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    public BankAccount createBankAccount(BankAccountInput input){
        return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD).build();


    }
}
