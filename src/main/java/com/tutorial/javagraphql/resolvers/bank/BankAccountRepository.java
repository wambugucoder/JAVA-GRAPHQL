package com.tutorial.javagraphql.resolvers.bank;

import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static java.util.UUID.fromString;
import java.util.Comparator;
import java.util.UUID;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class BankAccountRepository {

    private final List<BankAccount> bankAccounts = new ArrayList<>();
    public List<BankAccount> listBankAccounts(){
        bankAccounts.add(BankAccount.builder()
                .id(fromString("c6aa269a-812b-49d5-b178-a739a1ed74cc"))
                .currency(Currency.USD)
                .createdAt(ZonedDateTime.parse("2019-05-03T12:12:00+00:00"))
                .build());
        bankAccounts.add(BankAccount.builder()
                .id(fromString("c6aa269a-812b-49d5-b178-a739a1ed74cd"))
                .currency(Currency.USD)
                .createdAt(ZonedDateTime.parse("2019-05-03T12:12:00+00:00"))
                .build());
        bankAccounts.add( BankAccount.builder()
                .id(fromString("410f5919-e50b-4790-aae3-65d2d4b21c77"))
                .currency(Currency.USD)
                .createdAt(ZonedDateTime.parse("2020-12-03T10:15:30+00:00"))
                .build());
        bankAccounts.add( BankAccount.builder()
                .id(fromString("024bb503-5c0f-4d60-aa44-db19d87042f4"))
                .currency(Currency.KSH)
                .createdAt(ZonedDateTime.parse("2020-12-03T10:15:31+00:00"))
                .build());
        bankAccounts.add(BankAccount.builder()
                        .id(fromString("48e4a484-af2c-4366-8cd4-25330597473f"))
                        .currency(Currency.USD)
                        .createdAt(ZonedDateTime.parse("2007-08-07T19:01:22+04:00"))
                        .build());
        return bankAccounts.stream()
                .sorted(Comparator.comparing(BankAccount::getId))
                .collect(Collectors.toList());
    }


    public List<BankAccount> getBankAccounts() {
        listBankAccounts();
        return bankAccounts;
    }

    public List<BankAccount> getBankAccountsAfter(UUID id) {
        listBankAccounts();
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getId().compareTo(id) != 1)
                .collect(Collectors.toList());
    }
}
