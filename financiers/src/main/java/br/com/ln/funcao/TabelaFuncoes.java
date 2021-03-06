/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.funcao;

import br.com.ln.tipos.TipoFuncao;
import br.com.ln.comum.Historico;
import br.com.ln.dao.TabelaDao;
import br.com.ln.entity.LnTabela;
import br.com.ln.entity.LnTabelaItem;
import br.com.ln.objeto.Tabela;
import br.com.ln.objeto.TabelaItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcos Naves
 */
public class TabelaFuncoes implements Serializable {

    private Integer codigoTabItem = 0;
    private Integer codigoTab = 0;
    public String mensagem;
    public boolean bGravar;
    private final Historico historico;

    private final FacesContext context = FacesContext.getCurrentInstance();
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
    Logger logger = Logger.getLogger(TabelaFuncoes.class);

    public TabelaFuncoes() {
        historico = new Historico();
    }

    public boolean gravaTabela(LnTabela lnTabela) {
        mensagem = "";
        boolean bSalvo = false;

        switch (lnTabela.getTipoFuncao()) {
            case Incluir:
                lnTabela.setTabInCodigo(TabelaDao.grabLnTabelaNextId());
                bSalvo = incluirTabela(lnTabela);
                break;
            case Alterar:
                bSalvo = AlterarTabela(lnTabela);
                break;
            case Excluir:
                bSalvo = excluirTabela(lnTabela);
                break;
        }
        return bSalvo;
    }

    public boolean gravaTabelaItem(LnTabelaItem lnTabelaItem) {
        mensagem = "";
        boolean bSalvo = false;

        switch (lnTabelaItem.getTipoFuncao()) {
            case Incluir:
                lnTabelaItem.setTaiInCodigo(TabelaDao.grabLnTabelaItemNextId());
                bSalvo = incluirTabelaItem(lnTabelaItem);
                break;
            case Alterar:
                bSalvo = true;
                break;
            case Excluir:
                bSalvo = excluirTabelaItem(lnTabelaItem);
                break;
        }
        return bSalvo;
    }

    private boolean incluirTabela(LnTabela lnTabela) {
        boolean bSalvo = false;
        try {
            for (LnTabelaItem lnTabelaItem : lnTabela.getListLnTabelaItem()) {
                lnTabelaItem.setTabInCodigo(lnTabela.getTabInCodigo());
                bSalvo = gravaTabelaItem(lnTabelaItem);
            }

            if (bSalvo) {
                TabelaDao.saveObject(lnTabela);
                historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.inclusaotabela") + " " + lnTabela.getTabStDescricao());
                return true;
            } else {
                return false;
            }
        } catch (HibernateException ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        } catch (Exception ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        }
    }

    private boolean AlterarTabela(LnTabela lnTabela) {

        lnTabela.getListLnTabelaItem().stream().forEach((lnTabelaItem) -> {
            gravaTabelaItem(lnTabelaItem);
        });

        try {
            TabelaDao.saveOrUpdateObject(lnTabela);
            historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.alteracaotabela") + " " + lnTabela.getTabStDescricao());
            return true;
        } catch (HibernateException ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        }
    }

    private boolean excluirTabela(LnTabela lnTabela) {
        try {
            lnTabela.getListLnTabelaItem().stream().forEach((lnTabelaItem) -> {
                TabelaDao.deleteObject(lnTabelaItem);
            });
            TabelaDao.deleteObject(lnTabela);
            historico.gravaHistoricoModulo(bundle.getString("ln.mb.historico.exclusaotabela") + " " + lnTabela.getTabStDescricao());
            return true;
        } catch (HibernateException ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        }
    }

    private boolean incluirTabelaItem(LnTabelaItem lnTabelaItem) {

        try {
            TabelaDao.saveObject(lnTabelaItem);
            return true;
        } catch (HibernateException ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        }
    }

    private boolean excluirTabelaItem(LnTabelaItem lnTabelaItem) {

        try {
            TabelaDao.deleteObject(lnTabelaItem);
            return true;
        } catch (HibernateException ex) {
            mensagem = bundle.getString("ln.mb.frase.problema");
            logger.error(mensagem);
            return false;
        }
    }

    public List<Tabela> buscaListaTabela(Integer ttbInCodigo) {
        Tabela tabela;

        List<Tabela> listaTabela = new ArrayList<>();
        List<LnTabela> listTabelaDao = TabelaDao.grabTabela(ttbInCodigo);

        for (LnTabela lnTabela : listTabelaDao) {
            lnTabela.setListLnTabelaItem(TabelaDao.grabTabelaItem(lnTabela.getTabInCodigo()));

            tabela = new Tabela();
            tabela.setCodigoTabela(lnTabela.getTabInCodigo());
            tabela.setNomeTabela(lnTabela.getTabStDescricao());
            tabela.setDataInicial(lnTabela.getTabDtInicio());
            tabela.setDataFinal(lnTabela.getTabDtFinal());

            tabela.setListTabelaItem(buscaTabelaItem(lnTabela.getListLnTabelaItem()));

            listaTabela.add(tabela);
        }

        return listaTabela;
    }

