package com.juancho.artistas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.juancho.artistas.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("disquera")
public class Disquera {

    @Id
    private Integer id;

    @NotBlank(message= "Name is required")
    @Size (min= 7, max=80, message ="Name must be between 7 and 80 characters")
    @Column("nombreDisquera")
    private String nombreDisquera;

    @NotBlank(message= "Country is required")
    @Size (min= 2, max=80, message ="Country must be between 2 and 80 characters")
    private String pais;

    @NotBlank(message= "Number Artist is required")
    @Column("numeroArtistas")
    private Integer numeroArtistas;

    @NotBlank(message= "Date is required")
    private LocalDate fundacion;

    private TipoContrato contrato;

    //one to many
    //le llega el muchos
    private Set<Fanaticos> fanaticos = new HashSet<>();

    //Uno a muchos
    // le llega el one
    @Transient //Se guarda como referencia, los datos  de la disquera con su id
    Disquera disquera;

    public Set<Fanaticos> getFanaticos() {
        return fanaticos;
    }

    public void setFanaticos(Set<Fanaticos> fanaticos) {
        this.fanaticos = fanaticos;
    }

    public void addFanatico(Fanaticos fanatico) {
        fanaticos.add(fanatico);
        fanatico.disquera = this;
    }

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
