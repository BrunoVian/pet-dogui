package br.unipar.pet.dogui.model;

import java.util.ArrayList;

public class Pessoa {
    
    private int id;
    private String nome;
    private ArrayList<Endereco> listaEnderecos;
    private String nrTelefone;
    private String email;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, ArrayList<Endereco> listaEnderecos, String nrTelefone, String email) {
        this.id = id;
        this.nome = nome;
        this.listaEnderecos = listaEnderecos;
        this.nrTelefone = nrTelefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Endereco> getListaEnderecos() {
        return listaEnderecos;
    }

    public void setListaEnderecos(ArrayList<Endereco> listaEnderecos) {
        this.listaEnderecos = listaEnderecos;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", listaEnderecos=" + listaEnderecos + ", nrTelefone=" + nrTelefone + ", email=" + email + '}';
    }
    
}
