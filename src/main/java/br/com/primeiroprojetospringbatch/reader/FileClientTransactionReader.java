package br.com.primeiroprojetospringbatch.reader;

import br.com.primeiroprojetospringbatch.domain.Client;
import br.com.primeiroprojetospringbatch.domain.Transaction;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class FileClientTransactionReader implements ItemStreamReader<Client>, ResourceAwareItemReaderItemStream<Client> {

    private Object objAtual;
    private FlatFileItemReader<Object> delegate;

    public FileClientTransactionReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Client read() throws Exception {

        if (objAtual == null) {
            objAtual = delegate.read();
        }

        Client cliente = (Client) objAtual;

        if (objAtual != null) {
            while (peek() instanceof Transaction) {
                cliente.getTransacoes().add((Transaction) objAtual);
            }
        }

        return cliente;
    }

    private Object peek() throws Exception {
        objAtual = delegate.read();
        return objAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
