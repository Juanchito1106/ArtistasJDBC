package com.juancho.artistas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.juancho.artistas.enums.PlataformaFavorita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import com.juancho.artistas.model.Disquera;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("fanaticos")
public class Fanaticos {


    @Id
    private Integer id;

    @NotBlank(message= "Name is required")
    @Size(min= 3, max=80, message ="Name must be between 3 and 80 characters")
    @Column("nombreFanatico")
    private String nombreFanatico;

    @NotBlank(message= "Country is required")
    @Size(min= 3, max=80, message ="Country must be between 3 and 80 characters")
    @Column("paisOrigen")
    private String paisOrigen;

    @NotNull(message = "Age is required")
    private Integer edad;

    @NotNull(message = "Date is required")
    @Column("fechaRegistro")
    private LocalDate fechaRegistro;

    private PlataformaFavorita plataforma;


    public Fanaticos() {

    }


    public Fanaticos(String nombreFanatico, String paisOrigen, Integer edad, LocalDate fechaRegistro,
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


    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }


    public void setFechaRegistro(LocalDate fechaRegistro) {
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
