package com.juancho.artistas.model;

import java.time.LocalDateTime;

import com.juancho.artistas.enums.TipoContrato;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



public class Disquera {

    @Id
    private Integer id;
    private String nombreDisquera;
    private String pais;
    private Integer numeroArtistas;
    private LocalDateTime fundacion;
    private TipoContrato contrato;

    public  Disquera() {}

    public Disquera(String nombreDisquera, String pais, Integer numeroArtistas, LocalDateTime fundacion,
                    TipoContrato contrato) {

        super();
        this.nombreDisquera = nombreDisquera;
        this.pais = pais;
        this.numeroArtistas = numeroArtistas;
        this.fundacion = fundacion;
        this.contrato = contrato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDisquera() {
        return nombreDisquera;
    }

    public void setNombreDisquera(String nombreDisquera) {
        this.nombreDisquera = nombreDisquera;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getNumeroArtistas() {
        return numeroArtistas;
    }

    public void setNumeroArtistas(Integer numeroArtistas) {
        this.numeroArtistas = numeroArtistas;
    }

    public LocalDateTime getFundacion() {
        return fundacion;
    }

    public void setFundacion(LocalDateTime fundacion) {
        this.fundacion = fundacion;
    }

    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }


}// Fin de la clase general
