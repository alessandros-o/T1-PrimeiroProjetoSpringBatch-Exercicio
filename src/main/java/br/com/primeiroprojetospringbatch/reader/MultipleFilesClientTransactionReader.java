package br.com.primeiroprojetospringbatch.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resources;
import java.io.IOException;
import java.util.Arrays;

@Configuration
public class MultipleFilesClientTransactionReader {

    @Value("${clientsMultiFiles}")
    private String file;

    @SuppressWarnings("rawtypes")
    @StepScope
    @Bean(name = "multiplosArquivosClienteTransacao")
    public MultiResourceItemReader multiplosArquivosClienteTransacao(FlatFileItemReader readingMultipleFormatFileReader) throws IOException {
        return new MultiResourceItemReaderBuilder<>()
                .name("multiplosArquivosClienteTransacao")
                .resources(getAllFiles())
                .delegate(new FileClientTransactionReader(readingMultipleFormatFileReader))
                .build();
    }

    private Resource[] getAllFiles() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources("file:" + file + "/clientes1*.txt");
    }
}
