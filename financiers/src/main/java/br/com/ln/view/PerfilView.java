/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.view;

import br.com.ln.comum.BeanVar;
import br.com.ln.comum.Historico;
import br.com.ln.comum.JsfHelper;
import br.com.ln.comum.VarComuns;
import br.com.ln.dao.HistoricoDao;
import br.com.ln.dao.PerfilDao;
import br.com.ln.entity.LnModulo;
import br.com.ln.entity.LnPerfil;
import br.com.ln.entity.LnPerfilacesso;
import br.com.ln.entity.LnPerfilacessoPK;
import br.com.ln.funcao.PerfilFuncoes;
import br.com.ln.tipos.TipoFuncao;
import br.com.ln.financiers.TratamentoEspecial;
import br.com.ln.dao.ModuloDao;
import br.com.ln.entity.LnHistorico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marcos Naves
 */
@SessionScoped
@ManagedBean(name = "perfilView")
public class PerfilView implements Serializable {

    private List<LnPerfil> listPerfil;
    private LnPerfil lnPerfil;
    private LnPerfilacesso lnPerfilacesso;
    private List<LnModulo> listModulo;
    private String perfilDescricao;
    private boolean bAtivo;
    private boolean bAlteraSenha;
    private Integer modInCodigo;
    private boolean bIncluirAcesso;
    private boolean bAlterarAcesso;
    private boolean bExcluirAcesso;
    private boolean bPesquisarAcesso;
    private List<LnPerfilacesso> listPerfilacesso;
    private String mensagem;
    private final PerfilFuncoes perfilFuncoes;
    private final TratamentoEspecial tratativa;
    private final BeanVar beanVar;
    private final HistoricoView historico;

    private final FacesContext context = FacesContext.getCurrentInstance();
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
    Logger logger = Logger.getLogger(PerfilFuncoes.class);

    public PerfilView() {
        listPerfil = PerfilDao.grabListObject(LnPerfil.class);
        listaPerfilAcesso();
        listModulo = ModuloDao.grabListModuloAtivo('S');
        perfilFuncoes = new PerfilFuncoes();
        tratativa = new TratamentoEspecial();
        listPerfilacesso = new ArrayList<>();
        beanVar = (BeanVar) JsfHelper.getSessionAttribute("beanVar");
        historico = (HistoricoView) JsfHelper.getSessionAttribute("historicoView");
    }

    public List<LnPerfil> getListPerfil() {
        return listPerfil;
    }

    public void setListPerfil(List<LnPerfil> listPerfil) {
        this.listPerfil = listPerfil;
    }

    public LnPerfil getLnPerfil() {
        return lnPerfil;
    }

    public void setLnPerfil(LnPerfil lnPerfil) {
        this.lnPerfil = lnPerfil;
    }

    public LnPerfilacesso getLnPerfilacesso() {
        return lnPerfilacesso;
    }

    public void setLnPerfilacesso(LnPerfilacesso lnPerfilacesso) {
        this.lnPerfilacesso = lnPerfilacesso;
    }

    public List<LnModulo> getListModulo() {
        return listModulo;
    }

    public void setListModulo(List<LnModulo> listModulo) {
        this.listModulo = listModulo;
    }

    public String getPerfilDescricao() {
        return perfilDescricao;
    }

    public void setPerfilDescricao(String perfilDescricao) {
        this.perfilDescricao = perfilDescricao;
    }

    public boolean isbAtivo() {
        return bAtivo;
    }

    public void setbAtivo(boolean bAtivo) {
        this.bAtivo = bAtivo;
    }

    public boolean isbAlteraSenha() {
        return bAlteraSenha;
    }

    public void setbAlteraSenha(boolean bAlteraSenha) {
        this.bAlteraSenha = bAlteraSenha;
    }

    public Integer getModInCodigo() {
        return modInCodigo;
    }

    public void setModInCodigo(Integer modInCodigo) {
        this.modInCodigo = modInCodigo;
    }

    public boolean isbIncluirAcesso() {
        return bIncluirAcesso;
    }

    public void setbIncluirAcesso(boolean bIncluirAcesso) {
        this.bIncluirAcesso = bIncluirAcesso;
    }

    public boolean isbAlterarAcesso() {
        return bAlterarAcesso;
    }

