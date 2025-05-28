package com.juancho.artistas.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("disquera_fanatico")
public class DisqueraFanatico {


    private Integer disquera;

    @Column("nameDisquera")
    private String nameDisquera;

    public DisqueraFanatico() {
    }

    public DisqueraFanatico(Integer disquera, String nameDisquera) {
        this.disquera = disquera;
        this.nameDisquera = nameDisquera;
    }

    public Integer getDisquera() {
        return disquera;
    }

    public void setDisquera(Integer disquera) {
        this.disquera = disquera;
    }

    public String getNameDisquera() {
        return nameDisquera;
    }

    public void setNameDisquera(String nameDisquera) {
        this.nameDisquera = nameDisquera;
    }
}
