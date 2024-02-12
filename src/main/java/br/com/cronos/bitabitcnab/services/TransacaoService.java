package br.com.cronos.bitabitcnab.services;

import br.com.cronos.bitabitcnab.domain.TransacaoReport;

import java.util.List;

public interface TransacaoService {
    List<TransacaoReport> listTotaisTransacoesPorNomeLoja();
}
