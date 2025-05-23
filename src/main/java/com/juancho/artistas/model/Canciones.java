package com.juancho.artistas.model;

import java.time.LocalDateTime;
import com.juancho.artistas.enums.EstadoCancion;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("canciones")
public class Canciones {

    @Id
    private Integer id;

    @Column("nombreCancion")
    private String nombreCancion;

    @Column("fechaSalida")
    private LocalDateTime fechaSalida;
    private Integer duracion; //duración en minutos
    private Integer reproducciones;
    private EstadoCancion estado;

//    @Column("artistaid")
//    private AggregateReference<Artista,Integer> artistaid;

    //Uno a muchos
    // le llega el one
    @Transient //Se guarda como referencia, los datos  del artista con su id
    Artista artista;

//    public AggregateReference<Artista, Integer> getArtista() {
//        return artistaid;
//    }
//
//    public void setArtista(AggregateReference<Artista, Integer> artistaid) {
//        this.artistaid = artistaid;
//    }


    public Canciones() {

    }


    public Canciones(String nombreCancion, LocalDateTime fechaSalida, Integer duracion, Integer reproducciones,
                     EstadoCancion estado) {

        super();
        this.nombreCancion = nombreCancion;
        this.fechaSalida = fechaSalida;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        this.estado = estado;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }


    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }


    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }


    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }


    public Integer getDuracion() {
        return duracion;
    }


    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }


    public Integer getReproducciones() {
        return reproducciones;
    }


    public void setReproducciones(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }


    public EstadoCancion getEstado() {
        return estado;
    }


    public void setEstado(EstadoCancion estado) {
        this.estado = estado;
    }

}