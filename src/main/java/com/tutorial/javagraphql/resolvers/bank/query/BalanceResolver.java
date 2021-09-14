package com.tutorial.javagraphql.resolvers.bank.query;

import com.tutorial.javagraphql.context.dataloaders.DataLoaderRegistryFactory;
import com.tutorial.javagraphql.model.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Component
public class BalanceResolver implements GraphQLResolver<BankAccount>{

    public CompletableFuture<BigDecimal> balance(BankAccount bankAccount, DataFetchingEnvironment environment){
       log.info("Getting Balance for account with id {}",bankAccount.getId());
       DataLoader<UUID,BigDecimal> balanceLoader= environment.getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER);
       return balanceLoader.load(bankAccount.getId());

    }
}
