package com.juancho.artistas.repositories;

import com.juancho.artistas.model.Fanaticos;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface FanaticosRepositorio  extends CrudRepository<Fanaticos, Integer> {
    @Query("select f.* from fanaticos f join disquera_fanatico fd on f.id = fd.fanaticos where fd.disquera = :id")
    Set<Fanaticos> findByDisqueraId(@Param("id") Integer id);
}