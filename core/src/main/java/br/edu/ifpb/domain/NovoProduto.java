package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovoProduto {

    @EJB
    private Produtos produtos;
    
    public Produto novo(Produto produto){
        // validações...
        Objects.requireNonNull(produto,"O produto não pode ser nulo");
        return produtos.novo(produto);
    }
        
}
