package controller;

import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    private List<Pessoa> pessoas = new ArrayList<>();

    public void cadastrarPessoa(String nome, String email, String telefone, int idade){
        Pessoa pessoa = new Pessoa(nome, email, telefone, idade);
        if(!email.contains("@")){
            throw new IllegalArgumentException("E-mail inválido!");
        }
        if(idade < 0 || idade > 120){
            throw new IllegalArgumentException("Idade inválida!");
        }
        for(Pessoa p : pessoas){
            if(p.getEmail().equalsIgnoreCase(email)){
                throw new IllegalArgumentException("Email já cadastrado");
            }
        }
        pessoas.add(new Pessoa(nome, email, telefone, idade));
    }
    public void removerPessoa(Pessoa pessoa){
        pessoas.remove(pessoa);
    }
    public List<Pessoa> consultarPessoas(){
        return new ArrayList<>(pessoas);
    }
}
