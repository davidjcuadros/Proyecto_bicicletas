package com.bicicletas.trayectos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bicicletas.trayectos.logica.ReporteServices;
import com.bicicletas.trayectos.modelo.Reporte;

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
    @PostMapping("/publicar")
    public String publicarReporte(@RequestParam("latitud") float latitud,
                                  @RequestParam("longitud") float longitud,
                                  @RequestParam("idUsuario") Integer idUsuario,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam("imagen") MultipartFile imagen) {
        byte[] imagenBytes = reporteServices.convertirImagenABytes(imagen);
        Integer idReporte = reporteServices.publicar(latitud, longitud, idUsuario, descripcion, imagenBytes);
        if (idReporte != -1) {
            return "reportecreado";
        } else {
            return "reportenocreado";
        }
    }

    //CU004-seleccionar-yaSolucionado
    @PostMapping("/{idReporte}/like")
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

    //C003-visualización-reportes
    @GetMapping("/feed")
    public String listarReportes(Model model) {
        List<Reporte> reportes = reporteServices.obtenerTodosLosReportes();
        model.addAttribute("reportes", reportes);
        return "reportes";
    }
    
    @GetMapping("/{idReporte}/like")
    public String darLikeAReporte(@PathVariable Integer idReporte) {
        reporteServices.actualizarLikes(idReporte);
        return "redirect:/reportes";
    }

    @GetMapping("/reporte_creado")
    public String mostrarReporteCreado(Model model) {
        return "reportecreado";
    }
    
    @GetMapping("/reporte_nocreado")
    public String mostrarReporteNoCreado(Model model) {
        return "reportenocreado";
    }
}