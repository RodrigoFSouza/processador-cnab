package br.com.cronos.bitabitcnab.services.impl;


import br.com.cronos.bitabitcnab.services.CnabService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CnabServiceImpl implements CnabService {

    private final Path fileStorageLocation;

    private final JobLauncher jobLauncher;
    private final Job job;

    public CnabServiceImpl(@Value("${file.upload-dir}")String fileUploadDir,
                           @Qualifier("jobLauncherAsync") JobLauncher jobLauncher, Job job) {
        this.fileStorageLocation = Paths.get(fileUploadDir);
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void uploadCnabFile(MultipartFile file) throws Exception {
        var fileName = StringUtils.cleanPath(file.getOriginalFilename());
        var targetLocation = fileStorageLocation.resolve(fileName);
        file.transferTo(targetLocation);

        var jobParameters = new JobParametersBuilder()
                .addJobParameter("cnab", file.getOriginalFilename(), String.class, true)
                .addJobParameter("cnabFile", "file:" + targetLocation.toString(), String.class)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
