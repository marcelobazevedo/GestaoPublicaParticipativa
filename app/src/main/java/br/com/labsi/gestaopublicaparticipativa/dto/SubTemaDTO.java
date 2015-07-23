package br.com.labsi.gestaopublicaparticipativa.dto;

import java.io.Serializable;

/**
 * Created by Marcelo on 20/01/2015.
 */
public class SubTemaDTO implements Serializable {
    private static final long serialVersionUID = -8196393923967877699L;
    private Integer idSubTema;
    private String nomeSubTema;
    TemaDTO temaDTO;


    public Integer getIdSubTema() {
        return idSubTema;
    }

    public void setIdSubTema(Integer idSubTema) {
        this.idSubTema = idSubTema;
    }

    public String getNomeSubTema() {
        return nomeSubTema;
    }

    public void setNomeSubTema(String nomeSubTema) {
        this.nomeSubTema = nomeSubTema;
    }



    @Override
    public String toString() {
        return nomeSubTema;
    }
}
