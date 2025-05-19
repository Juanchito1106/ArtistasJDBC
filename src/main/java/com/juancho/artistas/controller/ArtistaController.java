package com.juancho.artistas.controller;

import com.juancho.artistas.model.Artista;
import com.juancho.artistas.repositories.ArtistasRepositorio;
import com.juancho.artistas.repositories.DisqueraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("artista", new Artista());
        return "Home"; // nombre del HTML
    }


    @PostMapping("/ingresar")
    public String addArtistPost(Artista artista) {

        if (!artistaRepo.existsById(artista.getId())) {
            artista.setId(null); // Deja que se genere automáticamente
            artistaRepo.save(artista);
        } else {
            artistaRepo.save(artista); // UPDATE
        }

        return "redirect:/artistas?success";
    }
}
