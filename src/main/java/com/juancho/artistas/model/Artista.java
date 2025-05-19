package com.juancho.artistas.model;

import java.time.LocalDateTime;

import com.juancho.artistas.enums.GeneroMusical;
import org.springframework.data.annotation.Id;


import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("artista")
public class Artista {


    @Id
    private Integer id;
    private String nombreArtistico;
    private Integer edad;
    private LocalDateTime fechaNacimiento;
    private String nacionalidad;
    private GeneroMusical genero;

    @Column("disqueraid")
    private AggregateReference<Disquera,Integer> disqueraid;

    public Artista() {}

    public Artista(String nombreArtistico, Integer edad, LocalDateTime fechaNacimiento, String nacionalidad, GeneroMusical genero) {

        super();
        this.nombreArtistico = nombreArtistico;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
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


}// Fin de la clase general

