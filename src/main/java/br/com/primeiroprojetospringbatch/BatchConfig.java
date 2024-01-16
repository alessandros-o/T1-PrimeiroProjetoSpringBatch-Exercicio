package br.com.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job imprimeOlaJob() {

        return jobBuilderFactory
                .get("imprimeOlaJob")
                .start(imprimeParOuImpar())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Step imprimeParOuImpar() {
        return stepBuilderFactory
                .get("imprimeParOuImpar")
                .<Integer, String>chunk(1)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6 ,7 ,8 ,9, 10);
        return new IteratorItemReader<Integer>(numeros);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>
                (item -> item % 2 == 0 ? String.format("%s é par.", item) : String.format("%s é ímpar.", item));
    }

    private ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}
