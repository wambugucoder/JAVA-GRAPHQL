package com.tutorial.javagraphql.resolvers.bank.mutation;

import com.tutorial.javagraphql.exceptions.CustomException;
import com.tutorial.javagraphql.model.BankAccount;
import com.tutorial.javagraphql.model.Currency;
import com.tutorial.javagraphql.request.BankAccountInput;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
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
    public DataFetcherResult<String> uploadAvatar(Part avatar, DataFetchingEnvironment environment){
         int maxUploadSizeInMb = 1 * 1024 * 1024;
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
