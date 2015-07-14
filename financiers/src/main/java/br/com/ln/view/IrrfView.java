/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.view;

import br.com.ln.comum.VarComuns;
import br.com.ln.entity.LnTabela;
import br.com.ln.entity.LnTabelaItem;
import br.com.ln.financiers.IrrfFuncoes;
import br.com.ln.financiers.Tabela;
import br.com.ln.financiers.TabelaItem;
import br.com.ln.financiers.TipoFuncao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marcos Naves
 */
@SessionScoped
@ManagedBean(name = "IrrfView")
public class IrrfView implements Serializable {

    private Integer idCodigo;
    private Integer codigoTabela;
    private String nomeTabela;
    private Date dataInicial;
    private Date dataFinal;
    private Integer codigoTabItem;
    private Double valorInicial;
    private Double valorFinal;
    private Double valorDependente;
    private Double valorDesconto;
    private Double percentual;
    private Integer qtdDependente;

    private LnTabela lnTabela;
    private Tabela tabela;
    private TabelaItem tabelaItem;
    private List<TabelaItem> listTabelaItem;
    private List<Tabela> listTabela;
    private final IrrfFuncoes irrfFuncao;

    private List<LnTabelaItem> listTabelaItemLoad = new ArrayList<>(100);
    private String valor;

    private String mensagem;

    public IrrfView() {
        tabela = new Tabela();
        tabelaItem = new TabelaItem();
        irrfFuncao = new IrrfFuncoes();
        listTabela = irrfFuncao.buscaTabela();
        listTabelaItem = new ArrayList<>();
    }

    public Integer getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public Integer getCodigoTabela() {
        return codigoTabela;
    }

