package com.beltexam.ideasgeniales.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beltexam.ideasgeniales.models.Idea;
import com.beltexam.ideasgeniales.models.User;
import com.beltexam.ideasgeniales.repository.IdeaRepository;
import com.beltexam.ideasgeniales.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IdeasController {

    private final IdeaRepository ideaRepository;
    private final UserService userService;
    private final HttpSession session;

    public IdeasController(IdeaRepository ideaRepo, UserService userService, HttpSession session) {
        this.ideaRepository = ideaRepo;
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/ideas")
    public String mostrarIdeas(@RequestParam(value = "orderBy", required = false) String orderBy, Model model) {
        Iterable<Idea> ideasIterable;

        if ("asc".equals(orderBy)) {
            ideasIterable = ideaRepository.findByOrderByContadorLikesAsc();
        } else {
            // Por defecto, o para "desc", ordena por likes descendentes
            ideasIterable = ideaRepository.findByOrderByContadorLikesDesc();
        }

        List<Idea> ideas = new ArrayList<>();
        ideasIterable.forEach(ideas::add);

        model.addAttribute("ideas", ideas);

        Long userId = (Long) session.getAttribute("userID");
        if (userId != null) {
            User usuario = userService.encontrarPorId(userId);
            model.addAttribute("usuario", usuario);
        }

        return "idea.jsp";
    }


    @GetMapping("/ideas/{nombreIdea}")
    public String mostrarIdeaDetalles(@PathVariable String nombreIdea, Model model) {
        Idea idea = ideaRepository.findByNombreIdea(nombreIdea);
        model.addAttribute("idea", idea);

        Long userId = (Long) session.getAttribute("userID");
        if (userId != null) {
            User usuario = userService.encontrarPorId(userId);
            model.addAttribute("usuario", usuario);
        }

        return "idea2.jsp";
    }

    @PostMapping("/agregarLike")
    public String agregarLike(@RequestParam("ideaId") Long ideaId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userID");
        User usuario = userService.encontrarPorId(userId);
        Idea idea = ideaRepository.findById(ideaId).orElse(null);

        if (usuario != null && idea != null) {
            if (idea.getUsuariosQueDieronLike().contains(usuario)) {
                // El usuario ya dio like, entonces vamos a remover el like
                idea.getUsuariosQueDieronLike().remove(usuario);
                idea.incrementarContadorLikes(-1);  // decrementar el contador de likes
            } else {
                // El usuario no dio like, entonces vamos a agregar el like
                idea.getUsuariosQueDieronLike().add(usuario);
                idea.incrementarContadorLikes(1);  // incrementar el contador de likes
            }

            ideaRepository.save(idea);
        }

        return "redirect:/ideas";
    }




    @GetMapping("/editarIdea")
    public String mostrarFormularioEditar(@RequestParam("ideaId") Long ideaId, Model model) {
        Idea idea = ideaRepository.findById(ideaId).orElse(null);

        if (idea != null) {
            Long userId = (Long) session.getAttribute("userID");
            User usuario = userService.encontrarPorId(userId);

            // Asegurarse de que el usuario que creó la idea esté presente en el modelo
            model.addAttribute("usuario", usuario);
            model.addAttribute("idea", idea);
            return "edit.jsp";
        } else {
            // Manejar el caso en que la idea no existe
            return "redirect:/ideas";
        }
    }


    @PostMapping("/guardarEdicionIdea")
    public String guardarEdicionIdea(@ModelAttribute("idea") Idea idea) {
        // Guardar la idea editada en la base de datos
        ideaRepository.save(idea);
        return "redirect:/ideas";
    }
    @PostMapping("/eliminarIdea")
    public String eliminarIdea(@RequestParam("ideaId") Long ideaId) {
        // Obtener la idea a eliminar
        Idea idea = ideaRepository.findById(ideaId).orElse(null);

        if (idea != null) {
            // Eliminar la idea
            ideaRepository.delete(idea);
        }

        // Redirigir a la página de ideas después de la eliminación
        return "redirect:/ideas";
    }
}
