package br.com.boletojuros.adapter.http;

import br.com.boletojuros.adapter.datasource.mapper.BoletoCalculadoMapper;
import br.com.boletojuros.adapter.http.dto.CalculoBoletoRequest;
import br.com.boletojuros.adapter.http.dto.CalculoBoletoResponse;
import br.com.boletojuros.adapter.http.mapper.CalculoBoletoMapper;
import br.com.boletojuros.core.port.in.CalculoBoletoPort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/boleto")
@RequiredArgsConstructor
public class CalculoBoletoController {

    private final CalculoBoletoPort calculoBoletoPort;

    private final CalculoBoletoMapper calculoBoletoMapper;
    @PostMapping("/calculo")
    @Operation(summary = "Calcular juros de um boleto")
    public ResponseEntity<CalculoBoletoResponse> calcularBoleto(@Valid @RequestBody CalculoBoletoRequest boletoRequest){
        var boletoCalculado = calculoBoletoPort.executar(boletoRequest.getCodigo(), boletoRequest.getDataPagamento());
        return ResponseEntity.ok(calculoBoletoMapper.toDto(boletoCalculado));
    }
}
