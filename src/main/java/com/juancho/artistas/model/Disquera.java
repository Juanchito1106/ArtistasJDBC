package com.juancho.artistas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.juancho.artistas.enums.TipoContrato;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("disquera")
public class Disquera {

    @Id
    private Integer id;

    @Column("nombreDisquera")
    private String nombreDisquera;

    private String pais;

    @Column("numeroArtistas")
    private Integer numeroArtistas;
    private LocalDate fundacion;
    private TipoContrato contrato;

    public  Disquera() {}

    public Disquera(String nombreDisquera, String pais, Integer numeroArtistas, LocalDate fundacion,
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

    public LocalDate getFundacion() {
        return fundacion;
    }

    public void setFundacion(LocalDate fundacion) {
        this.fundacion = fundacion;
    }

    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }


}// Fin de la clase general
