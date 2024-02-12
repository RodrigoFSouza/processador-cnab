package br.com.cronos.bitabitcnab.controllers;

import br.com.cronos.bitabitcnab.domain.TransacaoReport;
import br.com.cronos.bitabitcnab.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping
    public List<TransacaoReport> listAll() {
        return transacaoService.listTotaisTransacoesPorNomeLoja();
    }
}
