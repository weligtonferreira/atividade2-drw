package br.edu.ifpb.domain.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2021, 08:15:31
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
//@Lock(LockType.READ)
@Local(Chat.class)
//@Remote(Chat.class)
public class ChatOnline implements Chat{

    private List<String> mensagens = new ArrayList<>();
    
    @Override
    @Lock(LockType.WRITE)
    public void nova(String msg) {
        // alterando o estado dos atributos do objeto.
        this.mensagens.add(msg);
    }

    @Override
    @Lock(LockType.READ)
    public List<String> historico() {
        //não alteramos o estado do objeto
        return Collections.unmodifiableList(
             this.mensagens
        );
    }

}


// CQRS