    public void setbAlterarAcesso(boolean bAlterarAcesso) {
        this.bAlterarAcesso = bAlterarAcesso;
    }

    public boolean isbExcluirAcesso() {
        return bExcluirAcesso;
    }

    public void setbExcluirAcesso(boolean bExcluirAcesso) {
        this.bExcluirAcesso = bExcluirAcesso;
    }

    public boolean isbPesquisarAcesso() {
        return bPesquisarAcesso;
    }

    public void setbPesquisarAcesso(boolean bPesquisarAcesso) {
        this.bPesquisarAcesso = bPesquisarAcesso;
    }

    public List<LnPerfilacesso> getListPerfilacesso() {
        return listPerfilacesso;
    }

    public void setListPerfilacesso(List<LnPerfilacesso> listPerfilacesso) {
        this.listPerfilacesso = listPerfilacesso;
    }

    private void listaPerfilAcesso() {
        listPerfil.stream().forEach((perfil) -> {
            perfil.setListPerfilAcesso(PerfilDao.grabPerfilAcessoperInCodigo(perfil.getPerInCodigo()));
        });
    }

    public String buscaDescModulo(Integer modInCodigo) {
        return VarComuns.mapModulo.get(modInCodigo);
    }

    public void btIncluirPerfil() {
        if (VarComuns.lnPerfilacesso.getPacChIncluir().equals('S')) {
            lnPerfil = new LnPerfil();
            lnPerfil.setTipoFuncao(TipoFuncao.Incluir);
            lnPerfilacesso = new LnPerfilacesso();
            lnPerfilacesso.setTipoFuncao(TipoFuncao.Incluir);
            clearVar();
            listPerfilacesso.clear();
            beanVar.setTelaDialog("WEB-INF/templates/dialog/dialogperfil.xhtml");
            beanVar.setTituloDialog("ln.texto.cadastrodeperfil");
            RequestContext.getCurrentInstance().execute("PF('dialog').show()");
        } else {
            mensagem = bundle.getString("ln.mb.frase.permissao");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.perfil"), mensagem));
        }
    }

    public void btAlterarPerfil() {
        if (VarComuns.lnPerfilacesso.getPacChAlterar().equals('S')) {
            if (lnPerfil != null) {
                dataLoadPerfil();
                lnPerfil.setTipoFuncao(TipoFuncao.Alterar);
                beanVar.setTelaDialog("WEB-INF/templates/dialog/dialogperfil.xhtml");
                beanVar.setTituloDialog("ln.texto.cadastrodeperfil");
                RequestContext.getCurrentInstance().execute("PF('dialog').show()");
            } else {
                mensagem = bundle.getString("ln.mb.frase.selecionaregistro");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            }
        } else {
            mensagem = bundle.getString("ln.mb.frase.permissao");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.perfil"), mensagem));
        }
    }

