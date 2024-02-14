package br.com.primeiroprojetospringbatch.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class ReadingMultipleFormatFileItemReader {

    @Value("${clientsMultiFormatFile}")
    private String file;

    @SuppressWarnings("rawtypes")
    @StepScope
    @Bean(name = "readingMultipleFormatFileReader")
    public FlatFileItemReader readingMultipleFormatFileReader(LineMapper lineMapper) {

        return new FlatFileItemReaderBuilder()
                .name("readingMultipleFormatFileReader")
                .resource(new PathResource(file))
                .lineMapper(lineMapper)
                .build();
    }
}
