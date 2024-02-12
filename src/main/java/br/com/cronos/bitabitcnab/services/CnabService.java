package br.com.cronos.bitabitcnab.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CnabService {
    void uploadCnabFile(MultipartFile file) throws IOException, Exception;
}
