package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.chat.Chat;
import br.edu.ifpb.domain.chat.SalaDeBatePapo;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2021, 08:20:14
 */
@Named
@RequestScoped
public class ControladorDeChatOnline {

    private String mensagem;
    @Inject
    private SalaDeBatePapo chat;

    public String novaMensagem(){
        this.chat.nova(this.mensagem);
        this.mensagem = "";
        return null;
    }
    public List<String> todasAsMensagens(){
        return this.chat.historico();
    }
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}