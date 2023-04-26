package br.com.boletojuros.adapter.datasource.integration;

import br.com.boletojuros.adapter.datasource.integration.client.ComplementoBoletoClient;
import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.port.out.ComplementoBoletoPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComplementoBoletoIntegration implements ComplementoBoletoPort {

    private final ComplementoBoletoClient client;
    @Override
    public Boleto executar(String codigo) {
        var boletoDTO = client.getBoleto(codigo);

        return null;
    }
}
