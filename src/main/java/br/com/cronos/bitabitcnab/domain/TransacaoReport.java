package br.com.cronos.bitabitcnab.domain;

import java.math.BigDecimal;
import java.util.List;

public record TransacaoReport(
        String nomeDaLoja,
        BigDecimal total,
        List<Transacao> transacoes
) {

    public TransacaoReport addTotal(BigDecimal valor) {
        return new TransacaoReport(nomeDaLoja, total.add(valor), transacoes);
    }

    public TransacaoReport addTransacao(Transacao trancacao) {
        transacoes.add(trancacao);
        return new TransacaoReport(nomeDaLoja, total, transacoes);
    }
}
