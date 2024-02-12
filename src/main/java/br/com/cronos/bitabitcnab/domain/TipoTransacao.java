package br.com.cronos.bitabitcnab.domain;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum TipoTransacao {
    DEBITO(1), BOLETO(2), FINANCIAMENTO(3), CREDITO(4),
    RECEBIMENTO_EMPRESTIMO(5), VENDAS(6), RECEBIMENTO_TED(7),
    RECEBIMENTO_DOC(8), ALUGUEL(9);

    private int tipo;

    public BigDecimal getSinal() {
        return switch (tipo) {
            case 1, 4, 5, 6, 7, 8 -> new BigDecimal(1);
            case 2, 3, 9 -> new BigDecimal(-1);
            default -> new BigDecimal(0);
        };
    }

    public static TipoTransacao findByTipo(int tipo) {
        for (TipoTransacao tipoTransacao: values()) {
            if (tipoTransacao.tipo == tipo) {
                return tipoTransacao;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + tipo);
    }
}
