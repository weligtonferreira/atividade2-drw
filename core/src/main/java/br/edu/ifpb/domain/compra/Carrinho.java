package br.edu.ifpb.domain.compra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoVenda;
import java.util.List;

public interface Carrinho {
    public void adicionar(Produto produto);
    public void excluir(ProdutoVenda produto);
    public List<ProdutoVenda> produtos();
    public void finalizar();
}
