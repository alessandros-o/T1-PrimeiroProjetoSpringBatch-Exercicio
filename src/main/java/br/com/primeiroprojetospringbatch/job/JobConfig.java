package br.com.primeiroprojetospringbatch.job;

import br.com.primeiroprojetospringbatch.step.StepConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepConfig stepConfig;

    @Bean
    public Job fixedWidthFileJob(Step readingFixedWidthFileStep) {

        return jobBuilderFactory
                .get("fixedWidthFile")
                .start(readingFixedWidthFileStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
