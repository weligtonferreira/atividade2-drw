package br.edu.ifpb.domain;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/07/2021, 08:57:52
 */
@Stateless
public class ListaDeProdutos {

    @EJB
    private Produtos produtos;
    
    public List<Produto> todosOsProdutos(){
        //cache
        return this.produtos.todos();
    }
}
