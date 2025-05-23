package com.juancho.artistas.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import com.juancho.artistas.enums.EstadoCancion;
import com.juancho.artistas.model.Artista;
import com.juancho.artistas.model.Canciones;
import com.juancho.artistas.model.Disquera;
import com.juancho.artistas.model.RegistroDTO;
import com.juancho.artistas.repositories.ArtistasRepositorio;
import com.juancho.artistas.repositories.DisqueraRepositorio;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Set<Canciones> comentarios = artista.getCanciones();

        model.addAttribute("canciones", canciones);
        return "comentarios";
    }


}
