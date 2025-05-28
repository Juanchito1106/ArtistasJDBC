package com.juancho.artistas.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.juancho.artistas.enums.PlataformaFavorita;
import org.springframework.data.annotation.Id;
import com.juancho.artistas.model.Disquera;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    //Many to many
    private Set<DisqueraFanatico> Disqueras = new HashSet<>();

    public Set<DisqueraFanatico> getDisquera() {
        return Disqueras;
    }

    public void addDisquera(Disquera disquera) {
        DisqueraFanatico disqueraFanatico = new DisqueraFanatico();
        disqueraFanatico.setDisquera(disquera.getId());
        disqueraFanatico.setNameDisquera(disquera.getNombreDisquera());
        this.Disqueras.add(disqueraFanatico);

    }

    public Set<Integer> getDisqueraIds(){
        return this.Disqueras.stream()
                .map(d->d.getDisquera())
                .collect(Collectors.toSet());
    }

    public Set<String> getDisqueraNames(){
        return this.Disqueras.stream()
                .map(d->d.getNameDisquera())
                .collect(Collectors.toSet());
    }
}
