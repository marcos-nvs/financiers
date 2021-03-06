/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.view;

import br.com.ln.comum.BeanVar;
import br.com.ln.comum.JsfHelper;
import br.com.ln.comum.VarComuns;
import br.com.ln.dao.CategoriaDao;
import br.com.ln.dao.PlanoContaDao;
import br.com.ln.entity.LnCategoria;
import br.com.ln.entity.LnPlanoconta;
import br.com.ln.funcao.PlanoContaFuncoes;
import br.com.ln.funcao.TabelaFuncoes;
import br.com.ln.objeto.Conta;
import br.com.ln.objeto.MesFrequencia;
import br.com.ln.objeto.Tabela;
import br.com.ln.tipos.TipoFuncao;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
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
@ManagedBean(name = "contaView")
public class PlanoContaView implements Serializable {

    //Variavéis Plano Conta
    private Integer idConta;
    private String nomeConta;
    private boolean bContaAtiva;
    private Double saldoInicial;
    private TipoFuncao tipoFuncao;
    private String contaAtiva;
    private Integer idTabela;

    private Conta conta;
    private LnCategoria categoria;
    private MesFrequencia mesFrequencia;
    private Tabela tabela;

    private List<LnCategoria> listaCategoria;
    private List<Conta> listaConta;
    private List<LnPlanoconta> listaBancos;

    private String mensagem;
    private final FacesContext context = FacesContext.getCurrentInstance();
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
    private final PlanoContaFuncoes planoContaFuncoes;
    private final TabelaFuncoes tabelaFuncoes;
    private final BeanVar beanVar;
    private final EmprestimoView emprestimoView;

    //Variavéis de Tela
    private Integer idCategoria;
    private Integer idTipoConta;
    private boolean bAtivoConta;
    private boolean bItemAtivoAplicacao;
    private boolean bItemImovelAluguel;
    private boolean bItemImovelPais;
    private boolean bItemAtivoMovel;
    private String tipoAtivo;
    private String tipoImovel;
    private String telaAtivo = "imovel.xhtml";
    private String telaConta = "../cadastro/planocontas/ativo.xhtml";
    private String telaCalculada;
    private boolean bCalculada;
    private String tipoEmprestimo;
    private String tipoFinanciamento;
    private String telaEmprestimo;
    private String telaFinanciamento;
    private boolean bMostraSaldoInicial = false;

    ReceitaDespesaView receitaDespesaView;

