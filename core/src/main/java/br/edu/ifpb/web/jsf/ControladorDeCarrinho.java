package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.compra.Carrinho;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeCarrinho implements Serializable {

    @Inject
    private Carrinho carrinho; //session-key: 90ac0300141f-ffffffffe7de3f3d-0
    private String produto;

    public String novo(){
        this.carrinho.adicionar(this.produto);
        return null;
    }
    public String concluir(){
        this.carrinho.finalizar();
        novaInstanciaDoCarrinho();
        return null;
//        logout();
//        return "index.xhtml?faces-redirect=true";
    }

    private void novaInstanciaDoCarrinho() {
        this.carrinho = CDI.current()
                .select(Carrinho.class)
                .get();
    }

    private void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        session.invalidate();
    }

    public List<String> listar(){
        return this.carrinho.produtos();
    }

    public void setProduto(String produto){
        this.produto =produto;
    }
    public String getProduto(){
        return this.produto;
    }
    
    

}
