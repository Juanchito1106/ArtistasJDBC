package com.juancho.artistas.model;

import java.time.LocalDateTime;
import com.juancho.artistas.enums.EstadoCancion;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


public class Canciones {

    @Id
    private Integer id;
    private String nombreCancion;
    private LocalDateTime fechaSalida;
    private Integer duracion; //duración en minutos
    private Integer reproducciones;
    private EstadoCancion estado;

    //Uno a muchos
    @Transient //Se guarda como referencia, los datos  del artista con su id
    Artista artista;

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