package br.edu.ifpb.domain.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2021, 08:52:19
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SalaDeBatePapo{
    private List<String> mensagens = new ArrayList<>();
    private Object lock  = new Object();
    public List<String> historico() {
     return Collections.unmodifiableList(
         this.mensagens
     );
    }
    
    public void nova(String msg) { // 0.05ms
        // aqui temos validação...
        synchronized(lock){ //bloco de código
            this.mensagens.add(msg);
        }
        //aqui temos envio de mensagens assincronas..
    }
    
//    public void nova(String msg) { // 0.05ms
//        // aqui temos validação...
//        synchronized(this.mensagens){ //bloco de código
//            this.mensagens.add(msg);
//        }
//        //aqui temos envio de mensagens assincronas..
//    }
    
//    public void nova(String msg) { // 0.05ms
//        // aqui temos validação...
//        synchronized(this){ //bloco de código
//            this.mensagens.add(msg);
//        }
//        //aqui temos envio de mensagens assincronas..
//    }
    
//    public synchronized void nova(String msg) { // 0.05ms
//        // aqui temos validação...
//        this.mensagens.add(msg);
//        //aqui temos envio de mensagens assincronas..
//    }

}