    public void setCodigoTabela(Integer codigoTabela) {
        this.codigoTabela = codigoTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getCodigoTabItem() {
        return codigoTabItem;
    }

    public void setCodigoTabItem(Integer codigoTabItem) {
        this.codigoTabItem = codigoTabItem;
    }

    public Double getValorDependente() {
        return valorDependente;
    }

    public void setValorDependente(Double valorDependente) {
        this.valorDependente = valorDependente;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Integer getQtdDependente() {
        return qtdDependente;
    }

    public void setQtdDependente(Integer qtdDependente) {
        this.qtdDependente = qtdDependente;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public List<Tabela> getListTabela() {
        return listTabela;
    }

    public void setListTabela(List<Tabela> listTabela) {
        this.listTabela = listTabela;
    }

    public TabelaItem getTabelaItem() {
        return tabelaItem;
    }

    public void setTabelaItem(TabelaItem tabelaItem) {
        this.tabelaItem = tabelaItem;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<TabelaItem> getListTabelaItem() {
        return listTabelaItem;
    }

    public void setListTabelaItem(List<TabelaItem> listTabelaItem) {
        this.listTabelaItem = listTabelaItem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tabela);
        hash = 47 * hash + Objects.hashCode(this.listTabela);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IrrfView other = (IrrfView) obj;
        if (!Objects.equals(this.tabela, other.tabela)) {
            return false;
        }
        if (!Objects.equals(this.listTabela, other.listTabela)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IrrfView{" + "tabela=" + tabela + ", listTabela=" + listTabela + '}';
    }

    public void btIncluir() {
        if (VarComuns.lnPerfilacesso.getPacChIncluir().equals('S')) {
            tabela = new Tabela();
            tabela.setTipoFuncao(TipoFuncao.Incluir);
            RequestContext.getCurrentInstance().execute("PF('IrrfEdit').show()");
        } else {
            mensagem = "Usuario sem premissao para incluir tabela de IRRF.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
        }
    }

    public void btAlterar() {
        if (VarComuns.lnPerfilacesso.getPacChAlterar().equals('S')) {
            tabela.setTipoFuncao(TipoFuncao.Alterar);
            listTabelaItem = tabela.getListTabelaItem();
            loadTabelaVarDesc();
            RequestContext.getCurrentInstance().execute("PF('IrrfEdit').show()");
        } else {
            mensagem = "Usuario sem permissao para alterar a tabela de IRRF";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
        }
    }

    public void btExcluir() {
        if (VarComuns.lnPerfilacesso.getPacChExcluir().equals('S')){
            tabela.setTipoFuncao(TipoFuncao.Excluir);
            boolean bExcluir = loadLnTabela();
            if (bExcluir){
                bExcluir = irrfFuncao.tabela(lnTabela);
                if (bExcluir) {
                    clearVarTabela();
                    clearVarTabelaItem();
                    mensagem = "Tabela de Imposto incluida com sucesso!!!";
                    listTabela = irrfFuncao.buscaTabela();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
            } else {
                mensagem = irrfFuncao.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tabela IRRF", mensagem));
                }
            }
        } else {
            mensagem = "Usuario sem permissao para excluir a tabela de IRRF";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
        }
    }

    public void btIncluiDetalhe() {
        tabelaItem = new TabelaItem();
        tabelaItem.setCodigoTabItem(irrfFuncao.calcIdTabelaItem());
        tabelaItem.setTipoFuncao(TipoFuncao.Incluir);
        loadVarTabela();
        loadVarTabelaItem();
        if (irrfFuncao.verificaInformacoes(tabela, tabelaItem)) {
            tabelaItem.setTipoFuncao(TipoFuncao.Incluir);
            listTabelaItem.add(tabelaItem);
        } else {
            mensagem = irrfFuncao.mensagem;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
        }
    }

    public void btExcluiDetalhe() {
        if (VarComuns.lnPerfilacesso.getPacChExcluir().equals('S')) {
            if (listTabelaItem.size() > 1) {
                tabelaItem.setTipoFuncao(TipoFuncao.Excluir);
//                listTabelaItem.remove(tabelaItem);
                mensagem = "Registro marcado para exclusao com sucesso";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
            } else {
                mensagem = "E ncessario ter pelo menos um valor na tabela.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
            }
        } else {
            mensagem = "Usuario sem permissao para excluir tabela.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
        }
    }

    public void btSalvar() {
        boolean bGravar = loadLnTabela();
        if (bGravar) {
            bGravar = irrfFuncao.tabela(lnTabela);
            if (bGravar) {
                clearVarTabela();
                clearVarTabelaItem();
                listTabela = irrfFuncao.buscaTabela();
                RequestContext.getCurrentInstance().execute("PF('IrrfEdit').hide()");
                mensagem = "Tabela de Imposto incluida com sucesso!!!";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
            } else {
                mensagem = irrfFuncao.mensagem;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tabela IRRF", mensagem));
            }
        }
    }

    public void btFechar() {
        RequestContext.getCurrentInstance().execute("PF('IrrfEdit').hide()");
    }

    private void loadVarTabela() {
        tabela.setDataFinal(dataFinal);
        tabela.setDataInicial(dataInicial);
        tabela.setNomeTabela(nomeTabela);
    }

    private void loadVarTabelaItem() {
        tabelaItem.setPercentual(percentual);
        tabelaItem.setQtdDependente(qtdDependente);
        tabelaItem.setValorDependente(valorDependente);
        tabelaItem.setValorDesconto(valorDesconto);
        tabelaItem.setValorFinal(valorFinal);
        tabelaItem.setValorInicial(valorInicial);
    }

    private void clearVarTabela() {
        nomeTabela = "";
        dataInicial = null;
        dataFinal = null;
        codigoTabela = null;
    }

    private void clearVarTabelaItem() {
        codigoTabItem = null;
        valorDependente = null;
        valorDesconto = null;
        valorFinal = null;
        valorInicial = null;
        qtdDependente = null;
        percentual = null;
    }

    private void loadTabelaVarDesc() {
        nomeTabela = tabela.getNomeTabela();
        dataInicial = tabela.getDataInicial();
        dataFinal = tabela.getDataFinal();
    }
    
    private boolean loadLnTabela(){
        boolean bGravar = true;
        LnTabelaItem lnTabelaItem;
        
        lnTabela = new LnTabela();
        lnTabela.setTtbInCodigo(1);
        lnTabela.setTabStDescricao(tabela.getNomeTabela());
        lnTabela.setTabDtInicio(tabela.getDataInicial());
        lnTabela.setTabDtFinal(tabela.getDataFinal());
        lnTabela.setTipoFuncao(tabela.getTipoFuncao());
        
        if (listTabelaItem != null) {
            for (TabelaItem tbItem : listTabelaItem) {
                if (tbItem.getTipoFuncao() != null && tbItem.getTipoFuncao().equals(TipoFuncao.Incluir)) {

                    lnTabelaItem = new LnTabelaItem();
                    
                    lnTabelaItem.setTaiFlDependente(tbItem.getValorDependente());
                    lnTabelaItem.setTaiFlDesconto(tbItem.getValorDesconto());
                    lnTabelaItem.setTaiFlFinal(tbItem.getValorFinal());
                    lnTabelaItem.setTaiFlInicio(tbItem.getValorInicial());
                    lnTabelaItem.setTaiFlPercentual(tbItem.getPercentual());
                    lnTabelaItem.setTaiInQtddependente(tbItem.getQtdDependente());
                    lnTabelaItem.setTipoFuncao(tbItem.getTipoFuncao());

                    if (!listTabelaItemLoad.contains(lnTabelaItem)) {
                        listTabelaItemLoad.add(lnTabelaItem);
                        bGravar = true;
                    } else {
                        mensagem = "Exitem valores na tabela iguais, por favor verifique!!";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tabela IRRF", mensagem));
                        bGravar = false;
                        break;
                    }
                }
            }
            
            if (listTabelaItemLoad.size() > 0){
                lnTabela.setListLnTabelaItem(listTabelaItemLoad);
                bGravar = true;
            } else {
                mensagem = "Nao ha alteracoes a serem realizadas!!";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tabela IRRF", mensagem));
                bGravar = false;
            }
        } else {
            mensagem = "Lista da tabela esta vazia!!";
            bGravar = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tabela IRRF", mensagem));
        }        
        
        return bGravar;
    }
}