    public void btExcluirPerfil() {
        if (VarComuns.lnPerfilacesso.getPacChExcluir().equals('S')) {
            if (lnPerfil != null) {
                lnPerfil.setTipoFuncao(TipoFuncao.Excluir);
                if (perfilFuncoes.perfil(lnPerfil)) {
                    mensagem = perfilFuncoes.mensagem;
                    listPerfil = PerfilDao.grabListObject(LnPerfil.class);
                    listaPerfilAcesso();
                    lnPerfil = new LnPerfil();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            bundle.getString("ln.mb.titulo.perfil"), mensagem));
                } else {
                    mensagem = perfilFuncoes.mensagem;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            bundle.getString("ln.mb.titulo.perfil"), mensagem));
                }
            } else {
                mensagem = bundle.getString("ln.mb.frase.selecionaregistro");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            }
        } else {
            mensagem = bundle.getString("ln.mb.frase.permissao");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.perfil"), mensagem));
        }
    }

    public void btIncluirPerfilAcesso() {
        dataLoadVarPerfil();
        dataLoadVarPerfilAcesso();

        if (!lnPerfil.getListPerfilAcesso().contains(lnPerfilacesso)) {
            lnPerfilacesso.setTipoFuncao(TipoFuncao.Incluir);
            listPerfilacesso.add(lnPerfilacesso);
        } else {
            mensagem = bundle.getString("ln.mb.frase.modulocadastrado");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.perfil"), mensagem));
        }
    }

    public void btGravarPerfilAcesso() {
        if (lnPerfil.getTipoFuncao().equals(TipoFuncao.Incluir)) {
            for (LnPerfilacesso lnPerfilacessog : listPerfilacesso) {
                lnPerfil.getListPerfilAcesso().add(lnPerfilacessog);
            }
            if (perfilFuncoes.perfil(lnPerfil)) {
                mensagem = perfilFuncoes.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            } else {
                mensagem = perfilFuncoes.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            }
        } else if (lnPerfil.getTipoFuncao().equals(TipoFuncao.Alterar)) {
            dataLoadVarPerfil();
            if (perfilFuncoes.perfil(lnPerfil)) {
                mensagem = perfilFuncoes.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            } else {
                mensagem = perfilFuncoes.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("ln.mb.titulo.perfil"), mensagem));
            }
        }

        listPerfil = PerfilDao.grabListObject(LnPerfil.class);
        listaPerfilAcesso();
        lnPerfil = new LnPerfil();
        RequestContext.getCurrentInstance().execute("PF('dialog').hide()");
    }

    public void btFecharPerfilAcesso() {
        RequestContext.getCurrentInstance().execute("PF('dialog').hide()");

    }

    public void btEditaPerfilAcesso() {
        if (lnPerfil.getListPerfilAcesso().size() > 1) {
            dataLoadPerfil();
            listPerfilacesso.remove(lnPerfilacesso);
            lnPerfil.getListPerfilAcesso().remove(lnPerfilacesso);
            lnPerfilacesso.setTipoFuncao(TipoFuncao.Excluir);
            perfilFuncoes.perfilAcesso(lnPerfilacesso);
        } else {
            mensagem = bundle.getString("ln.mb.frase.perfilnaoexcluido");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.perfil"), mensagem));
        }
    }

    private void dataLoadVarPerfilAcesso() {
        LnPerfilacessoPK lnPerfilacessoPK;

        if (lnPerfil.getTipoFuncao().equals(TipoFuncao.Incluir)) {
            lnPerfilacessoPK = new LnPerfilacessoPK(0, modInCodigo);
        } else {
            lnPerfilacessoPK = new LnPerfilacessoPK(lnPerfil.getPerInCodigo(), modInCodigo);
        }

        lnPerfilacesso = new LnPerfilacesso();

        lnPerfilacesso.setLnPerfilacessoPK(lnPerfilacessoPK);
        lnPerfilacesso.setPacChIncluir(tratativa.tratamentoTextoCharacter(bIncluirAcesso));
        lnPerfilacesso.setPacChAlterar(tratativa.tratamentoTextoCharacter(bAlterarAcesso));
        lnPerfilacesso.setPacChExcluir(tratativa.tratamentoTextoCharacter(bExcluirAcesso));
        lnPerfilacesso.setPacChPesquisar(tratativa.tratamentoTextoCharacter(bPesquisarAcesso));
    }

    private void dataLoadVarPerfil() {
        lnPerfil.setPerStDescricao(perfilDescricao);
        lnPerfil.setPerChAtivo(tratativa.tratamentoTextoCharacter(bAtivo));
        lnPerfil.setPerChAlterasenha(tratativa.tratamentoTextoCharacter(bAlteraSenha));

    }

    private void dataLoadPerfil() {
        perfilDescricao = lnPerfil.getPerStDescricao();
        bAtivo = tratativa.tratamentoTextoBoolean(lnPerfil.getPerChAtivo());
        bAlteraSenha = tratativa.tratamentoTextoBoolean(lnPerfil.getPerChAlterasenha());
        listPerfilacesso = lnPerfil.getListPerfilAcesso();
    }

    private void clearVar() {
        perfilDescricao = "";
        bAtivo = false;
        bAlteraSenha = false;
        modInCodigo = null;
        bIncluirAcesso = false;
        bAlterarAcesso = false;
        bExcluirAcesso = false;
        bPesquisarAcesso = false;
    }

    public void btVoltar() {
        beanVar.setNovaTela("WEB-INF/templates/principal.xhtml");
    }

    public void btHistorico() {
        List<LnHistorico> listaHistorico = HistoricoDao.grabListHistorico(VarComuns.lnPerfil.getPerInCodigo());
        historico.setListHistorico(listaHistorico);
        RequestContext.getCurrentInstance().execute("PF('historico').show()");
    }

}
