package br.com.primeiroprojetospringbatch.reader;

import br.com.primeiroprojetospringbatch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

@Configuration
public class ReadingFixedWidthFileItemReader {

    @Value("${clientsFile}")
    private String jobParameters;

    @StepScope
    @Bean
    public FlatFileItemReader<Client> readingFixedWidthFileReader() {

        return new FlatFileItemReaderBuilder<Client>()
                .name("readingFixedWidthFileReader")
                .resource(new PathResource(jobParameters))
                .fixedLength()
                .columns(new Range[]{new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
                .names("name", "surname", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
