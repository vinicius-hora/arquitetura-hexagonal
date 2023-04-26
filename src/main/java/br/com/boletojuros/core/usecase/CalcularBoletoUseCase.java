package br.com.boletojuros.core.usecase;

import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.domain.BoletoCalculado;
import br.com.boletojuros.core.domain.enuns.TipoBoleto;
import br.com.boletojuros.core.domain.enuns.TipoExcecao;
import br.com.boletojuros.core.exception.AplicationException;
import br.com.boletojuros.core.port.in.CalculoBoletoPort;
import br.com.boletojuros.core.port.out.ComplementoBoletoPort;
import br.com.boletojuros.core.port.out.SalvarCalculoBoletoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CalcularBoletoUseCase implements CalculoBoletoPort {

    private static final BigDecimal JUROS_DIARIO = BigDecimal.valueOf(0.033);

    private final ComplementoBoletoPort complementoBoletoPort;

    private final SalvarCalculoBoletoPort salvarCalculoBoletoPort;
    @Override
    public BoletoCalculado executar(String codigo, LocalDate dataPagamento) {
        var boleto = complementoBoletoPort.executar(codigo);

        validar(boleto);

        var diasVencidos = getDiasVencidos(boleto.getDataVencimento(), dataPagamento);
        var valorJurosDias = JUROS_DIARIO.multiply(boleto.getValor()).divide(BigDecimal.valueOf(100));
        var juros = valorJurosDias.multiply(BigDecimal.valueOf(diasVencidos)).setScale(2, RoundingMode.HALF_EVEN);

        var boletoCalculado = BoletoCalculado.builder()
                .codigo(boleto.getCodigo())
                .dataPagamento(dataPagamento)
                .juros(juros)
                .dataVencimento(boleto.getDataVencimento())
                .valorOriginal(boleto.getValor())
                .valor(boleto.getValor().add(juros))
                .tipo(boleto.getTipo())
                .build();

        salvarCalculoBoletoPort.executar(boletoCalculado);

        return boletoCalculado;
    }

    private void validar(Boleto boleto){
        if(boleto == null){
            throw new AplicationException(TipoExcecao.BOLETO_INVALIDO);
        }

        if(boleto.getTipo() != TipoBoleto.XPTO){
            throw new AplicationException(TipoExcecao.TIPO_BOLETO_INVALIDO);

        }

        if(boleto.getDataVencimento().isAfter(LocalDate.now())){
            throw new AplicationException(TipoExcecao.BOLETO_NAO_VENCIDO);

        }
    }

    private Long getDiasVencidos(LocalDate dataVencimento, LocalDate dataPagamento){
        return  ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
    }
}
