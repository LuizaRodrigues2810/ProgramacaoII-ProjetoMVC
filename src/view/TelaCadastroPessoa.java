
package view;

import controller.PessoaController;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastroPessoa extends JFrame {

    private JLabel labelNome;
    private JLabel labelEmail;
    private JLabel labelPessoas;
    private JLabel labelTelefone;
    private JLabel labelIdade;
    private JTextField fieldTelefone;
    private JTextField fieldIdade;
    private JTextArea areaDetalhes;
    private JButton buttonRemover;

    private JTextField fieldNome;
    private JTextField fieldEmail;

    private JButton buttonCadastro;

    // Lista de objetos Pessoa
    private JList<Pessoa> listaPessoas;
    private DefaultListModel<Pessoa> modeloLista;
    private JScrollPane scrollLista;

    // Controller
    private PessoaController controller;

    public TelaCadastroPessoa() {
        this.controller = new PessoaController();
        initComponents();
    }

    // Consulta as pessoas cadastradas via Controller
    private void atualizarLista() {

        List<Pessoa> pessoas = controller.consultarPessoas();

        modeloLista.clear();

        for (Pessoa pessoa : pessoas) {
            modeloLista.addElement(pessoa);
        }
    }

    public void initComponents() {

        labelNome = new JLabel("Nome:");
        labelEmail = new JLabel("E-mail:");
        labelTelefone = new JLabel("Telefone:");
        labelIdade = new JLabel("Idade:");
        labelPessoas = new JLabel("Pessoas cadastradas:");

        fieldNome = new JTextField();
        fieldEmail = new JTextField();
        fieldTelefone = new JTextField();
        fieldIdade = new JTextField();

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        buttonCadastro = new JButton("Cadastrar");
        buttonRemover = new JButton("Remover");

        // Inicialização da lista de pessoas
        modeloLista = new DefaultListModel<>();
        listaPessoas = new JList<>(modeloLista);

        // Define como cada Pessoa será exibida na lista
        listaPessoas.setCellRenderer(
                new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus) {

                        super.getListCellRendererComponent(
                                list,
                                value,
                                index,
                                isSelected,
                                cellHasFocus
                        );

                        if (value instanceof Pessoa) {
                            Pessoa pessoa = (Pessoa) value;

                            setText(
                                    pessoa.getNome()
                                            + " - "
                                            + pessoa.getEmail()
                            );
                        }

                        return this;
                    }
                }
        );

        scrollLista = new JScrollPane(listaPessoas);

        this.setTitle("Cadastrar Pessoa");
        this.setSize(500, 620);
        this.setResizable(false);
        this.setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE
        );

        this.getContentPane().setLayout(null);

        labelNome.setBounds(30, 30, 70, 25);
        this.add(labelNome);
        fieldNome.setBounds(100, 30, 340, 25);
        this.add(fieldNome);

        labelEmail.setBounds(30, 70, 70, 25);
        this.add(labelEmail);
        fieldEmail.setBounds(100, 70, 340, 25);
        this.add(fieldEmail);

        labelTelefone.setBounds(30, 110, 70, 25);
        this.add(labelTelefone);
        fieldTelefone.setBounds(100, 110, 340, 25);
        this.add(fieldTelefone);

        labelIdade.setBounds(30, 150, 70, 25);
        this.add(labelIdade);
        fieldIdade.setBounds(100, 150, 340, 25);
        this.add(fieldIdade);

        buttonCadastro.setBounds(100, 190, 120, 30);
        this.add(buttonCadastro);
        buttonRemover.setBounds(230, 190, 120, 30);
        this.add(buttonRemover);

        labelPessoas.setBounds(30, 240, 200, 25);
        this.add(labelPessoas);
        scrollLista.setBounds(30, 270, 410, 130);
        this.add(scrollLista);

        areaDetalhes.setBounds(30, 415, 410, 100);
        this.add(areaDetalhes);

        buttonCadastro.addActionListener(e -> {

            String nome = fieldNome.getText().trim();
            String email = fieldEmail.getText().trim();
            String telefone = fieldTelefone.getText().trim();
            String idadeTexto = fieldIdade.getText().trim();

            if (nome.isEmpty() || email.isEmpty()
            || telefone.isEmpty() || idadeTexto.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos!"
                );
                return;
            }
            try{
                int idade = Integer.parseInt(idadeTexto);

                //Cadastra a pessoa via controller
                controller.cadastrarPessoa(nome, email, telefone, idade);

                //atualiza lista via controller
                atualizarLista();

                JOptionPane.showMessageDialog(
                        this,
                        "Pessoa cadastrada com sucesso!");

                fieldNome.setText("");
                fieldEmail.setText("");
                fieldTelefone.setText("");
                fieldIdade.setText("");

                fieldNome.requestFocusInWindow();

            }catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(this,
                        "A idade deve ser um número!");
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        });

        buttonRemover.addActionListener(e -> {
            Pessoa selecionada = listaPessoas.getSelectedValue();

            if(selecionada == null){
                JOptionPane.showMessageDialog(this,
                        "Selecione uma pessoa na lista!");
                return;
            }
            controller.removerPessoa(selecionada);
            atualizarLista();
            areaDetalhes.setText("");
        });

        listaPessoas.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            Pessoa p = listaPessoas.getSelectedValue();

            if (p == null) {
                areaDetalhes.setText("");
                return;   // <- o que faltava
            }

            areaDetalhes.setText(
                    " Nome: " + p.getNome()
                            + "\n E-mail: " + p.getEmail()
                            + "\n Telefone: " + p.getTelefone()
                            + "\n Idade: " + p.getIdade()
            );
        });

        atualizarLista();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
