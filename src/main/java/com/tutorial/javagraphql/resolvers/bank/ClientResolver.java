package com.tutorial.javagraphql.resolvers.bank;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Client;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public Client clientName(BankAccount bankAccount){
        log.info("Client created with ban account:"+bankAccount.getId());
        throw new GraphQLException("Client Unavailable");
      //  List<String> list =new ArrayList<String>();
        //list.add("Maina");
        //return Client.builder().id(UUID.randomUUID()).firstname("Jos").lastname("Wambugu").middlename(list).build();

    }
}
