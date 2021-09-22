package br.edu.ifpb.domain;

import java.util.List;

public interface Vendas {

    Venda nova(Venda venda);
    Venda atualiza(Venda venda);
    Venda exclui(int id);
    Venda localizar(int id);
    List<Venda> todos();

}
