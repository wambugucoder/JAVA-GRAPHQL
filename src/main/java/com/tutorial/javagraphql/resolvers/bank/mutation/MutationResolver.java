package com.tutorial.javagraphql.resolvers.bank.mutation;

import com.tutorial.javagraphql.exceptions.CustomException;
import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import com.tutorial.javagraphql.publisher.BankAccountPublisher;
import com.tutorial.javagraphql.request.BankAccountInput;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Validated
public class MutationResolver implements GraphQLMutationResolver {
    private final Clock clock;
    private final BankAccountPublisher publisher;

    public BankAccount createBankAccount(@Valid BankAccountInput input){
        BankAccount bankAccount= BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD)
                .createdAt(ZonedDateTime.now(clock))
                .createdOn(LocalDate.now(clock))
                .build();
        publisher.publish(bankAccount);
        return bankAccount;

    }
    public BankAccount updateBank(UUID id,String name,int age){
        log.info("Updating Bank with ID {}, name {} and Age {}",id,name,age);
        return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD)
                .createdAt(ZonedDateTime.now(clock))
                .createdOn(LocalDate.now(clock))
                .build();


    }
    public UUID upload(DataFetchingEnvironment environment){
        DefaultGraphQLServletContext context= environment.getContext();
        context.getFileParts().forEach(part -> {
            log.info("Uploading:{},with size:{}",part.getSubmittedFileName(),part.getSize());
        });
        return UUID.randomUUID();
    }
    public DataFetcherResult<String> uploadAvatar(Part avatar, DataFetchingEnvironment environment){
         int maxUploadSizeInMb = 1024 * 1024;
        Part actualAvatar = environment.getArgument("avatar");
        if (actualAvatar.getSize()>maxUploadSizeInMb){
            return DataFetcherResult.<String>newResult().data(null).error( new CustomException(404,"File Too Large")).build();
        }

        log.info(actualAvatar.getSubmittedFileName());
        String minetype=actualAvatar.getContentType();

       return getType(minetype);


    }
    private DataFetcherResult<String> getType(String mimetype) {
        MediaType mediaType = MediaType.parseMediaType(mimetype);
        if (!isImage(mediaType) && !isJpeg(mediaType) ) return DataFetcherResult.<String>newResult().data(null).error( new CustomException(404,"File Is Not an Image")).build();

        return DataFetcherResult.<String>newResult().data("Image Uploaded Successfully").build();
    }

    private boolean isJpeg(MediaType mediaType) {
        return "jpeg".equalsIgnoreCase(mediaType.getSubtype());
    }

    private boolean isImage(MediaType mediaType) {
        return "image".equalsIgnoreCase(mediaType.getType());
    }
}
