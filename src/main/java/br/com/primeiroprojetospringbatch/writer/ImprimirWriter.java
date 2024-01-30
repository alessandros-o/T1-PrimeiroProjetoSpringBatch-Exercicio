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
    public ItemWriter<Client> readingFileWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
