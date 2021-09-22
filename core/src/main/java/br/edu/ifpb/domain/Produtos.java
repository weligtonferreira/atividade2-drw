package br.edu.ifpb.domain;

import java.util.List;

public interface Produtos {

    Produto novo(Produto produto);
    Produto atualiza(Produto produto);
    Produto exclui(int id);
    Produto localizar(int id);
    List<Produto> todos();

}
