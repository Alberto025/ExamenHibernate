package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();

        // Prueba: Registrar una nueva película
        Pelicula nuevaPelicula = new Pelicula("Pelicula de Prueba");
        dataService.registrarPelicula(nuevaPelicula);

        // Prueba: Añadir una opinión a la película recién creada
        Opinion nuevaOpinion = new Opinion("Una película increíble!", "usuario@ejemplo.com", 9);
        dataService.añadirOpinionAPelicula(nuevaPelicula.getId(), nuevaOpinion);

        // Prueba: Obtener opiniones de un usuario específico
        List<Opinion> opiniones = dataService.obtenerOpinionesPorUsuario("usuario@ejemplo.com");
        for (Opinion o : opiniones) {
            System.out.println("Opinión: " + o.getDescripcion());
        }

        // Prueba: Listar películas con baja puntuación
        List<Pelicula> peliculasConBajaPuntuacion = dataService.obtenerPeliculasConBajaPuntuacion();
        for (Pelicula p : peliculasConBajaPuntuacion) {
            System.out.println("Película con baja puntuación: " + p.getTitulo());
        }
    }
}