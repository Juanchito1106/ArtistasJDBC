package com.juancho.artistas.model;

import java.time.LocalDateTime;

import com.juancho.artistas.enums.PlataformaFavorita;
import org.springframework.data.annotation.Id;


public class Fanaticos {


    @Id
    private Integer id;
    private String nombreFanatico;
    private String paisOrigen;
    private Integer edad;
    private LocalDateTime fechaRegistro;
    private PlataformaFavorita plataforma;


    public Fanaticos() {
        super();
    }


    public Fanaticos(String nombreFanatico, String paisOrigen, Integer edad, LocalDateTime fechaRegistro,
                     PlataformaFavorita plataforma) {
        super();
        this.nombreFanatico = nombreFanatico;
        this.paisOrigen = paisOrigen;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
        this.plataforma = plataforma;
    }


    public String getNombreFanatico() {
        return nombreFanatico;
    }


    public void setNombreFanatico(String nombreFanatico) {
        this.nombreFanatico = nombreFanatico;
    }


    public String getPaisOrigen() {
        return paisOrigen;
    }


    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }


    public Integer getEdad() {
        return edad;
    }


    public void setEdad(Integer edad) {
        this.edad = edad;
    }


    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }


    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    public PlataformaFavorita getPlataforma() {
        return plataforma;
    }


    public void setPlataforma(PlataformaFavorita plataforma) {
        this.plataforma = plataforma;
    }

}
