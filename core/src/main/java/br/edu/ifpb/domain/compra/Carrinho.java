package br.edu.ifpb.domain.compra;

import br.edu.ifpb.domain.Produto;
import java.util.List;

public interface Carrinho {
    public void adicionar(Produto produto);
    public void excluir(Produto produto);
    public List<Produto> produtos();
    public void finalizar();
}
