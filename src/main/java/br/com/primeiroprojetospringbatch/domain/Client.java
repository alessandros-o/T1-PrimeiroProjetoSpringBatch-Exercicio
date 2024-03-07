package br.com.primeiroprojetospringbatch.domain;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String name;
    private String surname;
    private Integer age;
    private String email;

    private List<Transaction> transacoes = new ArrayList<>();

    public List<Transaction> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transaction> transacoes) {
        this.transacoes = transacoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                (transacoes.isEmpty() ? "" : ", transacoes=" + transacoes) +
                '}';
    }
}
