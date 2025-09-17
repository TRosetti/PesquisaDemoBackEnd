package com.example.demo;
public class Item {
    private int chave;

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }


    public Item(int chave) {
        this.chave = chave;
    }

    @Override
    public String toString() {
        return "Item [chave=" + chave + "]";
    }    

}