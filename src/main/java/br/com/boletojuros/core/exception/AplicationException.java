package br.com.boletojuros.core.exception;

import br.com.boletojuros.core.domain.enuns.TipoExcecao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AplicationException extends RuntimeException{
    private TipoExcecao tipoExcecao;
}
