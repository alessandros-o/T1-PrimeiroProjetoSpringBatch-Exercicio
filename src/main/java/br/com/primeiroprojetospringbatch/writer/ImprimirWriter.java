package br.com.primeiroprojetospringbatch.writer;

import br.com.primeiroprojetospringbatch.domain.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimirWriter {

//    @Bean
//    public ItemWriter<String> imprimeWriter() {
//        return itens -> itens.forEach(System.out::println);
//    }

    @Bean
    public ItemWriter<Client> readingFixedWidthFileWriter() {
        return itens -> itens.forEach(System.out::println);
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public ItemWriter readingMultipleFormatFileWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
