package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opinion> opiniones = new ArrayList<>();

    // Constructor vacío
    public Pelicula() {}

    // Constructor con parámetros
    public Pelicula(String titulo) {
        this.titulo = titulo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Opinion> getOpiniones() {
        return opiniones;
    }

    public void añadirOpinion(Opinion o) {
        opiniones.add(o);
        o.setPelicula(this);
    }
}