package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sort")
@CrossOrigin(origins = "http://localhost:3000")
public class SortController {

    @GetMapping("/generate-random/{quantNumeros}")
    public List<Integer> generateRandom(@PathVariable int quantNumeros) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < quantNumeros; i++) {
            numbers.add(random.nextInt(10000));
        }
        return numbers;
    }

    @PostMapping("/load-file")
    public List<Integer> loadFile(@RequestBody String fileName) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            // Você pode retornar um erro para o frontend se o arquivo não for encontrado
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage());
        }
        return numbers;
    }


    //Endpoint para cada algoritmo de ordenação

    @PostMapping("/insersao")
    public ResultadoOrdenacao insersaoDireta(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.inserçãoDireta();
    }

    @PostMapping("/selecao")
    public ResultadoOrdenacao selecaoDireta(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.seleçãoDireta();
    }

    @PostMapping("/bubble")
    public ResultadoOrdenacao bubbleSort(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.bubblesort();
    }

    @PostMapping("/shaker")
    public ResultadoOrdenacao shakerSort(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.shakersort();
    }

    @PostMapping("/shell")
    public ResultadoOrdenacao shellSort(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.shellSort();
    }

    @PostMapping("/heap")
    public ResultadoOrdenacao heapSort(@RequestBody List<Integer> vetor) {
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.heapSort();
    }

    @PostMapping("/quick")
    public ResultadoOrdenacao quickSort(@RequestBody List<Integer> vetor) {
        // Lógica para criar VetorItens a partir da lista
        VetorItens vetorItens = new VetorItens(vetor.size());
        for (Integer num : vetor) {
            vetorItens.inserir(new Item(num));
        }
        return vetorItens.quicksort();
    }
}