/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ln.objeto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Marcos Naves
 */
public class ReceitaDespesa implements Serializable{
    
    private boolean bCalculada;
    private Integer contaBaseCalculo;
    private boolean bImposto;
    private Integer codigoTabelaImposto;
    private Integer qtdeOcorrencia;
    private Integer diaOcorrencia;
    private boolean bJaneiro;
    private boolean bFevereiro;
    private boolean bMarco;
    private boolean bAbril;
    private boolean bMaio;
    private boolean bJunho;
    private boolean bJulho;
    private boolean bAgosto;
    private boolean bSetembro;
    private boolean bOutubro;
    private boolean bNovembro;
    private boolean bDezembro;

    public ReceitaDespesa() {
    }

    public boolean isbCalculada() {
        return bCalculada;
    }

    public void setbCalculada(boolean bCalculada) {
        this.bCalculada = bCalculada;
    }

    public Integer getContaBaseCalculo() {
        return contaBaseCalculo;
    }

    public void setContaBaseCalculo(Integer contaBaseCalculo) {
        this.contaBaseCalculo = contaBaseCalculo;
    }

    public boolean isbImposto() {
        return bImposto;
    }

    public void setbImposto(boolean bImposto) {
        this.bImposto = bImposto;
    }

    public Integer getCodigoTabelaImposto() {
        return codigoTabelaImposto;
    }

    public void setCodigoTabelaImposto(Integer codigoTabelaImposto) {
        this.codigoTabelaImposto = codigoTabelaImposto;
    }

    public Integer getQtdeOcorrencia() {
        return qtdeOcorrencia;
    }

    public void setQtdeOcorrencia(Integer qtdeOcorrencia) {
        this.qtdeOcorrencia = qtdeOcorrencia;
    }

    public Integer getDiaOcorrencia() {
        return diaOcorrencia;
    }

    public void setDiaOcorrencia(Integer diaOcorrencia) {
        this.diaOcorrencia = diaOcorrencia;
    }

    public boolean isbJaneiro() {
        return bJaneiro;
    }

    public void setbJaneiro(boolean bJaneiro) {
        this.bJaneiro = bJaneiro;
    }

    public boolean isbFevereiro() {
        return bFevereiro;
    }

    public void setbFevereiro(boolean bFevereiro) {
        this.bFevereiro = bFevereiro;
    }

    public boolean isbMarco() {
        return bMarco;
    }

    public void setbMarco(boolean bMarco) {
        this.bMarco = bMarco;
    }

    public boolean isbAbril() {
        return bAbril;
    }

    public void setbAbril(boolean bAbril) {
        this.bAbril = bAbril;
    }

    public boolean isbMaio() {
        return bMaio;
    }

    public void setbMaio(boolean bMaio) {
        this.bMaio = bMaio;
    }

    public boolean isbJunho() {
        return bJunho;
    }

    public void setbJunho(boolean bJunho) {
        this.bJunho = bJunho;
    }

    public boolean isbJulho() {
        return bJulho;
    }

    public void setbJulho(boolean bJulho) {
        this.bJulho = bJulho;
    }

    public boolean isbAgosto() {
        return bAgosto;
    }

    public void setbAgosto(boolean bAgosto) {
        this.bAgosto = bAgosto;
    }

    public boolean isbSetembro() {
        return bSetembro;
    }

    public void setbSetembro(boolean bSetembro) {
        this.bSetembro = bSetembro;
    }

    public boolean isbOutubro() {
        return bOutubro;
    }

    public void setbOutubro(boolean bOutubro) {
        this.bOutubro = bOutubro;
    }

    public boolean isbNovembro() {
        return bNovembro;
    }

    public void setbNovembro(boolean bNovembro) {
        this.bNovembro = bNovembro;
    }

    public boolean isbDezembro() {
        return bDezembro;
    }

    public void setbDezembro(boolean bDezembro) {
        this.bDezembro = bDezembro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.bCalculada ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.contaBaseCalculo);
        hash = 97 * hash + (this.bImposto ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.codigoTabelaImposto);
        hash = 97 * hash + Objects.hashCode(this.qtdeOcorrencia);
        hash = 97 * hash + Objects.hashCode(this.diaOcorrencia);
        hash = 97 * hash + (this.bJaneiro ? 1 : 0);
        hash = 97 * hash + (this.bFevereiro ? 1 : 0);
        hash = 97 * hash + (this.bMarco ? 1 : 0);
        hash = 97 * hash + (this.bAbril ? 1 : 0);
        hash = 97 * hash + (this.bMaio ? 1 : 0);
        hash = 97 * hash + (this.bJunho ? 1 : 0);
        hash = 97 * hash + (this.bJulho ? 1 : 0);
        hash = 97 * hash + (this.bAgosto ? 1 : 0);
        hash = 97 * hash + (this.bSetembro ? 1 : 0);
        hash = 97 * hash + (this.bOutubro ? 1 : 0);
        hash = 97 * hash + (this.bNovembro ? 1 : 0);
        hash = 97 * hash + (this.bDezembro ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReceitaDespesa other = (ReceitaDespesa) obj;
        if (this.bCalculada != other.bCalculada) {
            return false;
        }
        if (this.bImposto != other.bImposto) {
            return false;
        }
        if (this.bJaneiro != other.bJaneiro) {
            return false;
        }
        if (this.bFevereiro != other.bFevereiro) {
            return false;
        }
        if (this.bMarco != other.bMarco) {
            return false;
        }
        if (this.bAbril != other.bAbril) {
            return false;
        }
        if (this.bMaio != other.bMaio) {
            return false;
        }
        if (this.bJunho != other.bJunho) {
            return false;
        }
        if (this.bJulho != other.bJulho) {
            return false;
        }
        if (this.bAgosto != other.bAgosto) {
            return false;
        }
        if (this.bSetembro != other.bSetembro) {
            return false;
        }
        if (this.bOutubro != other.bOutubro) {
            return false;
        }
        if (this.bNovembro != other.bNovembro) {
            return false;
        }
        if (this.bDezembro != other.bDezembro) {
            return false;
        }
        if (!Objects.equals(this.contaBaseCalculo, other.contaBaseCalculo)) {
            return false;
        }
        if (!Objects.equals(this.codigoTabelaImposto, other.codigoTabelaImposto)) {
            return false;
        }
        if (!Objects.equals(this.qtdeOcorrencia, other.qtdeOcorrencia)) {
            return false;
        }
        if (!Objects.equals(this.diaOcorrencia, other.diaOcorrencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReceitaDespesa{" + "bCalculada=" + bCalculada + ", contaBaseCalculo=" + contaBaseCalculo + ", bImposto=" + bImposto + ", codigoTabelaImposto=" + codigoTabelaImposto + ", qtdeOcorrencia=" + qtdeOcorrencia + ", diaOcorrencia=" + diaOcorrencia + ", bJaneiro=" + bJaneiro + ", bFevereiro=" + bFevereiro + ", bMarco=" + bMarco + ", bAbril=" + bAbril + ", bMaio=" + bMaio + ", bJunho=" + bJunho + ", bJulho=" + bJulho + ", bAgosto=" + bAgosto + ", bSetembro=" + bSetembro + ", bOutubro=" + bOutubro + ", bNovembro=" + bNovembro + ", bDezembro=" + bDezembro + '}';
    }

    
}
