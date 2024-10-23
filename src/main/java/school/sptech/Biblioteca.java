package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null ||
                livro.getTitulo() == null || livro.getTitulo().isBlank() ||
                livro.getAutor() == null || livro.getAutor().isBlank() ||
                livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException("ERRO: há algum argumento inválido.");
        }
        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo){
        if ( titulo == null || titulo.isBlank() ){
            throw new ArgumentoInvalidoException("ERRO: o titulo não pode ser nulo, nem ter apenas espaços em branco.");
        }
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)){
                livros.remove(i);
                return;
            }
        }
        throw new LivroNaoEncontradoException("ERRO: livro não encontrado.");
    }

    public Livro buscarLivroPorTitulo(String titulo){
        if ( titulo == null || titulo.isBlank() ){
            throw new ArgumentoInvalidoException("ERRO: o titulo não pode ser nulo, nem ter apenas espaços em branco.");
        }
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("ERRO: livro não encontrado.");
    }

    public Integer contarLivros(){
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano){
        List<Livro> livrosAteAno = new ArrayList<>();
        for (Livro livro : livros) {
            Integer anoPublicacao = livro.getDataPublicacao().getYear();
            if (anoPublicacao <= ano){
                livrosAteAno.add(livro);
            }
        }
        return livrosAteAno;
    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> listaCincoLivros = new ArrayList<>();

        if (livros.isEmpty()) {
            return listaCincoLivros;
        }

        for (int i = 0; i < livros.size(); i++) {
            Livro livroAtual = livros.get(i);

            listaCincoLivros.add(livroAtual);
        }

        listaCincoLivros.sort((livro1, livro2) -> Double.compare(livro2.calcularMediaAvaliacoes(), livro1.calcularMediaAvaliacoes()));

        if (listaCincoLivros.size() > 5) {
            return listaCincoLivros.subList(0, 5);
        }
        return listaCincoLivros;
    }
}
