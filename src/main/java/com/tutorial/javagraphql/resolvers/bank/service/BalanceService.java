package com.tutorial.javagraphql.resolvers.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BalanceService {
    public Map<UUID,BigDecimal> getBalance(Set<UUID> bankAccountsId, String id) {
        log.info("Getting Batch balance for account with id:{} and user with id:{}",bankAccountsId,id);
        Map<UUID, BigDecimal> balance = new HashMap<>();
        for (UUID eachId :bankAccountsId){
            balance.put(eachId, BigDecimal.valueOf(Math.random()));
        }

        return balance;

    }
}
