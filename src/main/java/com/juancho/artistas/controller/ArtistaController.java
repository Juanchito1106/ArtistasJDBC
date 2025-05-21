package com.juancho.artistas.controller;

import com.juancho.artistas.model.Artista;
import com.juancho.artistas.model.Disquera;
import com.juancho.artistas.model.RegistroDTO;
import com.juancho.artistas.repositories.ArtistasRepositorio;
import com.juancho.artistas.repositories.DisqueraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {


    private final ArtistasRepositorio artistaRepo;
    private final DisqueraRepositorio disqueraRepo;

    public ArtistaController(ArtistasRepositorio artistaRepo, DisqueraRepositorio disqueraRepo) {
        this.artistaRepo = artistaRepo;
        this.disqueraRepo = disqueraRepo;

    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("dto", new RegistroDTO());
        return "Home"; // nombre del HTML
    }


    @PostMapping("/ingresar")
    public String addArtistPost(@ModelAttribute RegistroDTO dto) {

        // 1. Crearé primero una nueva disquera
        Disquera disquera = new Disquera();
        disquera.setNombreDisquera(dto.getNombreDisquera());
        disquera.setPais(dto.getPais());
        disquera.setNumeroArtistas(dto.getNumeroArtistas());
        disquera.setFundacion(dto.getFundacion());
        disquera.setContrato(dto.getContrato());
        disqueraRepo.save(disquera);

        // 2. Luego creo un artista con referencia a la disquera creada
        Artista artista = new Artista();
        artista.setNombreArtistico(dto.getNombreArtistico());
        artista.setEdad(dto.getEdad());
        artista.setFechaNacimiento(dto.getFechaNacimiento());
        artista.setNacionalidad(dto.getNacionalidad());
        artista.setGenero(dto.getGenero());
        artista.setDisquera(AggregateReference.to(disquera.getId()));

        artistaRepo.save(artista);

        return "exito";
    }

    //Para ir al menu de las canciones
//        @GetMapping
//        public String menuCanciones(Model model) {
//            model.addAttribute("Artista", ArtistasRepositorio.findAll());
//            return "MenuCanciones"; // nombre del HTML
//        }

    //Para agregar una nueva CANCIÓN según el id del ARTISTA
//        @GetMapping
//        public String menuCanciones(Model model) {
//            model.addAttribute("Artista", ArtistasRepositorio.findAll());
//            return "MenuCanciones"; // nombre del HTML
//        }


}
