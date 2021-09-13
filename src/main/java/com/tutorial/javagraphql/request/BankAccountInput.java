package com.tutorial.javagraphql.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class BankAccountInput {
    @NotBlank(message = "First Name Cannot be blank")
    String firstname;
    int age;


}
