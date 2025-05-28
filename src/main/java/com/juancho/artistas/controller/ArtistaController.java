package com.juancho.artistas.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import com.juancho.artistas.enums.EstadoCancion;
import com.juancho.artistas.enums.PlataformaFavorita;
import com.juancho.artistas.model.*;
import com.juancho.artistas.repositories.ArtistasRepositorio;
import com.juancho.artistas.repositories.DisqueraRepositorio;
import com.juancho.artistas.repositories.FanaticosRepositorio;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        return "registroExitoso";
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
                @RequestParam(required=true, name ="idArtista") Integer idArtista,
                @RequestParam(required=true, name ="nombreCancion") String nombreCancion,
                @RequestParam(required=true, name ="fechaSalida") String fechaSalida,
                @RequestParam(required=true, name ="duracion") Integer duracion,
                @RequestParam(required=true, name ="reproducciones") Integer reproducciones,
                @RequestParam(required=true, name ="estado") EstadoCancion estado){

            String[] partes = fechaSalida.split("-");

            LocalDate fecha = LocalDate.of(Integer.valueOf(partes[0]),
                    Integer.valueOf(partes[1]),
                    Integer.valueOf(partes[2]));


//            implementé este metodo para evitar error de fecha
            LocalDateTime fechaCompleta = fecha.atStartOfDay();

            Canciones cancion = new Canciones(nombreCancion,fechaCompleta,duracion,reproducciones,estado);

            Optional<Artista> artistaById = artistaRepo.findById(Integer.valueOf(idArtista));
            Artista artista = artistaById.orElse(new Artista());

            artista.addCancion(cancion);
            artistaRepo.save(artista);

            return "redirect:/artistas/menuCanciones";
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
    public String agregarFanaticos() {
        return "agregarFanatico";
    }

    @PostMapping("/ingresarFanatico")
    public String ingresarFanatico(Model model,
                          @RequestParam(required=true, name ="nombreFanatico") String nombreFanatico,
                          @RequestParam(required=true, name ="paisOrigen") String paisOrigen,
                          @RequestParam(required=true, name ="edad") Integer edad,
                          @RequestParam(required=true, name ="fechaRegistro") String fechaRegistro,
                          @RequestParam(required=true, name ="plataforma") PlataformaFavorita plataforma) {

        String[] partes = fechaRegistro.split("-");

        LocalDate fecha = LocalDate.of(Integer.valueOf(partes[0]),
                Integer.valueOf(partes[1]),
                Integer.valueOf(partes[2]));

        //implementé este metodo para evitar error de fecha
        LocalDateTime fechaCompleta = fecha.atStartOfDay();

        Fanaticos fanatico = new Fanaticos();
        fanatico.setNombreFanatico(nombreFanatico);
        fanatico.setPaisOrigen(paisOrigen);
        fanatico.setEdad(edad);
        fanatico.setFechaRegistro(fechaCompleta);
        fanatico.setPlataforma(plataforma);

        fanaticosRepo.save(fanatico);

        model.addAttribute("fanaticos", fanaticosRepo.findAll());

        return "menuFanaticos";
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

        fanatico.addDisquera(disquera);

        fanaticosRepo.save(fanatico);

        return "redirect:/anuncios/verFanaticos";
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

        model.addAttribute("fanaticos", fanaticosRepo);
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
