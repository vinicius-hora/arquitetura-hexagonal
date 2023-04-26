package br.com.boletojuros.core.domain.enuns;

public enum TipoExcecao {
    BOLETO_INVALIDO{
        @Override
        public String getMensagemErro() {
            return "O boleto encontrado é inválido";
        }
    },
    TIPO_BOLETO_INVALIDO{
        @Override
        public String getMensagemErro() {
            return "Só pode ser calculado juros do boleto XPTO";
        }
    },
    BOLETO_NAO_VENCIDO{
        @Override
        public String getMensagemErro() {
            return "O boleto informado ainda não está vencido";
        }
    };

    public abstract String getMensagemErro();
}
