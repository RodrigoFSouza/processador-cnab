package br.com.cronos.bitabitcnab.controllers;

import br.com.cronos.bitabitcnab.services.CnabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("cnab")
public class CnabController {

    private final CnabService cnabService;

    @PostMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file) throws Exception {
        cnabService.uploadCnabFile(file);
        return "Processamento iniciado em background!";
    }

}
