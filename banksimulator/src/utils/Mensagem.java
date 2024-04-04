package utils;

import entities.Account;


import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Mensagem {

    private String nome;
    private int conta;
    public Mensagem (String nome, int conta) {
        this.nome = nome;
        this.conta = conta;
    }

    public void bemVindo() {
        System.out.println(Collor.YELLOW_BOLD_BRIGHT + "\n=== BEM-VINDO AO NOSSO BANCO ===" + Collor.RESET);
        System.out.println(Collor.WHITE_BOLD + "Bem-vindo ao nosso banco! Aqui você pode realizar diversas operações financeiras de forma rápida e segura.");
        System.out.println("Se precisar de ajuda, basta selecionar a opção desejada no menu principal.");
        System.out.println("Aproveite sua experiência conosco!");
        System.out.println();
    }

    public void sair() {
        System.out.println("\n" + Collor.GREEN + "Até logo, " + nome + "! Obrigado por usar nossos serviços." + Collor.RESET);
        System.exit(0);
    }

    public void status() {
        System.out.println("\n" + Collor.CYAN + "=================================" + Collor.RESET);
        System.out.println("Olá, " + Collor.GREEN + nome + Collor.RESET + "!");
        System.out.println("Número da sua conta: " + Collor.PURPLE + conta + Collor.RESET);
       // System.out.println("Saldo disponível: " + Collor.YELLOW + "R$" + conta.getSaldo() + Collor.RESET);
        System.out.println(Collor.CYAN + "=================================" + Collor.RESET + "\n");
    }

    public void menu() {
        System.out.println(Collor.GREEN_BOLD_BRIGHT + "==== MENU DE OPERAÇÕES ====" + Collor.RESET);
        System.out.println("1 - " + Collor.CYAN_BOLD + "Depositar" + Collor.RESET);
        System.out.println("2 - " + Collor.CYAN_BOLD + "Sacar" + Collor.RESET);
        System.out.println("3 - " + Collor.CYAN_BOLD + "Transferir" + Collor.RESET);
        System.out.println("4 - " + Collor.CYAN_BOLD + "Histórico" + Collor.RESET);
        System.out.println("5 - " + Collor.CYAN_BOLD + "Menu de ajuda" + Collor.RESET);
        System.out.println("6 - " + Collor.RED_BOLD + "Sair" + Collor.RESET);
        System.out.print("Digite sua opção: ");
    }

    public void depositar() {
        System.out.println("\n" + Collor.YELLOW_BOLD + "=== DEPOSITAR ===" + Collor.RESET);
        System.out.println("Digite o valor que deseja depositar:");
    }

    public void depositarStatus(BigDecimal valorDeposito) {
        System.out.println("\n" + Collor.GREEN + "=== DEPÓSITO REALIZADO ===" + Collor.RESET);
        System.out.println("Valor depositado: " + valorDeposito + " R$");
        //System.out.println("Novo saldo: " + conta.getSaldo() + " R$");
        System.out.println("✔️ Operação bem-sucedida!");
    }

    public void sacar() {
        System.out.println("\n" + Collor.YELLOW + "=== REALIZAR SAQUE ===" + Collor.RESET);
        System.out.println("Por favor, informe o valor que deseja sacar:");
    }

    public void transferir() {
        System.out.println("\n" + Collor.YELLOW + "=== REALIZAR TRANSFERÊNCIA ===" + Collor.RESET);
        System.out.println("Por favor, digite o número da conta de destino:");
    }

    public void transferirValor() {
        System.out.println(Collor.YELLOW + "Por favor, digite o valor que deseja transferir:" + Collor.RESET);
    }

    public void entradaInvalidaNum() {
        System.out.println(Collor.RED_BOLD_BRIGHT + "\nOpção inválida. Por favor, digite um número correspondente à operação desejada." + Collor.RESET + "\n");
        System.out.println("❌ Ocorreu um erro durante a operação. Por favor, tente novamente.");

    }

    public void historico() {
        System.out.println(Collor.PURPLE_BRIGHT + "\n=== HISTÓRICO BANCÁRIO ===\n");
    }

    public void nome() {
        System.out.println(Collor.CYAN + "Digite seu nome:" + Collor.RESET);
    }

    public void conta() {
        System.out.println(Collor.CYAN + "Por favor, crie o número da sua conta com 5 dígitos:" + Collor.RESET);

    }

    public void numeroNulo() {
        System.out.println(Collor.RED + "Erro: Valor não pode ser nulo." + Collor.RESET);
        System.out.println("❌ Ocorreu um erro durante a operação. Por favor, tente novamente.");

    }

    public void numeroNegativo() {
        System.out.println(Collor.RED + "Erro: O valor não pode ser negativo." + Collor.RESET);
        System.out.println("❌ Ocorreu um erro durante a operação. Por favor, tente novamente.");


    }

    public void saldoInsuficiente(BigDecimal valorSaque) {
        System.out.println(Collor.RED + "Operação cancelada: saldo insuficiente para sacar R$" + valorSaque + "." + Collor.RESET);
        System.out.println("❌ Ocorreu um erro durante a operação. Por favor, tente novamente.");

    }

    public void saqueRealizado(BigDecimal valorSaque) {
        System.out.println(Collor.GREEN + "Saque de R$" + valorSaque + " realizado com sucesso." + Collor.RESET);
        //System.out.println("Saldo atualizado: R$" + conta.getSaldo());
        System.out.println("✔️ Operação bem-sucedida!");

    }

    public void transfInsuficiente(BigDecimal valorTransferencia, int numDaConta) {
        System.out.println(Collor.RED + "Transferência de R$" + valorTransferencia + " para a conta " + numDaConta + " não realizada: saldo insuficiente." + Collor.RESET);
        System.out.println("❌ Ocorreu um erro durante a operação. Por favor, tente novamente.");

    }

    public void transferenciaRealizada(BigDecimal valorTransferencia, int numDaConta) {
        System.out.println(Collor.GREEN + "Transferência de R$" + valorTransferencia + " para a conta " + numDaConta + " realizada com sucesso." + Collor.RESET);
        //System.out.println("Saldo atual: " + conta.getSaldo() + " R$");
        System.out.println("✔️ Operação bem-sucedida!");

    }

    public void depositoHistorico(ArrayList<BigDecimal> depositoHistorico, int i) {
        System.out.println(Collor.GREEN + "Depósito #" + (i + 1) + ": " + depositoHistorico.get(i) + " R$");
    }

    public void saqueHistorico(ArrayList<BigDecimal> saqueHistorico, int i) {
        System.out.println(Collor.RED + "Saque #" + (i + 1) + ": " + saqueHistorico.get(i) + " R$");
    }

    public void transfHistorico(ArrayList<BigDecimal> transfHistorico, int i) {
        System.out.println(Collor.YELLOW + "Transferência #" + (i + 1) + ": " + transfHistorico.get(i) + " R$");
    }

    public void exibirMenuAjuda() {
        System.out.println("\n" + Collor.GREEN_BOLD_BRIGHT + "==== MENU DE AJUDA ====" + Collor.RESET + "\n");
        System.out.println("1 - " + Collor.CYAN_BOLD + "Depositar:" + Collor.RESET + " Permite adicionar fundos à sua conta.");
        System.out.println("2 - " + Collor.CYAN_BOLD + "Sacar:" + Collor.RESET + " Permite retirar fundos da sua conta.");
        System.out.println("3 - " + Collor.CYAN_BOLD + "Transferir:" + Collor.RESET + " Permite enviar dinheiro para outra conta.");
        System.out.println("4 - " + Collor.CYAN_BOLD + "Histórico:" + Collor.RESET + " Exibe o histórico de transações da sua conta.");
        System.out.println("5 - " + Collor.RED_BOLD + "Sair:" + Collor.RESET + " Encerra a sessão bancária.\n");

    }

    // NOVO METODO PARA CRIAÇÃO TESTE
    // IMPLEMENTANDO A NOVA FUNC. CRIAR CONTA

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public void status(Account contaUser) {
        System.out.println("\n" + Collor.CYAN + "=================================" + Collor.RESET);
        System.out.println("Olá, " + Collor.GREEN + contaUser.getNomeDoTitular() + Collor.RESET + "!");
        System.out.println("Número da sua conta: " + Collor.PURPLE + contaUser.getNumeroDaConta() + Collor.RESET);
        System.out.println("Saldo disponível: " + Collor.YELLOW + "R$" + contaUser.getSaldo() + Collor.RESET);
        System.out.println("Senha: " + Collor.YELLOW + contaUser.getSenha() + Collor.RESET);
        System.out.println("DIA: " + Collor.YELLOW + contaUser.getDia().format(dtf) + Collor.RESET);
        System.out.println(Collor.CYAN + "=================================" + Collor.RESET + "\n");

    }

}