    public PlanoContaView() {
        planoContaFuncoes = new PlanoContaFuncoes();
        listaCategoria = CategoriaDao.grabCategoria('S');
        listaConta = planoContaFuncoes.grabListaConta();
        beanVar = (BeanVar) JsfHelper.getSessionAttribute("beanVar");
        emprestimoView = (EmprestimoView) JsfHelper.getSessionAttribute("emprestimoView");
        tabelaFuncoes = new TabelaFuncoes();
        tabela = tabelaFuncoes.buscaTabela(3);
        tipoEmprestimo = "1";
        tipoFinanciamento = "1";
        telaEmprestimo = "infotomando.xhtml";
        telaFinanciamento = "ativo.xhtml";
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public List<LnCategoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<LnCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public boolean isbContaAtiva() {
        return bContaAtiva;
    }

    public void setbContaAtiva(boolean bContaAtiva) {
        this.bContaAtiva = bContaAtiva;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getListaConta() {
        return listaConta;
    }

    public void setListaConta(List<Conta> listaConta) {
        this.listaConta = listaConta;
    }

    public String getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(String contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    //Tela
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(Integer idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getTelaConta() {
        return telaConta;
    }

    public void setTelaConta(String telaConta) {
        this.telaConta = telaConta;
    }

    public boolean isbAtivoConta() {
        return bAtivoConta;
    }

    public void setbAtivoConta(boolean bAtivoConta) {
        this.bAtivoConta = bAtivoConta;
    }

    public boolean isbItemAtivoAplicacao() {
        return bItemAtivoAplicacao;
    }

    public void setbItemAtivoAplicacao(boolean bItemAtivoAplicacao) {
        this.bItemAtivoAplicacao = bItemAtivoAplicacao;
    }

    public boolean isbItemAtivoMovel() {
        return bItemAtivoMovel;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public String getTelaAtivo() {
        return telaAtivo;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public void setTelaAtivo(String telaAtivo) {
        this.telaAtivo = telaAtivo;
    }

    public String getTelaCalculada() {
        return telaCalculada;
    }

    public void setTelaCalculada(String telaCalculada) {
        this.telaCalculada = telaCalculada;
    }

    public boolean isbCalculada() {
        return bCalculada;
    }

    public void setbCalculada(boolean bCalculada) {
        this.bCalculada = bCalculada;
    }

    public String getTipoEmprestimo() {
        return tipoEmprestimo;
    }

    public void setTipoEmprestimo(String tipoEmprestimo) {
        this.tipoEmprestimo = tipoEmprestimo;
    }

    public String getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public void setTipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public String getTelaEmprestimo() {
        return telaEmprestimo;
    }

    public void setTelaEmprestimo(String telaEmprestimo) {
        this.telaEmprestimo = telaEmprestimo;
    }

    public String getTelaFinanciamento() {
        return telaFinanciamento;
    }

    public void setTelaFinanciamento(String telaFinanciamento) {
        this.telaFinanciamento = telaFinanciamento;
    }

    public boolean isbMostraSaldoInicial() {
        return bMostraSaldoInicial;
    }

    public void setbMostraSaldoInicial(boolean bMostraSaldoInicial) {
        this.bMostraSaldoInicial = bMostraSaldoInicial;
    }

    public MesFrequencia getMesFrequencia() {
        return mesFrequencia;
    }

    public void setMesFrequencia(MesFrequencia mesFrequencia) {
        this.mesFrequencia = mesFrequencia;
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public boolean isbItemImovelAluguel() {
        return bItemImovelAluguel;
    }

    public void setbItemImovelAluguel(boolean bItemImovelAluguel) {
        this.bItemImovelAluguel = bItemImovelAluguel;
    }

    public boolean isbItemImovelPais() {
        return bItemImovelPais;
    }

    public void setbItemImovelPais(boolean bItemImovelPais) {
        this.bItemImovelPais = bItemImovelPais;
    }

    public List<LnPlanoconta> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(List<LnPlanoconta> listaBancos) {
        this.listaBancos = listaBancos;
    }
  
       
    public void btIncluirConta() {
        if (VarComuns.lnPerfilacesso.getPacChIncluir().equals('S')) {
            clearVarConta();
            clearVarContaDepdente();
            clearVarAgendaConta();
            beanVar.setTelaDialog("WEB-INF/templates/dialog/dialogplanoconta.xhtml");
            beanVar.setTituloDialog("ln.texto.cadastroconta");
            
            if (listaCategoria == null){
                listaCategoria = CategoriaDao.grabCategoria('S');
            }
            
            RequestContext.getCurrentInstance().execute("PF('dialog').show()");
            tipoFuncao = TipoFuncao.Incluir;
        } else {
            mensagem = bundle.getString("ln.mb.frase.permissao");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.tabela"), mensagem));
        }
    }

    public void btAlterarConta() {

    }

    public void btExcluirConta() {

    }

    public void btVisualizarConta() {

    }

    public void btFecharConta() {
        beanVar.setNovaTela("WEB-INF/templates/principal.xhtml");
    }

    public void btSalvarContaLista() {
        conta = new Conta();
        defineConfiguracaoConta(conta);

        if (!planoContaFuncoes.verificaInformacao(conta)) {
            mensagem = planoContaFuncoes.mensagem;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("ln.mb.titulo.conta"), mensagem));
        } else if (planoContaFuncoes.planoConta(conta)) {
            mensagem = planoContaFuncoes.mensagem;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.conta"), mensagem));
            listaConta = planoContaFuncoes.grabListaConta();
            RequestContext.getCurrentInstance().execute("PF('dialog').hide()");
        } else {
            mensagem = planoContaFuncoes.mensagem;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("ln.mb.titulo.conta"), mensagem));
        }
    }

    private Conta defineConfiguracaoConta(Conta conta) {

        Integer idTipoCategoria = planoContaFuncoes.tipoConta(idCategoria);
        AtivoView ativoView;
        AlertaView alertaView;

        switch (idTipoCategoria) {
            case 1: //Ativo
                ativoView = (AtivoView) JsfHelper.getSessionAttribute("ativoInfo");
                conta.setAtivo(ativoView.grabAtivo(tipoAtivo));
                conta.getAtivo().setTipoAtivo(tipoAtivo);
                conta.getAtivo().setTipoImovel(tipoImovel);
                conta.setSaldoConta(conta.getAtivo().getValorAtivo());
                ativoView.setValorInicial(null);
                break;
//            case 2: //Passivo.
//                break;
            case 3: //banco
                BancoView bancoView = (BancoView) JsfHelper.getSessionAttribute("bancoInfo");
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                conta.setBanco(bancoView.grabBanco());
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                break;
            case 4: //Cartão de Crédito
                CartaoCreditoView ccView = (CartaoCreditoView) JsfHelper.getSessionAttribute("ccView");
                conta.setCartaoCredito(ccView.grabCartaoCredito());
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
//            case 5: //Dinheiro
//                break;
            case 6: //Empréstimo
                EmprestimoView emprestimoView = (EmprestimoView) JsfHelper.getSessionAttribute("emprestimoView");
                conta.setEmprestimo(emprestimoView.grabEmprestimo());
                conta.getEmprestimo().setTipoEmprestimo(tipoEmprestimo);
                conta.setSaldoConta(conta.getEmprestimo().getValorTotal());
                
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
            case 7: //Financiamento
                FinanciamentoView financiamentoView = (FinanciamentoView) JsfHelper.getSessionAttribute("finView");
                ativoView = (AtivoView) JsfHelper.getSessionAttribute("ativoInfo");
                conta.setFinancimento(financiamentoView.grabFinanciamento());
                conta.setAtivo(ativoView.grabAtivo(tipoAtivo));
                conta.getAtivo().setTipoAtivo(tipoAtivo);
                conta.setSaldoConta(conta.getFinancimento().getValorTotalFinanciamento());
                conta.getAtivo().setValorAtivo(conta.getFinancimento().getValorAtivo());
                conta.getFinancimento().setTipoFinancimanto(tipoFinanciamento);
                conta.setAtivo(ativoView.grabAtivo(tipoAtivo));
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
//            case 8: //Outros Passivos
//                break;
            case 9: //Receitas
                receitaDespesaView = (ReceitaDespesaView) JsfHelper.getSessionAttribute("recdespView");
                conta.setReceitaDespesa(receitaDespesaView.grabReceitaDespesa());
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
            case 10: //Despesas
                receitaDespesaView = (ReceitaDespesaView) JsfHelper.getSessionAttribute("recdespView");
                conta.setReceitaDespesa(receitaDespesaView.grabReceitaDespesa());
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
            case 11: //Contas à Receber
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
            case 12: //Contas à Pagar
                alertaView = (AlertaView) JsfHelper.getSessionAttribute("alertaView");
                alertaView.getConfiguracaoAlerta().setEmailDescricao(null);
                alertaView.getConfiguracaoAlerta().setAleta(false);
                alertaView.getConfiguracaoAlerta().setEmail(false);
                conta.setConfiguracaoAlerta(alertaView.getConfiguracaoAlerta());
                break;
        }

        if (conta.getSaldoConta() == null && saldoInicial != null) {
            conta.setSaldoConta(saldoInicial);
        } else if (conta.getSaldoConta() == null) {
            conta.setSaldoConta(0d);
        }

        conta.setbContaAtiva(bContaAtiva);
        conta.setCodigoCategoria(idCategoria);
        conta.setDescricaoConta(nomeConta);
        conta.setTipoFuncao(tipoFuncao);
        conta.setDtCriacao(planoContaFuncoes.getDataDb());
        conta.setUsuStCodigo(planoContaFuncoes.getUsuarioLogado());

        return conta;
    }

    public void btFecharContaLista() {
        listaCategoria = null;
//        emprestimoView.setListaFavorecido(null);
        RequestContext.getCurrentInstance().execute("PF('dialog').hide()");
    }

    private void clearVarConta() {

        idConta = null;
        nomeConta = null;
        bContaAtiva = false;
        saldoInicial = null;
        tipoFuncao = null;
        contaAtiva = null;
        conta = null;
        categoria = null;

        idCategoria = null;
        idTipoConta = null;
        bAtivoConta = false;
        bItemAtivoAplicacao = false;
        bItemImovelAluguel = false;
        bItemImovelPais = false;
        bItemAtivoMovel = false;
//        tipoAtivo = null;
        tipoImovel = null;
        telaCalculada = null;
        bCalculada = false;
        bMostraSaldoInicial = false;
        
        telaConta = "../cadastro/planocontas/ativo.xhtml";

        if (conta != null) {
            conta.getAtivo().getEndereco().setBairro(null);
            conta.getAtivo().getEndereco().setCep(null);
            conta.getAtivo().getEndereco().setCidade(null);
            conta.getAtivo().getEndereco().setComplemento(null);
            conta.getAtivo().getEndereco().setEndereco(null);
            conta.getAtivo().getEndereco().setEstado(null);
            conta.getAtivo().getEndereco().setTipoEndereco(null);
            conta.getAtivo().setValorAtivo(null);
        }
    }

    private void clearVarContaDepdente() {
    }

    private void clearVarAgendaConta() {

    }

    public void btVoltar() {
        beanVar.setNovaTela("WEB-INF/templates/principal.xhtml");
    }

    public void identificaTipoConta() {

        getTipoContaPorCategoria(idCategoria);

        switch (idTipoConta) {
            case 1:
                telaConta = "../cadastro/planocontas/ativo.xhtml";
                bItemAtivoAplicacao = false;
                bItemAtivoMovel = false;
                bMostraSaldoInicial = false;
                break;
            case 2:
                telaConta = "../cadastro/planocontas/vazia.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 3:
                telaConta = "../cadastro/planocontas/banco.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 4:
                telaConta = "../cadastro/planocontas/cartaocredito.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 5:
                telaConta = "../cadastro/planocontas/vazia.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 6:
                telaConta = "../cadastro/planocontas/emprestimo.xhtml";
                bMostraSaldoInicial = false;
                break;
            case 7:
                telaConta = "../cadastro/planocontas/financiamento.xhtml";
                bItemAtivoAplicacao = true;
                bItemAtivoMovel = true;
                bMostraSaldoInicial = false;
                bItemImovelAluguel = true;
                bItemImovelPais = true;
                break;
            case 8:
                telaConta = "../cadastro/planocontas/vazia.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 9:
                telaConta = "../cadastro/planocontas/receitas.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 10:
                telaConta = "../cadastro/planocontas/despesas.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 11:
                telaConta = "../cadastro/planocontas/vazia.xhtml";
                bMostraSaldoInicial = true;
                break;
            case 12:
                telaConta = "../cadastro/planocontas/vazia.xhtml";
                bMostraSaldoInicial = true;
                break;
        }

    }

    private void getTipoContaPorCategoria(Integer categoria) {
        idTipoConta = CategoriaDao.grabTipoContaPorCategoria(categoria);
    }

    public void mostraCalculoConta() {
        if (bCalculada) {
            telaCalculada = "../cadastro/planocontas/contacalculada.xhtml";
        } else {
            telaCalculada = "../cadastro/planocontas/vazia.xhtml";
        }
    }

    public void mostraAtivo() {

        switch (tipoAtivo) {
            case "Imóvel":
                telaAtivo = "imovel.xhtml";
                break;
            case "Automóvel":
                telaAtivo = "automovel.xhtml";
                break;
            case "Móvel":
                telaAtivo = "vazia.xhtml";
                break;
            case "Aplicação":
                telaAtivo = "vazia.xhtml";
                break;
            case "Empresa":
                telaAtivo = "vazia.xhtml";
                break;
        }
    }

    public void mostraTipoEmpretimo() {
        if (tipoEmprestimo.equals("1")) {
            telaEmprestimo = "infotomando.xhtml";
        } else {
            telaEmprestimo = "emprestando.xhtml";
        }
    }

    public void mostraTipoFinanciamento() {
        if (tipoFinanciamento.equals("1")) {
            telaFinanciamento = "ativo.xhtml";
        } else {
            telaFinanciamento = "escolhebem.xhtml";
        }
    }

    public String textoContaAtiva(boolean contaAtiva) {

        if (contaAtiva) {
            return this.contaAtiva = bundle.getString("ln.bt.sim");
        } else {
            return this.contaAtiva = bundle.getString("ln.bt.nao");
        }
    }

    public String textoCategoria(Integer idCategoria) {
        categoria = CategoriaDao.grabCategoria(idCategoria);
        return categoria.getCatStDescricao();
    }
    
    public String mostraSaldoAtual(Integer idConta) {
        return NumberFormat.getCurrencyInstance().format(planoContaFuncoes.saldoAtualConta(idConta));
    }
}
