package com.tutorial.javagraphql.resolvers.bank;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Client;
import graphql.GraphQLException;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public DataFetcherResult<Client> clientName(BankAccount bankAccount){
        log.info("Client created with an account:"+bankAccount.getId());
        List<String> list =new ArrayList<String>();
        list.add("Maina");
        //BUILD DATA AND ERRORS IF ANY
        return DataFetcherResult.<Client>newResult().data(
                Client.builder().id(UUID.randomUUID())
                        .firstname("Jos")
                        .lastname("Wambugu")
                        .middlename(list)
                        .build())
               // .error(new GenericGraphQLError("Could not get sub client id"))
                .build();


    }
}
