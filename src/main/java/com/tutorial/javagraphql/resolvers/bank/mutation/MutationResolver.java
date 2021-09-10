package com.tutorial.javagraphql.resolvers.bank.mutation;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import com.tutorial.javagraphql.request.BankAccountInput;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class MutationResolver implements GraphQLMutationResolver {
    public BankAccount createBankAccount(BankAccountInput input){
        return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD).build();


    }
    public UUID upload(DataFetchingEnvironment environment){
        DefaultGraphQLServletContext context= environment.getContext();
        context.getFileParts().forEach(part -> {
            log.info("Uploading:{},with size:{}",part.getSubmittedFileName(),part.getSize());
        });
        return UUID.randomUUID();
    }
}
