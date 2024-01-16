package br.com.primeiroprojetospringbatch.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComparadorProcessor {

    @Bean
    public FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>
                (item -> item % 2 == 0 ? String.format("%s é par.", item) : String.format("%s é ímpar.", item));
    }
}
