package br.com.boletojuros.adapter.http.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
public class CalculoBoletoRequest {
    @NotNull
    private String codigo;
    @NotNull
    @JsonProperty("data_pagamento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPagamento;
}
