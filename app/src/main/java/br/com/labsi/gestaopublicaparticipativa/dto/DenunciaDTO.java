package br.com.labsi.gestaopublicaparticipativa.dto;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Marcelo on 23/07/2015.
 */
public class DenunciaDTO implements Serializable {

    private static final long serialVersionUID = -2987011166734509643L;
    private Integer idUsuario;
    private Integer idSubtema;
    private String coordenadas;
    private Integer idDenuncia;
    private String nomeDenuncia;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdSubtema() {
        return idSubtema;
    }

    public void setIdSubtema(Integer idSubtema) {
        this.idSubtema = idSubtema;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Integer idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getNomeDenuncia() {
        return nomeDenuncia;
    }

    public void setNomeDenuncia(String nomeDenuncia) {
        this.nomeDenuncia = nomeDenuncia;
    }

    @Override
    public String toString() {
        return "DenunciaDTO{" +
                "idDenuncia=" + idDenuncia +
                '}';
    }
}
