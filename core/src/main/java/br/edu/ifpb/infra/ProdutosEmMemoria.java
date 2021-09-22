package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Produtos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;

@Stateless
public class ProdutosEmMemoria implements Produtos {

    private List<Produto> produtos = new ArrayList<>();

    @Override
    public Produto novo(Produto produto) {
        this.produtos.add(produto);
        return produto;
    }

    @Override
    public List<Produto> todos() {
        return Collections
            .unmodifiableList(
                this.produtos
            );
    }

    @Override
    public Produto localizar(int id) {
        return this.produtos
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(new Produto());
    }
    
    @Override
    public List<Produto> localizarPorDescricao(String descricao) {
        return this.produtos
                .stream()
                .filter(p -> p.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Produto atualiza(Produto produto) {
        this.produtos.removeIf(c -> c.getId() == produto.getId());
        this.produtos.add(produto);
        return produto;
    }

    @Override
    public Produto exclui(int id) {
        this.produtos.removeIf(c -> c.getId() == id);
        return new Produto();
    }
}
