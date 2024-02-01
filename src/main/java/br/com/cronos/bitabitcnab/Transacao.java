package br.com.cronos.bitabitcnab;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

public record Transacao(
        Long id,
        Integer tipo,
        Date data,
        BigDecimal valor,
        Long cpf,
        String cartao,
        Time hora,
        String donoDaLoja,
        String nomeDaLoja
) {
}
