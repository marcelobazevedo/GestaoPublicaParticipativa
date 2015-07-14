package br.com.labsi.gestaopublicaparticipativa.dto;

import java.io.Serializable;

/**
 * Created by Marcelo on 20/01/2015.
 */
public class TemaDTO implements Serializable {

    private static final long serialVersionUID = -4379304422163569063L;
    private Integer idTema;
    private String tema;

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return tema;
    }
}
