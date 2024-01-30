package br.com.primeiroprojetospringbatch.reader;

import br.com.primeiroprojetospringbatch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class ReadingDelimitedFileItemReader {

    @Value("${clientsDelimitedFile}")
    private String clientsDelimitedFile;

    @StepScope
    @Bean
    public FlatFileItemReader<Client> readingDelimitedFileReader () {
        return new FlatFileItemReaderBuilder<Client>()
                .name("readingDelimitedFileReader")
                .resource(new PathResource(clientsDelimitedFile))
                .delimited()
                .names("name", "surname", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
