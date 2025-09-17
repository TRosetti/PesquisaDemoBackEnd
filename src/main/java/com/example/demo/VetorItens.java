package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class VetorItens {
    Item[] vetor;
    int nElem;
    private long comparacoes;
    private long movimentacoes;

    public VetorItens(int capacidade) {
        vetor = new Item[capacidade];
        nElem = 0;
        this.comparacoes = 0;
        this.movimentacoes = 0;
    }

    // Método para resetar os contadores
    public void esvaziarContadores() {
        this.comparacoes = 0;
        this.movimentacoes = 0;
    }

    public void esvaziar() {
        this.nElem = 0;
    }

    public void inserir(Item item) {
        vetor[nElem++] = item;
    }

    public void exibir() {
        for (int i = 0; i < nElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    // Seleção direta
    public ResultadoOrdenacao seleçãoDireta() {
        esvaziarContadores();
        long startTime = System.currentTimeMillis();

        int i, j, minimo;
        Item temp;
        
        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }


        for (i = 0; i < this.nElem - 1; i++) {
            minimo = i;            
            for (j = i + 1; j < this.nElem ; j++){
                this.comparacoes++;
                if (this.vetor[j].getChave() < this.vetor[minimo].getChave())
                    minimo = j;
            }
            temp = this.vetor[minimo];
            this.vetor[minimo] = this.vetor[i];
            this.vetor[i] = temp;
            movimentacoes ++;
        }

        long endTime = System.currentTimeMillis();

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    // Heap
    public ResultadoOrdenacao heapSort() {
        this.esvaziarContadores();
        long startTime = System.currentTimeMillis();

        int dir = nElem - 1;
        int esq = (dir - 1) / 2;
        Item temp;

        
        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }


        while (esq >= 0)
            refazHeap(esq--, this.nElem - 1);
        while (dir > 0) {
            temp = this.vetor[0];
            this.vetor[0] = this.vetor[dir];
            this.vetor[dir--] = temp;
            refazHeap(0, dir);
            this.movimentacoes += 1;
        }

        long endTime = System.currentTimeMillis();

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    private void refazHeap(int esq, int dir) {
        int i = esq;
        int MaiorFolha = 2 * i + 1;
        Item raiz = this.vetor[i];
        boolean heap = false;

        while ((MaiorFolha <= dir) && (!heap)) {
            // this.comparacoes++;

            if (MaiorFolha < dir) {
                this.comparacoes++;

                if (this.vetor[MaiorFolha].getChave() < this.vetor[MaiorFolha + 1].getChave()) {
                    // this.comparacoes++;
                    MaiorFolha++;
                }
            }

            this.comparacoes++;

            if (raiz.getChave() < this.vetor[MaiorFolha].getChave()) {
                // this.comparacoes++;

                this.vetor[i] = this.vetor[MaiorFolha];
                this.movimentacoes++;

                i = MaiorFolha;
                MaiorFolha = 2 * i + 1;
            } else{
                heap = true;
            }
               
        }
        this.vetor[i] = raiz;
        // this.movimentacoes++;
    }

    // Inserção direta
    public ResultadoOrdenacao inserçãoDireta() {
        this.esvaziarContadores();
        long startTime = System.currentTimeMillis();

        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }

        int i, j;
        Item temp;
        for (i = 1; i < this.nElem; i++) {
            temp = this.vetor[i];
            j = i - 1;
            // this.movimentacoes++;
            while ((j >= 0) && (this.vetor[j].getChave() > temp.getChave())) {
                this.comparacoes++;
                this.vetor[j + 1] = this.vetor[j--];
                this.movimentacoes++;
            }
            this.comparacoes++;
            this.vetor[j + 1] = temp;
            // this.movimentacoes++;
        }   

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        long endTime = System.currentTimeMillis();
        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    // Shell Sort
    public ResultadoOrdenacao shellSort() {

        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }
        this.esvaziarContadores();
        long startTime = System.currentTimeMillis();
        int i, j, h;
        Item temp;
        h = 1;
        do {
            h = 3 * h + 1;
        } while (h < this.nElem);
        do {
            h = h / 3;
            for (i = h; i < this.nElem; i++) {
                temp = this.vetor[i];
                this.comparacoes++;
                j = i;
                while (this.vetor[j - h].getChave() > temp.getChave()) {
                    this.movimentacoes++;
                    this.vetor[j] = this.vetor[j - h];
                    this.comparacoes++;
                    j -= h;
                    if (j < h) {                        
                        break;
                    }
                }                
                this.vetor[j] = temp;
            }
        } while (h != 1);

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        long endTime = System.currentTimeMillis();
        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    // Bubble Sort
    public ResultadoOrdenacao bubblesort() {
        this.esvaziarContadores();
        long startTime = System.currentTimeMillis();

        
        int n, i, j;
        Item temp;
        n = this.nElem - 1;

        
        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }

        do {
            i = 0;
            
            for (j = 0; j < n; j++) {
                this.comparacoes++;
                if (this.vetor[j].getChave() > this.vetor[j + 1].getChave()) {
                    temp = this.vetor[j];
                    this.vetor[j] = this.vetor[j + 1];
                    this.vetor[j + 1] = temp;
                    this.movimentacoes += 3; 
                    i = j;
                }
            }
            n = i;
        } while (n >= 1);

        long endTime = System.currentTimeMillis();

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    // ShakerSort
    public ResultadoOrdenacao shakersort() {
        this.esvaziarContadores();
        long startTime = System.currentTimeMillis();

        int esq, dir, i, j;
        Item temp;
        esq = 1;
        dir = this.nElem - 1;
        j = dir;

        
        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }

        do {
            for (i = dir; i >= esq; i--) {
                this.comparacoes++;
                if (this.vetor[i - 1].getChave() > this.vetor[i].getChave()) {
                    temp = this.vetor[i];
                    this.vetor[i] = this.vetor[i - 1];
                    this.vetor[i - 1] = temp;
                    this.movimentacoes += 3;
                    j = i;
                }
            }
            esq = j + 1;
            for (i = esq; i <= dir; i++) {
                this.comparacoes++;
                if (this.vetor[i - 1].getChave() > this.vetor[i].getChave()) {
                    temp = this.vetor[i];
                    this.vetor[i] = this.vetor[i - 1];
                    this.vetor[i - 1] = temp;
                    this.movimentacoes += 3;
                    j = i;
                }
            }
            dir = j - 1;
        } while (esq <= dir);

        long endTime = System.currentTimeMillis();

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    // QuickSort
    public ResultadoOrdenacao quicksort() {
        this.esvaziarContadores();

        
        List<Integer> vetorOriginal = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOriginal.add(item.getChave());
        }

        long startTime = System.currentTimeMillis();
        ordena(0, this.nElem - 1);
        long endTime = System.currentTimeMillis();

        List<Integer> vetorOrdenado = new ArrayList<>();
        for (Item item : this.vetor) {
            vetorOrdenado.add(item.getChave());
        }

        return new ResultadoOrdenacao(this.comparacoes, this.movimentacoes, endTime - startTime, vetorOriginal, vetorOrdenado);
    }

    private void ordena(int esq, int dir) {
        int pivo, i = esq, j = dir;
        Item temp;
        pivo = this.vetor[(i + j) / 2].getChave();
        do {
            while (this.vetor[i].getChave() < pivo) {
                this.comparacoes++;
                i++;
            }
            this.comparacoes++;
            while (this.vetor[j].getChave() > pivo) {
                this.comparacoes++;
                j--;
            }
            this.comparacoes++;
            if (i <= j) {
                temp = this.vetor[i];
                this.vetor[i] = this.vetor[j];
                this.vetor[j] = temp;
                this.movimentacoes += 1;
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j) {
            ordena(esq, j);
        }
        if (dir > i) {
            ordena(i, dir);
        }
    }
}
