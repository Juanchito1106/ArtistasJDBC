package com.juancho.artistas.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import com.juancho.artistas.model.*;
import com.juancho.artistas.repositories.ArtistasRepositorio;
import com.juancho.artistas.repositories.DisqueraRepositorio;
import com.juancho.artistas.repositories.FanaticosRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {


    private final ArtistasRepositorio artistaRepo;
    private final DisqueraRepositorio disqueraRepo;
    private final FanaticosRepositorio fanaticosRepo;

    public ArtistaController(ArtistasRepositorio artistaRepo, DisqueraRepositorio disqueraRepo, FanaticosRepositorio fanaticosRepo) {
        this.artistaRepo = artistaRepo;
        this.disqueraRepo = disqueraRepo;
        this.fanaticosRepo = fanaticosRepo;
    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("artista", new Artista());
        model.addAttribute("disquera", new Disquera());
        return "Home"; // nombre del HTML
    }

    // Ingresa un nuevo Artista y una Disquera a la base de datos.
    @PostMapping("/ingresar")
    public String addArtistPost(@Valid @ModelAttribute ("artista") Artista artista,
                                BindingResult artistabindingResult,
                                @Valid @ModelAttribute ("disquera") Disquera disquera,
                                BindingResult disquerabindingResult,
                                Model model) {

        if (artistabindingResult.hasErrors() || disquerabindingResult.hasErrors()) {
            return "Home";
        }

        // Obtenemos el objeto Disquera con su Id generado
        AggregateReference<Disquera,Integer> disqueraAggregate = AggregateReference.to(
                disqueraRepo.save(disquera).getId());

        // Le asigno el id de la Disquera al Artista ya que es clave foránea
        artista.setDisquera(disqueraAggregate);

        // Se ingresa en la tabla al Artista
        artistaRepo.save(artista);

        model.addAttribute("artistas", artistaRepo.findAll());
        model.addAttribute("artista", new Artista());
        model.addAttribute("disquera", new Disquera());// Para limpiar campos
        model.addAttribute("artistaDisqueraCreados", true); // ✅ Artistas y disquera creados

        return "Home";
    }

    @GetMapping("/listarArtistas")
    public String listarArtistas(Model model) {
        model.addAttribute("artistas", artistaRepo.findAll());
        return "verArtistas";
    }

    @GetMapping("/verDisquera/{id}")
    public String verDisquera(Model model, @PathVariable Integer id) {
        Optional<Disquera> disqueraOpt = disqueraRepo.findById(id);
        Disquera disquera = disqueraOpt.orElse(new Disquera());
        model.addAttribute("disquera", disquera);
        return "verDisqueras";
    }

//    Para ir al menu de las canciones
        @GetMapping("/menuCanciones")
        public String menuCanciones(Model model) {
            model.addAttribute("artistas", artistaRepo.findAll());
            return "menuCanciones"; // nombre del HTML
        }

//    Para agregar una nueva CANCIÓN según el id del ARTISTA
        @GetMapping("/agregarCancion/{id}")
        public String agregarCancion(Model model,  @PathVariable Integer id) {
            model.addAttribute("idArtista", id);
            model.addAttribute("cancion", new Canciones());
            return "agregarCancion"; // nombre del HTML
        }


        @PostMapping("/agregarCancion")
        public String nuevaCancion(
                @Valid @ModelAttribute("cancion") Canciones canciones,
                BindingResult cancionBindingResult,
                @RequestParam(required=true, name ="idArtista") String idArtista,
                Model model) {

            if (cancionBindingResult.hasErrors()) {

                model.addAttribute("idArtista", idArtista);
                return "agregarCancion";
            }

            Optional<Artista> artistaById = artistaRepo.findById(Integer.valueOf(idArtista));
            Artista artista = artistaById.orElse(new Artista());
            artista.addCancion(canciones);
            artistaRepo.save(artista);

            model.addAttribute("idArtista", idArtista);
            model.addAttribute("cancion", new Canciones()); // Para limpiar campos


            model.addAttribute("cancionCreada", true);
            return "agregarCancion";
        }

    @GetMapping("/verCanciones/{id}")
    public String verCanciones(Model model, @PathVariable String id) {

        Optional<Artista> artistaById = artistaRepo.findById(Integer.valueOf(id));
        Artista artista = artistaById.orElse(new Artista());

        Set<Canciones> canciones = artista.getCanciones();

        model.addAttribute("canciones", canciones);
        return "verCanciones";
    }

    //Levar hacia la interfaz para añadir un nuevo fanatico
    @GetMapping("/agregarFanatico")
    public String agregarFanaticos(Model model) {
        model.addAttribute("fanatico", new Fanaticos());
        return "agregarFanatico";
    }

    @PostMapping("/ingresarFanatico")
    public String ingresarFanatico(@Valid @ModelAttribute("fanatico") Fanaticos fanatico,
                                   BindingResult fanaticoBindingResult,
                                   Model model){

        if (fanaticoBindingResult.hasErrors()) {
            return "agregarFanatico";
        }

        fanaticosRepo.save(fanatico);
        model.addAttribute("fanaticos", fanaticosRepo.findAll());
        model.addAttribute("fanaticoCreado", true); // ✅ Mensaje cuando fanatico sea creado con exito
        return "agregarFanatico";
    }

    @GetMapping("/verFanaticos")
    public String verFanaticos(Model model) {

        model.addAttribute("fanaticos", fanaticosRepo.findAll());
        return "menuFanaticos";
    }

    @GetMapping("/agregarDisquera/{id}")
    public String agregarDisquera(
            Model model,
            @PathVariable String id) {

        model.addAttribute("idFanatico", id);
        model.addAttribute("disquera",disqueraRepo.findAll());
        return "agregarDisquera";
    }

    // agregar una disquera a un fanatico:
    @PostMapping("/asignarDisquera")
    public String asignarDisquera(
            Model model,
            @RequestParam(required=true, name ="idFanatico") String idFanatico,
            @RequestParam(required=true, name ="idDisquera") String idDisquera) {

        Optional<Disquera> disqueraById = disqueraRepo.findById(Integer.valueOf(idDisquera));
        Disquera disquera = disqueraById.orElse(new Disquera());

        Optional<Fanaticos> fanaticoById = fanaticosRepo.findById(Integer.valueOf(idFanatico));
        Fanaticos fanatico = fanaticoById.orElse(new Fanaticos());

        Set<Integer> disquerasIds = fanatico.getDisqueraIds();

        String mensajeError = "prueba";

        if (disquerasIds.contains(Integer.valueOf(idDisquera))) {
            mensajeError = "La disquera ya se ha asignado al fanatico con id "+idFanatico;
        } else {
            fanatico.addDisquera(disquera);
            fanaticosRepo.save(fanatico);
        }

        model.addAttribute("mensajeError", mensajeError);
        model.addAttribute("fanaticos", fanaticosRepo.findAll());

        return "menuFanaticos";
    }

    // ver todas las disqueras que hay en los fanaticos con id:
    @GetMapping("/verDisqueras/{id}")
    public String verDisqueras(
            Model model,
            @PathVariable String id) {

        Optional<Fanaticos> fanaticoById = fanaticosRepo.findById(Integer.valueOf(id));
        Fanaticos fanatico = fanaticoById.orElse(new Fanaticos());

        Set<Integer> disquerasIds = fanatico.getDisqueraIds();

        Iterable<Disquera> disquerasDeFanatico = disqueraRepo.findAllById(disquerasIds);

        model.addAttribute("fanatico", fanatico);
        model.addAttribute("disqueras",disquerasDeFanatico);
        return "disquerasFanaticos";
    }

    //Ver los fanaticos de la disquera con id:
    @GetMapping("/fanaticosDisquera/{id}")
    public String verFanaticosDisquera(
            Model model,
            @PathVariable String id) {

        Optional<Disquera> disqueraById = disqueraRepo.findById(Integer.valueOf(id));
        Disquera disquera = disqueraById.orElse(new Disquera());

        Iterable<Fanaticos> fanaticosByDisqueraId = fanaticosRepo.findByDisqueraId(disquera.getId());

        model.addAttribute("fanaticos", fanaticosByDisqueraId);

        return "fanaticosDeLaDisquera";
    }


}
