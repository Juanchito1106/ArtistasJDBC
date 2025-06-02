package com.juancho.artistas.model;


import com.juancho.artistas.enums.GeneroMusical;
import com.juancho.artistas.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

public class RegistroDTO {

    // --- Atributos de Artista ---

    @NotBlank(message= "Name is required")
    @Size(min= 5, max=80, message ="Name must be between 5 and 80 characters")
    @Column("nombreArtistico")
    private String nombreArtistico;

    @NotNull(message = "Age is required")
    private Integer edad;

    @NotNull(message = "Date is required")
    @Column("fechaNacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message= "Nacionality is required")
    @Size(min= 5, max=80, message ="Nacionality must be between 5 and 80 characters")
    private String nacionalidad;

    private GeneroMusical genero;


    // --- Atributos de Disquera ---
    @NotBlank(message= "Name is required")
    @Size (min= 7, max=80, message ="Name must be between 7 and 80 characters")
    @Column("nombreDisquera")
    private String nombreDisquera;

    @NotBlank(message= "Country is required")
    @Size (min= 2, max=80, message ="Country must be between 2 and 80 characters")
    private String pais;

    @NotNull(message= "Number Artist is required")
    @Column("numeroArtistas")
    private Integer numeroArtistas;

    @NotNull(message= "Date is required")
    private LocalDate fundacion;

    private TipoContrato contrato;

    public RegistroDTO() {}



    // Getters y setters
    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
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

}

