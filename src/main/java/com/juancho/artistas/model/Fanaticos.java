package com.juancho.artistas.model;

import java.time.LocalDateTime;

import com.juancho.artistas.enums.PlataformaFavorita;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("fanaticos")
public class Fanaticos {


    @Id
    private Integer id;

    @Column("nombreFanatico")
    private String nombreFanatico;

    @Column("paisOrigen")
    private String paisOrigen;

    private Integer edad;

    @Column("fechaRegistro")
    private LocalDateTime fechaRegistro;

    private PlataformaFavorita plataforma;

    //Uno a muchos
    // le llega el one
    @Transient //Se guarda como referencia, los datos  del artista con su id
    Disquera disquera;

    public Fanaticos() {

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
