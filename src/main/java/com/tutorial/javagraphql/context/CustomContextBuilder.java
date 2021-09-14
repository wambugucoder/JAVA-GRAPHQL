package com.tutorial.javagraphql.context;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.var;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public class CustomContextBuilder implements GraphQLServletContextBuilder {
    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userId= httpServletRequest.getHeader("user_id");
       DefaultGraphQLServletContext build= DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
       return new CustomContext(userId,build);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("Unsupported Yet");
    }

    /**
     * @return the graphql context
     */
    @Override
    public GraphQLContext build() {
       throw new IllegalStateException("Unsupported Yet");
    }
}
