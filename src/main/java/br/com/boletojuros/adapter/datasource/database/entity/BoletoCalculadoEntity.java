package br.com.boletojuros.adapter.datasource.database.entity;

import br.com.boletojuros.core.domain.enuns.TipoBoleto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "boleto_calculado")
@Getter
@Setter
public class BoletoCalculadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String codigo;
    @Column(name = "valor_original")
    private BigDecimal valorOriginal;
    @Column
    private BigDecimal valor;
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    @Column
    private BigDecimal juros;
    @Column
    private TipoBoleto tipo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoletoCalculadoEntity that = (BoletoCalculadoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
