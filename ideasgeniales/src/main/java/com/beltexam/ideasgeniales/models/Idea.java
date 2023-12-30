package com.beltexam.ideasgeniales.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo Contenido no puede estar vacío")
    private String nombreIdea;

    // Nueva propiedad para contar los likes
    private int contadorLikes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuarioAgregador;

    @ManyToMany
    private Set<User> usuariosQueDieronLike = new HashSet<>();

    // Otros campos y métodos...

    public int getContadorLikes() {
        return contadorLikes;
    }

    public void setContadorLikes(int contadorLikes) {
        this.contadorLikes = contadorLikes;
    }

    // Método para incrementar el contador de likes
    public void incrementarContadorLikes(int cantidad) {
        this.contadorLikes += cantidad;
    }

    public Set<User> getUsuariosQueDieronLike() {
        return usuariosQueDieronLike;
    }

    public void setUsuariosQueDieronLike(Set<User> usuariosQueDieronLike) {
        this.usuariosQueDieronLike = usuariosQueDieronLike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreIdea() {
        return nombreIdea;
    }

    public void setNombreIdea(String nombreIdea) {
        this.nombreIdea = nombreIdea;
    }

    public User getUsuarioAgregador() {
        return usuarioAgregador;
    }

    public void setUsuarioAgregador(User usuarioAgregador) {
        this.usuarioAgregador = usuarioAgregador;
    }
}