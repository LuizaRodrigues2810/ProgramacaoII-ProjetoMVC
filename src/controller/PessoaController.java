package controller;

import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    private List<Pessoa> pessoas = new ArrayList<>();

    public void cadastrarPessoa(String nome, String email){
        Pessoa pessoa = new Pessoa(nome, email);
        pessoas.add(pessoa);
    }
    public List<Pessoa> consultarPessoas(){
        return new ArrayList<>(pessoas);
    }
}
