package com.juancho.artistas.repositories;

import com.juancho.artistas.model.Artista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistasRepositorio extends CrudRepository<Artista, Integer> {

}
