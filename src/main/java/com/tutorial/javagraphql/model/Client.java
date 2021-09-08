package com.tutorial.javagraphql.model;

import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@Builder
public class Client {
    UUID id;
    String firstname;
    List<String> middlename;
    String lastname;
    Client client;
}
