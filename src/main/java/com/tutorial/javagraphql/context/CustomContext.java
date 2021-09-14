package com.tutorial.javagraphql.context;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Getter
@Setter
@RequiredArgsConstructor
public class CustomContext implements GraphQLServletContext {

    private final String userId;
    private final GraphQLServletContext context;

    @Override
    public List<Part> getFileParts() {
        return context.getFileParts();
    }

    @Override
    public Map<String, List<Part>> getParts() {
        return context.getParts();
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return context.getHttpServletRequest();
    }

    @Override
    public HttpServletResponse getHttpServletResponse() {
        return context.getHttpServletResponse();
    }

    /**
     * @return the subject to execute the query as.
     */
    @Override
    public Optional<Subject> getSubject() {
        return context.getSubject();
    }

    /**
     * @return the Dataloader registry to use for the execution. Must not return <code>null</code>
     */
    @Override
    public @NonNull DataLoaderRegistry getDataLoaderRegistry() {
        return context.getDataLoaderRegistry();
    }
}
