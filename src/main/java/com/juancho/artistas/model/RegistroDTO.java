package com.juancho.artistas.model;


import com.juancho.artistas.enums.GeneroMusical;
import com.juancho.artistas.enums.TipoContrato;
import java.time.LocalDate;

public class RegistroDTO {

    // --- Atributos de Artista ---
    private String nombreArtistico;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private GeneroMusical genero;


    // --- Atributos de Disquera ---
    private String nombreDisquera;
    private String pais;
    private Integer numeroArtistas;
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

