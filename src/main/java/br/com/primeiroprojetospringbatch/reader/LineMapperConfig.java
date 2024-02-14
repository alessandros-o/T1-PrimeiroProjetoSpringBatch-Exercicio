package br.com.primeiroprojetospringbatch.reader;

import br.com.primeiroprojetospringbatch.domain.Client;
import br.com.primeiroprojetospringbatch.domain.Transaction;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LineMapperConfig {

    @SuppressWarnings("rawtypes")
    @Bean
    public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        //noinspection unchecked
        lineMapper.setTokenizers(tokenizers());
        //noinspection unchecked
        lineMapper.setFieldSetMappers(fieldSetMappers());

        return lineMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {

        Map<String, LineTokenizer> tokenizerMap = new HashMap<>();
        tokenizerMap.put("0*", clienteLineTokenizer());
        tokenizerMap.put("1*", transacaolineTokenizer());

        return tokenizerMap;
    }

    private LineTokenizer clienteLineTokenizer() {

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "surname", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);

        return lineTokenizer;
    }

    private LineTokenizer transacaolineTokenizer() {

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "description", "value");
        lineTokenizer.setIncludedFields(1, 2, 3);

        return lineTokenizer;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, FieldSetMapper> fieldSetMappers() {

        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Client.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transaction.class));

        return fieldSetMappers;
    }

    @SuppressWarnings("rawtypes")
    private FieldSetMapper fieldSetMapper(Class clientClass) {
        BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(clientClass);

        return beanWrapperFieldSetMapper;
    }
}
