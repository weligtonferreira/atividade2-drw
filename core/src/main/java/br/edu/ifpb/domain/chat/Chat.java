package br.edu.ifpb.domain.chat;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2021, 08:14:08
 */
public interface Chat {
    public void nova(String msg);
    
    public List<String> historico();

}
