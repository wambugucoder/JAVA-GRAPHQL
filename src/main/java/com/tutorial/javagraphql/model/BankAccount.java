package com.tutorial.javagraphql.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Builder
public class BankAccount implements Serializable {
    UUID id;
    Currency currency;
    Client clientName;

}
