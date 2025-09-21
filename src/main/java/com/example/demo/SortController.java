package com.example.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/sort")
@CrossOrigin(origins = "http://localhost:3000")
public class SortController {

    // Lógica para gerar números aleatórios
    @GetMapping("/generate-random/{quantNumeros}")
    public List<Integer> generateRandom(@PathVariable int quantNumeros) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < quantNumeros; i++) {
            numbers.add(random.nextInt(10000));
        }
        return numbers;
    }

    // Lógica para carregar arquivo
    @PostMapping("/load-file")
    public List<Integer> loadFile(@RequestParam("file") MultipartFile file) {
        List<Integer> numbers = new ArrayList<>();
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo enviado está vazio.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Ignorando linha não numérica: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage());
        }
        return numbers;
    }

    // --- Endpoints de ordenação consolidado ---
    @PostMapping("/{algoritmo}")
    public ResultadoOrdenacao sortAlgorithm(@PathVariable String algoritmo, @RequestBody List<Integer> vetorOriginal) {
        VetorItens vetorItens = new VetorItens(vetorOriginal.size());
        for (Integer num : vetorOriginal) {
            vetorItens.inserir(new Item(num));
        }

        ResultadoOrdenacao resultado;

        switch (algoritmo) {
            case "insersao":
                resultado = vetorItens.inserçãoDireta();
                break;
            case "selecao":
                resultado = vetorItens.seleçãoDireta();
                break;
            case "bubble":
                resultado = vetorItens.bubblesort();
                break;
            case "shaker":
                resultado = vetorItens.shakersort();
                break;
            case "shell":
                resultado = vetorItens.shellSort();
                break;
            case "heap":
                resultado = vetorItens.heapSort();
                break;
            case "quick":
                resultado = vetorItens.quicksort();
                break;
            default:
                throw new IllegalArgumentException("Algoritmo não encontrado: " + algoritmo);
        }

        return resultado;
    }
}