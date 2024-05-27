package com.bicicletas.trayectos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bicicletas.trayectos.logica.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
    
    @Autowired
    private ReporteServices reporteServices;

    @GetMapping("/creacionReporte")
    public String mostrarFormularioCreacionReporte(Model model) {
        return "crearReporte";
    }

    //CU001-crear-reporte
    @PostMapping("/creacionReporte/grabar")
    public String publicarReporte(
            @RequestParam String ubicacion,
            @RequestParam Integer idUsuario,
            @RequestParam String descripcion,
            @RequestParam(required = false) MultipartFile imagen,
            Model model
    ) {
        String rutaImagen = guardarImagen(imagen);
        String[] coordenadas = ubicacion.split(",");
        float latitud = Float.parseFloat(coordenadas[0].trim());
        float longitud = Float.parseFloat(coordenadas[1].trim());

        Integer idReporte = reporteServices.publicar(latitud, longitud, idUsuario, descripcion, rutaImagen);

        if (idReporte != -1) {
            model.addAttribute("idReporte", idReporte);
            return "reportecreado";
        } else {
            model.addAttribute("error", "Error al crear el reporte");
            return "reportenocreado";
        }
    }

    private String guardarImagen(MultipartFile imagen) {
        String rutaImagenes = "/ruta/hacia/carpeta/imagenes/"; // Reemplaza con la ruta correcta en tu servidor
        String nombreArchivo = "";

        try {
            // Generar un nombre de archivo único
            nombreArchivo = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(imagen.getOriginalFilename());

            // Crear la ruta completa del archivo
            Path rutaArchivo = Paths.get(rutaImagenes, nombreArchivo);

            // Copiar el archivo a la ruta especificada
            Files.copy(imagen.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

            // Devolver la ruta completa de la imagen guardada
            return rutaArchivo.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores
        }

        return "";
    }
    //CU004-seleccionar-yaSolucionado
    @PostMapping("/reporte/{idReporte}/like")
    public ResponseEntity<String> actualizarLikes(@PathVariable Integer idReporte) {
        try {
            boolean exitoActualizacion = reporteServices.actualizarLikes(idReporte);
            if (exitoActualizacion) {
                return ResponseEntity.ok("Like actualizado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el like");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el like: " + e.getMessage());
        }
    }
}