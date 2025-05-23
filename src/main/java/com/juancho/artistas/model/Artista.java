package com.juancho.artistas.model;

import java.time.LocalDateTime;
import com.juancho.artistas.enums.GeneroMusical;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("artista")
public class Artista {


    @Id
    private Integer id;

    @NotBlank(message= "Name is required")
    @Size(min= 5, max=80, message ="Name must be between 5 and 80 characters")
    @Column("nombreArtistico")
    private String nombreArtistico;

    @NotBlank(message= "Age is required")
    private Integer edad;

    @NotBlank(message= "Date is required")
    @Column("fechaNacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message= "Nacionality is required")
    @Size(min= 5, max=80, message ="Nacionality must be between 5 and 80 characters")
    private String nacionalidad;

    private GeneroMusical genero;

    // one to one
    @Column("disqueraid")
    private AggregateReference<Disquera,Integer> disqueraid;

    public AggregateReference<Disquera, Integer> getDisquera() {
        return disqueraid;
    }

    public void setDisquera(AggregateReference<Disquera, Integer> disqueraid) {
        this.disqueraid = disqueraid;
    }

    //one to many
    //le llega el muchos
    private Set<Canciones> canciones = new HashSet<>();

    //Uno a muchos
    // le llega el one
    @Transient //Se guarda como referencia, los datos  del artista con su id
    Artista artista;


    public Set<Canciones> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Canciones> canciones) {
        this.canciones = canciones;
    }

    public void addCancion(Canciones cancion) {
        canciones.add(cancion);
        cancion.artista = this;
    }


    public Artista() {}

    public Artista(String nombreArtistico, Integer edad, LocalDate fechaNacimiento, String nacionalidad, GeneroMusical genero) {

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


}// Fin de la clase general

