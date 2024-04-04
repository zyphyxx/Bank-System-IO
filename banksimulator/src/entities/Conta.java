package entities;

import services.OperacoesService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Conta extends OperacoesService {

    private String nomeDoTitular;
    private int numeroDaConta;
    private BigDecimal saldo;
    // IMPLEMENTANDO AS NOVAS FEAT. SENHA E DIA
    private String senha;
    private LocalDateTime dia = LocalDateTime.now();

    public Conta(){
        super();
        this.saldo = BigDecimal.ZERO;
    }

    // IMPLEMENTANDO O NOVO CONSTRUTOR PARA CRIAR O USUARIO
    public Conta (String nome, int conta, String senha){
        this.nomeDoTitular = nome;
        this.numeroDaConta = conta;
        this.senha = senha;
        // iniciando o saldo
        this.saldo = BigDecimal.ZERO;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public void setNomeDoTitular(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public void setDia(LocalDateTime dia) {
        this.dia = dia;
    }
}
