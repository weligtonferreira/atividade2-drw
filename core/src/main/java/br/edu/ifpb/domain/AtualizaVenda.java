package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AtualizaVenda {

    @EJB
    private Vendas vendas;
    
    public Venda atualiza(Venda venda){
        // validações...
        Objects.requireNonNull(venda,"O cliente não pode ser nulo");
        return vendas.atualiza(venda);
    }
        
}
