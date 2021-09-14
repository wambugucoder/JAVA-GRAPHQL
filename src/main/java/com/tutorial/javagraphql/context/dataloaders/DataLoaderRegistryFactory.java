package com.tutorial.javagraphql.context.dataloaders;

import com.tutorial.javagraphql.resolvers.bank.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {
    private final BalanceService balanceService;
    public static  final String BALANCE_DATA_LOADER="BALANCE_DATA_LOADER";
    private static final Executor balanceToThreadPool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public DataLoaderRegistry create(String id){
        DataLoaderRegistry registry= new DataLoaderRegistry();
        registry.register(BALANCE_DATA_LOADER,createBalanceDataLoader(id));
        return registry;

    }

    private DataLoader<UUID, BigDecimal> createBalanceDataLoader(String id) {

        return DataLoader.newMappedDataLoader((Set<UUID> bankAccountsId) ->
                CompletableFuture.supplyAsync(()-> balanceService.getBalance(bankAccountsId,id),balanceToThreadPool));
    }
}
