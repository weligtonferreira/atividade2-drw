package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AtualizaProduto {

    @EJB
    private Produtos produtos;
    
    public Produto atualiza(Produto produto){
        // validações...
        Objects.requireNonNull(produto,"O cliente não pode ser nulo");
        return produtos.atualiza(produto);
    }
        
}