    public Tabela buscaTabela(Integer ttbInCodigo) {
        Tabela tabela = null;

        List<LnTabela> listTabelaDao = TabelaDao.grabTabela(ttbInCodigo);

        for (LnTabela lnTabela : listTabelaDao) {
            lnTabela.setListLnTabelaItem(TabelaDao.grabTabelaItem(lnTabela.getTabInCodigo()));

            tabela = new Tabela();
            tabela.setCodigoTabela(lnTabela.getTabInCodigo());
            tabela.setNomeTabela(lnTabela.getTabStDescricao());
            tabela.setDataInicial(lnTabela.getTabDtInicio());
            tabela.setDataFinal(lnTabela.getTabDtFinal());

            tabela.setListTabelaItem(buscaTabelaItem(lnTabela.getListLnTabelaItem()));

        }

        return tabela;
    }

    private List<TabelaItem> buscaTabelaItem(List<LnTabelaItem> listTabelaItemDao) {

        List<TabelaItem> listaTabelaItem = new ArrayList<>();
        TabelaItem tabelaItem;

        for (LnTabelaItem lnTabelaItem : listTabelaItemDao) {
            tabelaItem = new TabelaItem();

            tabelaItem.setCodigoTabela(lnTabelaItem.getTabInCodigo());
            tabelaItem.setCodigoTabItem(lnTabelaItem.getTaiInCodigo());
            tabelaItem.setPercentual(lnTabelaItem.getTaiFlPercentual());
            tabelaItem.setValorDependente(lnTabelaItem.getTaiFlDependente());
            tabelaItem.setValorDesconto(lnTabelaItem.getTaiFlDesconto());
            tabelaItem.setValorFinal(lnTabelaItem.getTaiFlFinal());
            tabelaItem.setValorInicial(lnTabelaItem.getTaiFlInicio());
            tabelaItem.setOrigem(lnTabelaItem.getTaiStOrigem());
            tabelaItem.setTipo(lnTabelaItem.getTaiStTipo());

            listaTabelaItem.add(tabelaItem);
        }

        return listaTabelaItem;
    }

    public List<Tabela> montaTabela(Integer tipoTabela) {
        TabelaFuncoes tabelaFuncao = new TabelaFuncoes();
        return tabelaFuncao.buscaListaTabela(tipoTabela);
    }

    public Integer calcIdTabela() {
        Integer i = codigoTab + 1;
        codigoTab = i;
        return codigoTab;
    }

    public Integer calcIdTabelaItem() {
        Integer i = codigoTabItem + 1;
        codigoTabItem = i;
        return codigoTabItem;
    }

    public boolean tabela(LnTabela lnTabela) {
        TabelaFuncoes tabelafuncao = new TabelaFuncoes();
        mensagem = tabelafuncao.mensagem;
        return tabelafuncao.gravaTabela(lnTabela);
    }

    public LnTabela loadLnTabela(Tabela tabela, List<TabelaItem> listTabelaItem, Integer ttbInCodigo) {
        LnTabelaItem lnTabelaItem;
        List<LnTabelaItem> listTabelaItemLoad = new ArrayList<>();
        LnTabela lnTabela = new LnTabela();
        lnTabela.setTtbInCodigo(ttbInCodigo);
        lnTabela.setTabStDescricao(tabela.getNomeTabela());
        lnTabela.setTabDtInicio(tabela.getDataInicial());
        lnTabela.setTabDtFinal(tabela.getDataFinal());
        lnTabela.setTipoFuncao(tabela.getTipoFuncao());

        if (tabela.getCodigoTabela() != null && tabela.getCodigoTabela() > 0) {
            lnTabela.setTabInCodigo(tabela.getCodigoTabela());
        } else {
            lnTabela.setTabInCodigo(calcIdTabela());
        }

        if (listTabelaItem != null) {
            for (TabelaItem tbItem : listTabelaItem) {

                lnTabelaItem = new LnTabelaItem();
                lnTabelaItem.setTabInCodigo(lnTabela.getTabInCodigo());

                if (tbItem.getCodigoTabItem() != null && tbItem.getCodigoTabItem() > 0) {
                    lnTabelaItem.setTaiInCodigo(tbItem.getCodigoTabItem());
                } else {
                    lnTabelaItem.setTaiInCodigo(calcIdTabelaItem());
                }

                lnTabelaItem.setTaiFlDependente(tbItem.getValorDependente());
                lnTabelaItem.setTaiFlDesconto(tbItem.getValorDesconto());
                lnTabelaItem.setTaiFlFinal(tbItem.getValorFinal());
                lnTabelaItem.setTaiFlInicio(tbItem.getValorInicial());
                lnTabelaItem.setTaiFlPercentual(tbItem.getPercentual());
                lnTabelaItem.setTaiStOrigem(tbItem.getOrigem());
                lnTabelaItem.setTaiStTipo(tbItem.getTipo());

                if (tbItem.getTipoFuncao() != null) {
                    lnTabelaItem.setTipoFuncao(tbItem.getTipoFuncao());
                } else {
                    lnTabelaItem.setTipoFuncao(TipoFuncao.Alterar);
                }

                if (!listTabelaItemLoad.contains(lnTabelaItem)) {
                    listTabelaItemLoad.add(lnTabelaItem);
                    bGravar = true;
                } else {
                    mensagem = bundle.getString("ln.mb.frase.valorestabelaigual");
                    logger.warn(mensagem);
                    bGravar = false;
                    break;
                }
            }

            if (bGravar) {
                if (listTabelaItemLoad.size() > 0) {
                    lnTabela.setListLnTabelaItem(listTabelaItemLoad);
                } else {
                    mensagem = bundle.getString("ln.mb.frase.naohaalteracao");
                    logger.warn(mensagem);
                }
            }
        } else {
            mensagem = bundle.getString("ln.mb.frase.tabelavazia");
            logger.warn(mensagem);
        }
        return lnTabela;
    }

}
