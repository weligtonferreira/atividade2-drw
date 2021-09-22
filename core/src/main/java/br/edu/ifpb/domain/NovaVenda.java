package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovaVenda {

    @EJB
    private Vendas vendas;
    
    public Venda nova(Venda venda){
        // validações...
        Objects.requireNonNull(venda,"O produto não pode ser nulo");
        return vendas.nova(venda);
    }
        
}
