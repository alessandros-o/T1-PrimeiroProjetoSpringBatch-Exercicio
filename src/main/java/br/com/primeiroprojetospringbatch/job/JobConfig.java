package br.com.primeiroprojetospringbatch.job;

import br.com.primeiroprojetospringbatch.step.StepConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public Job readingFileJob(@Qualifier("readingFixedWidthFileStep") Step readingFixedWidthFileStep, @Qualifier("readingDelimitedFileStep") Step readingDelimitedFileStep,
                              @Qualifier("readingMultipleFormatFileStep") Step readingMultipleFormatFileStep, @Qualifier("readingMultipleFilesStep") Step readingMultipleFilesStep) {

        return jobBuilderFactory
                .get("fixedWidthFile")
                .start(readingFixedWidthFileStep)
                .next(readingDelimitedFileStep)
                .next(readingMultipleFormatFileStep)
                .next(readingMultipleFilesStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
