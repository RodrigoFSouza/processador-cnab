package br.com.cronos.bitabitcnab;

import br.com.cronos.bitabitcnab.domain.Transacao;
import br.com.cronos.bitabitcnab.repositories.TransacaoRepository;
import br.com.cronos.bitabitcnab.services.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks
    private TransacaoServiceImpl service;

    @Mock
    private TransacaoRepository repository;

    @Test
    public void testListaTotaisTransacoesNomeDaLoja() {
        // arrange
        final String lojaA = "Loja A";
        final String lojaB = "Loja B";

        var transacao1 = new Transacao(
            1L, 1, new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(200.0), 1234567739L, "1234-5679-9012-3456",
                new Time(System.currentTimeMillis()), "Dono da lojaB", lojaB);

        var transacao2 = new Transacao(
                2L, 2, new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(-100.0), 1234567740L, "1234-5679-9012-9987",
                new Time(System.currentTimeMillis()), "Dono da lojaB", lojaB);

        var transacao3 = new Transacao(
                3L, 1, new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(300.0), 1234567741L, "1234-5679-9012-1111",
                new Time(System.currentTimeMillis()), "Dono da lojaB", lojaB);

        var transacao4 = new Transacao(
                4L, 1, new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(100.0), 1234567742L, "1234-5679-9012-2222",
                new Time(System.currentTimeMillis()), "Dono da lojaB", lojaA);

        var mockTransacoes = List.of(transacao1, transacao2, transacao3, transacao4);

        when(repository.findAllByOrderByNomeDaLojaAscIdDesc()).thenReturn(mockTransacoes);

        // action
        var reports = service.listTotaisTransacoesPorNomeLoja();

        // assert
        assertEquals(2, reports.size());

        reports.forEach(report -> {
            if (report.nomeDaLoja().equals(lojaA)) {
                assertEquals(BigDecimal.valueOf(100.0), report.total());
                assertEquals(1, report.transacoes().size());
                assertTrue(report.transacoes().contains(transacao4));
            } else {
                assertEquals(3, report.transacoes().size());
                assertEquals(BigDecimal.valueOf(400.0), report.total());
                assertTrue(report.transacoes().contains(transacao1));
                assertTrue(report.transacoes().contains(transacao2));
                assertTrue(report.transacoes().contains(transacao3));
            }
        });
    }
}
