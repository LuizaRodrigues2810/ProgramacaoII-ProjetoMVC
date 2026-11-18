package model;

public class Pessoa {
    private String nome;
    private String email;
    private String telefone;
    private int idade;

    public Pessoa(String nome, String email, String telefone, int idade){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public String getTelefone(){
        return telefone;
    }
    public int getIdade(){
        return idade;
    }
}
