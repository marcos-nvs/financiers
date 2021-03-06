/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.funcao;

import br.com.ln.comum.Historico;
import br.com.ln.dao.FavorecidoDao;
import br.com.ln.entity.LnFavorecido;
import br.com.ln.entity.LnTipofavorecido;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcos Naves
 */
public class FavorecidoFuncoes implements Serializable {

    public String mensagem;
    private Historico historico;

    private final FacesContext context = FacesContext.getCurrentInstance();
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());

    public List<LnTipofavorecido> grabListaTipoFavorecido() {
        return FavorecidoDao.grabListObject(LnTipofavorecido.class);
    }

    public boolean favorecido(LnFavorecido lnFavorecido) {

        historico = new Historico();

        switch (lnFavorecido.getTipoFuncao()) {
            case Incluir:
                return incluirFavorecido(lnFavorecido);
            case Alterar:
                return alterarFavorecido(lnFavorecido);
            case Excluir:
                return excluirFavorecido(lnFavorecido);
            default:
                return false;
        }
    }

    private boolean incluirFavorecido(LnFavorecido lnFavorecido) {
        if (lnFavorecido != null) {
            FavorecidoDao.saveObject(lnFavorecido);
            historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.inclusaofavorecido") + " " + lnFavorecido.getFavStDescricao());
            mensagem = bundle.getString("ln.mb.texto.sucesso");
            return true;
        } else {
            mensagem = bundle.getString("ln.mb.frase.problema");
            return false;
        }
    }

    private boolean alterarFavorecido(LnFavorecido lnFavorecido) {
        if (lnFavorecido != null) {
            FavorecidoDao.saveOrUpdateObject(lnFavorecido);
            historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.alteracaofavorecido") + " " + lnFavorecido.getFavStDescricao());
            mensagem = bundle.getString("ln.mb.texto.sucesso");
            return true;
        } else {
            mensagem = bundle.getString("ln.mb.frase.problema");
            return false;
        }
    }

    private boolean excluirFavorecido(LnFavorecido lnFavorecido) {
        if (lnFavorecido != null) {
            FavorecidoDao.deleteObject(lnFavorecido);
            historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.exclusaofavorecido") + " " + lnFavorecido.getFavStDescricao());
            mensagem = bundle.getString("ln.mb.texto.sucesso");
            return true;
        } else {
            mensagem = bundle.getString("ln.mb.frase.problema");
            return false;
        }
    }

    public List<LnFavorecido> grabListaFavorecido() {
        return FavorecidoDao.grabListaFavorecido();
    }
    
    public List<LnFavorecido> grabListaFavorecidoAtivo(){
        return FavorecidoDao.grabListaFavorecidoAtivo();
    }

    public boolean verificaInformacoes(LnFavorecido lnFavorecido) {
        boolean validado = true;
        mensagem = bundle.getString("ln.mb.frase.preenchercampos") + " ";

        if (lnFavorecido.getFavStDescricao() == null || lnFavorecido.getFavStDescricao().equals("")) {
            mensagem = mensagem + bundle.getString("ln.texto.nome") + "";
            validado = false;
        }

        return validado;
    }

    public boolean verificaExclusaoFavorecido(LnFavorecido lnFavorecido) {
            return true;
    }

    public LnFavorecido grabFavorecido(Integer idFavorecido) {
        return FavorecidoDao.grabFavorecidoCodigo(idFavorecido);
    }

}
