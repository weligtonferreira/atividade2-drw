package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoVenda;
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
    private final List<ProdutoVenda> produtos = new LinkedList<>();
    @Override
    public void adicionar(Produto produto) {
        ProdutoVenda produtoVendaEncontrado = this.produtos
                .stream()
                .filter(pv -> pv.getProduto().equals(produto))
                .findFirst()
                .orElse(new ProdutoVenda());
        if (!produtoVendaEncontrado.equals(new ProdutoVenda())) {
            produtoVendaEncontrado.setQuantidade(produtoVendaEncontrado.getQuantidade() + 1);
        } else {
            this.produtos.add(new ProdutoVenda(produto, 1));
        }
    }
    
    @Override
    public void excluir(ProdutoVenda produto) {
        ProdutoVenda produtoVendaEncontrado = this.produtos
                .stream()
                .filter(pv -> pv.equals(produto))
                .findFirst()
                .orElse(new ProdutoVenda());
        if (!produtoVendaEncontrado.equals(new ProdutoVenda())) {
            if (produtoVendaEncontrado.getQuantidade() > 1) {
                produtoVendaEncontrado.setQuantidade(produtoVendaEncontrado.getQuantidade() - 1);
            } else {
                this.produtos.remove(produtoVendaEncontrado);
            }
        }
    }
    @Override
    public List<ProdutoVenda> produtos() {
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
