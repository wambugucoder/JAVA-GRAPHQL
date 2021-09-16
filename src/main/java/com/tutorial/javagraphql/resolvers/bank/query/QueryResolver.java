package com.tutorial.javagraphql.resolvers.bank.query;

import com.tutorial.javagraphql.connection.CursorUtil;
import com.tutorial.javagraphql.context.CustomContext;
import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import com.tutorial.javagraphql.resolvers.bank.BankAccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final BankAccountRepository bankAccountRepository;
    private final CursorUtil cursorUtil;
    private final Clock clock;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BankAccount bankAccount(UUID id, DataFetchingEnvironment environment){

      if(environment.getSelectionSet().contains("currency")){
          log.info("Field contains Currency ");
      }

        log.info("Account created with id:" +id);

        CustomContext context= environment.getContext();
        log.info("User id is {}",context.getUserId());



       return BankAccount.builder().id(id).currency(Currency.USD).createdOn(LocalDate.now(clock)).createdAt(ZonedDateTime.now(clock)).build();

    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Connection<BankAccount> getAllBankAccounts(int first, @Nullable String cursor){
        int actualSize=bankAccountRepository.getBankAccounts().size();
        int size= Math.min(first, actualSize);
      List<Edge<BankAccount>> edges =  getAllAccounts(cursor)
                .stream()
                .map(bankAccount -> new DefaultEdge<>(bankAccount, cursorUtil.from(bankAccount.getId())))
              .limit(size)
              .collect(Collectors.toList());
        var pageInfo = new DefaultPageInfo(
                cursorUtil.getFirstCursorFrom(edges),
                cursorUtil.getLastCursorFrom(edges),
                cursor != null,
                edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);


    }
    public List<BankAccount> getAllAccounts(String cursor){
        if(cursor==null) {
            return bankAccountRepository.getBankAccounts();
        }
        return bankAccountRepository.getBankAccountsAfter(cursorUtil.decode(cursor));

    }
}
