package br.com.boletojuros.adapter.datasource.database;

import br.com.boletojuros.adapter.datasource.database.repository.BoletoCalculadoRepository;
import br.com.boletojuros.adapter.datasource.mapper.BoletoCalculadoMapper;
import br.com.boletojuros.core.domain.BoletoCalculado;
import br.com.boletojuros.core.port.out.SalvarCalculoBoletoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarBoletoCalculado implements SalvarCalculoBoletoPort {

    private final BoletoCalculadoMapper mapper;

    private final BoletoCalculadoRepository repository;
    @Override
    public void executar(BoletoCalculado boletoCalculado) {
        var boletoEntity = mapper.toEntity(boletoCalculado);

        repository.save(boletoEntity);

    }
}
