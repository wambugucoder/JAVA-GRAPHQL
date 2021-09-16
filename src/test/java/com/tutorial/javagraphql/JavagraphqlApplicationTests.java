package com.tutorial.javagraphql;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ClockConfig.class)
class JavagraphqlApplicationTests {

    private static  String GRAPHQL_QUERY= "/graphql/BankAccountRequest.graphql";
    private static String GRAPHQL_RESPONSE="/graphql/BankAccountResponse.json";
    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void bank_account_resolver() throws IOException, JSONException {
       GraphQLResponse response= graphQLTestTemplate.postForResource(GRAPHQL_QUERY);
       String expectedResponseBody = read(GRAPHQL_RESPONSE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(expectedResponseBody,response.getRawResponse().getBody(),true);

    }
    private String read(String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }


}
