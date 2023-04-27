package br.com.boletojuros.adapter.datasource.integration;

import br.com.boletojuros.adapter.datasource.integration.client.ComplementoBoletoClient;
import br.com.boletojuros.adapter.datasource.mapper.BoletoMapper;
import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.port.out.ComplementoBoletoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplementoBoletoIntegration implements ComplementoBoletoPort {

    private final BoletoMapper mapper;

    private final ComplementoBoletoClient client;
    @Override
    public Boleto executar(String codigo) {
        var boletoDTO = client.getBoleto(codigo);

        return mapper.toDomain(boletoDTO);
    }
}
