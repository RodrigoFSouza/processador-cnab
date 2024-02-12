package br.com.cronos.bitabitcnab.services.impl;

import br.com.cronos.bitabitcnab.domain.TipoTransacao;
import br.com.cronos.bitabitcnab.domain.TransacaoReport;
import br.com.cronos.bitabitcnab.repositories.TransacaoRepository;
import br.com.cronos.bitabitcnab.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;

    @Override
    public List<TransacaoReport> listTotaisTransacoesPorNomeLoja() {
        var listTransacoes = transacaoRepository.findAllByOrderByNomeDaLojaAscIdDesc();

        var reportMap = new LinkedHashMap<String, TransacaoReport>();

        listTransacoes.forEach(transacao -> {
            String nomeDaLoja = transacao.nomeDaLoja();
            var tipoTransacao = TipoTransacao.findByTipo(transacao.tipo());

            BigDecimal valor = transacao.valor().multiply(tipoTransacao.getSinal());

            reportMap.compute(nomeDaLoja, (key, existingReport) -> {
                var report = (existingReport != null) ? existingReport :
                    new TransacaoReport(key, BigDecimal.ZERO, new ArrayList<>());

                return report.addTotal(valor).addTransacao(transacao.withValor(valor));
            });
        });

        return new ArrayList<>(reportMap.values());
    }
}
