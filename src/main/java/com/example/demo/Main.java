package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static int quantNumeros = 1000;
    static VetorItens vetor = new VetorItens(quantNumeros);

    public static void main(String[] args) {
        int escolha;
        do {
            exibirMenu();
            try {
                escolha = Integer.parseInt(entrada.nextLine());
                processarEscolha(escolha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                escolha = -1; // Para continuar o loop
            }
        } while (escolha != 0);
    }

    private static void exibirMenu() {
        System.out.print(
                "\n--- Menu de Opções ---\n" +
                "1 - Gerar e Gravar arquivo com números\n" +
                "2 - Ler arquivo e carregar dados\n" +
                "3 - Inserção Direta\n" +
                "4 - Seleção Direta\n" +
                "5 - Bubble Sort\n" +
                "6 - Shaker Sort\n" +
                "7 - Shell Sort\n" +
                "8 - Heap Sort\n" +
                "9 - Quick Sort\n" +
                "0 - Sair\n" +
                "Opção: ");
    }

    private static void processarEscolha(int escolha) {
        switch (escolha) {
            case 1:
                criarEGravarArquivo();
                break;
            case 2:
                lerArquivo();
                break;
            case 3:
                executarOrdenacao("Inserção Direta", vetor.inserçãoDireta());
                break;
            case 4:
                executarOrdenacao("Seleção Direta", vetor.seleçãoDireta());
                break;
            case 5:
                executarOrdenacao("Bubble Sort", vetor.bubblesort());
                break;
            case 6:
                executarOrdenacao("Shaker Sort", vetor.shakersort());
                break;
            case 7:
                executarOrdenacao("Shell Sort", vetor.shellSort());
                break;
            case 8:
                executarOrdenacao("Heap Sort", vetor.heapSort());
                break;
            case 9:
                executarOrdenacao("Quick Sort", vetor.quicksort());
                break;
            case 0:
                System.out.println("Obrigado por usar o programa, saindo...");
                break;
            default:
                System.out.println("Opção inválida! 🤖");
                break;
        }
    }

    private static void executarOrdenacao(String nomeAlgoritmo, ResultadoOrdenacao resultado) {
        System.out.println("\n---- Antes da ordenação ----");
        vetor.exibir();
        System.out.println("\n---- Executando " + nomeAlgoritmo + " ----");
        // A lógica de ordenação já foi executada para obter o 'resultado'
        System.out.println("\n---- Depois da ordenação ----");
        vetor.exibir();
        System.out.println("\n" + resultado);
    }

    private static void criarEGravarArquivo() {
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = entrada.nextLine();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            Random random = new Random();
            for (int i = 0; i < quantNumeros; i++) {
                writer.write(String.valueOf(random.nextInt(1000)));
                writer.newLine();
            }
            System.out.printf("Arquivo '%s' criado com sucesso com %d números!\n", nomeArquivo, quantNumeros);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    private static void lerArquivo() {
        System.out.print("Digite o nome do arquivo para ler: ");
        String nomeArquivo = entrada.nextLine();
        vetor.esvaziar();
        List<Integer> numbers = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
            for (Integer num : numbers) {
                vetor.inserir(new Item(num));
            }
            System.out.printf("Arquivo '%s' lido com sucesso. %d números carregados.\n", nomeArquivo, numbers.size());
            System.out.println("Conteúdo do vetor:");
            vetor.exibir();

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro: O arquivo contém dados que não são números inteiros.");
        }
    }
}