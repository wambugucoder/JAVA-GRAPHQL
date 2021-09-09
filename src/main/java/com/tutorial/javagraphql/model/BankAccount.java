package com.tutorial.javagraphql.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Builder
public class BankAccount implements Serializable {
    UUID id;
    Currency currency;
    Client clientName;

}
