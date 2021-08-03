package br.edu.ifpb.domain.compra;

import java.util.List;

public interface Carrinho {
    public void adicionar(String produto);
    public List<String> produtos();
    public void finalizar();
}
