package br.com.primeiroprojetospringbatch.step;

import br.com.primeiroprojetospringbatch.domain.Client;
import br.com.primeiroprojetospringbatch.processor.ComparadorProcessor;
import br.com.primeiroprojetospringbatch.reader.ContadorReader;
import br.com.primeiroprojetospringbatch.reader.ReadingFixedWidthFileItemReader;
import br.com.primeiroprojetospringbatch.writer.ImprimirWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Autowired
//    private ContadorReader contadorReader;

//    @Autowired
//    private ComparadorProcessor comparadorProcessor;
//
//    @Autowired
//    private ImprimirWriter imprimirWriter;
//
//    @Bean
//    public Step imprimeParOuImpar() {
//        return stepBuilderFactory
//                .get("imprimeParOuImpar")
//                .<Integer, String>chunk(1)
//                .reader(contadorReader.contaAteDezReader())
//                .processor(comparadorProcessor.parOuImparProcessor())
//                .writer(imprimirWriter.imprimeWriter())
//                .build();
//    }

    @Bean
    public Step readingFixedWidthFile(ItemReader<Client> readingFixedWidthFileReader, ItemWriter<Client> readingFixedWidthFileWriter) {
        return stepBuilderFactory
                .get("readingFixedWidthFileStep")
                .<Client, Client>chunk(1)
                .reader(readingFixedWidthFileReader)
                .writer(readingFixedWidthFileWriter)
                .build();
    }


}
