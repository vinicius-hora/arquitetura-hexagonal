package br.com.boletojuros.adapter.datasource.integration.dto;

import br.com.boletojuros.core.domain.enuns.TipoBoleto;
import com.fasterxml.jackson.annotation.JsonProperty;
import feign.form.FormProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class BoletoDTO {
    private String codigo;

    @JsonProperty("data_vencimento")
    private LocalDate dataVencimento;

    private BigDecimal valor;

    private TipoBoleto tipo;
}
