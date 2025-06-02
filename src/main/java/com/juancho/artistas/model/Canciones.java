package com.juancho.artistas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.juancho.artistas.enums.EstadoCancion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("canciones")
public class Canciones {

    @Id
    private Integer id;

    @NotBlank(message= "Name is required")
    @Column("nombreCancion")
    private String nombreCancion;

    @NotNull(message = "Date drop is required")
    @Column("fechaSalida")
    private LocalDate fechaSalida;

    @NotNull(message = "Duration drop is required")
    private Integer duracion; //duración en minutos

    @NotNull(message = "Reproductions drop is required")
    private Integer reproducciones;

    private EstadoCancion estado;

    //Uno a muchos
    // le llega el one
    @Transient //Se guarda como referencia, los datos  del artista con su id
    Artista artista;


    public Canciones() {

    }


    public Canciones(String nombreCancion, LocalDate fechaSalida, Integer duracion, Integer reproducciones,
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


    public LocalDate getFechaSalida() {
        return fechaSalida;
    }


    public void setFechaSalida(LocalDate fechaSalida) {
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