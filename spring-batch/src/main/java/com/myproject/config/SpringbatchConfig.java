package com.myproject.config;

import com.myproject.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.annotation.Validated;

@Configuration
public class SpringbatchConfig {

  @Bean
  private LineMapper<User> lineMapper() {
    DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
    return null;
  }

  @Bean
  public FlatFileItemReader<User> flatFileItemReader(@Value("${input}") Resource resource) {
    FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
    flatFileItemReader.setResource(resource);
    flatFileItemReader.setName("CSV-Reader");
    flatFileItemReader.setLinesToSkip(1);
    flatFileItemReader.setLineMapper(lineMapper());
    return flatFileItemReader;
  }



  @Bean
  public Step step(
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager,
      ItemReader<User> itemReader,
      ItemProcessor<User, User> itemProcessor,
      ItemWriter<User> itemWriter) {
    Step step =
        new StepBuilder("ETL-file-load", jobRepository)
            .<User, User>chunk(10, platformTransactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    return step;
  }

  @Bean
  public Job job(
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager,
      Step step) {
    return new JobBuilder("ETL-Load", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(step)
        .build();
  }
}
