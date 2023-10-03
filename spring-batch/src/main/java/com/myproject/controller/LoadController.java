package com.myproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class LoadController {

  private final JobLauncher jobLauncher;
  private final Job job;

  public LoadController(JobLauncher jobLauncher, Job job) {
    this.jobLauncher = jobLauncher;
    this.job = job;
  }

  @GetMapping
  public BatchStatus load() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
    JobParameters parameters = new JobParametersBuilder()
            .addLong("StartAt",System.currentTimeMillis()).toJobParameters();
    JobExecution jobExecution = jobLauncher.run(job, parameters);

    System.out.println("JobExecution "+ jobExecution.getStatus());

    System.out.println("Batch is running....");
    while(jobExecution.isRunning()){
      System.out.println("...");
    }
    return jobExecution.getStatus();
  }
}
