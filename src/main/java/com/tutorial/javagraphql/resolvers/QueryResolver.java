package com.tutorial.javagraphql.resolvers;

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
        List<String> list=new ArrayList<String>();
        list.add("kkdd");
        list.add("dadadad");
        var clientB = Client.builder().id(id).firstname("abc").lastname("jssa").middlename(list).build();
        var clientA= Client.builder().id(UUID.randomUUID()).firstname("def").lastname("sada").middlename(list).build();
        clientA.setClient(clientB);
        clientB.setClient(clientA);

       return BankAccount.builder().id(id).currency(Currency.USD).clientName(clientA).build();

    }
}
