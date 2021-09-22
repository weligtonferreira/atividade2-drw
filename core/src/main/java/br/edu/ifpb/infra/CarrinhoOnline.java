package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.compra.Carrinho;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


//acesso: remote
//acesso: local
//acesso: local-no-interface (local mas sem a necessidade de uma interface)
@Stateful
public class CarrinhoOnline implements Carrinho {
    //carrinho.produtos().add("novo")
    private final List<Produto> produtos = new LinkedList<>();
    @Override
    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }
    
    @Override
    public void excluir(Produto produto) {
        this.produtos.remove(produto);
    }
    @Override
    public List<Produto> produtos() {
        return Collections.unmodifiableList(
                this.produtos
        );
    }
    @Remove
    @Override
    public void finalizar() {
        System.out.println("------ produtos ------");
        this.produtos.forEach(System.out::println);
        //atualizando o estoque
        //confirmando o pedido
        //enviando e-maik
    }
}
