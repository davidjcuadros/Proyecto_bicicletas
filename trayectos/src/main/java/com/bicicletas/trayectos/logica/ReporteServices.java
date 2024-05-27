package com.bicicletas.trayectos.logica;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bicicletas.trayectos.dataAccess.ReporteRepository;
import com.bicicletas.trayectos.modelo.Reporte;

@Service
public class ReporteServices {

    @Autowired
    private ReporteRepository reporteRepository;

    public Integer publicar(float latitud, float longitud, Integer idUsuario,
                            String descripcion, byte[] imagen) {
        Reporte reporte = new Reporte();
        reporte.setLatitud(latitud);
        reporte.setLongitud(longitud);
        reporte.setId(idUsuario);
        reporte.setDescripcion(descripcion);
        reporte.setImagen(imagen);

        Optional<Reporte> reporteOptional = reporteRepository.findById(idUsuario);
        if (reporteOptional.isPresent()) {
            reporte = reporteOptional.get();
        }

        return reporteRepository.save(reporte).getId();
    }

    public boolean actualizarLikes(Integer idReporte) {
        // Obtener el reporte de la base de datos
        Reporte reporte = reporteRepository.findById(idReporte).orElse(null);
    
        if (reporte != null) {
            // Incrementar el contador de likes
            int likesActuales = reporte.getLikes();
            reporte.setLikes(likesActuales + 1);
    
            // Guardar los cambios en la base de datos
            reporteRepository.save(reporte);
    
            return true;
        }
    
        return false;
    }

    public List<Reporte> obtenerTodosLosReportes() {
        return reporteRepository.findAll();
    }

    public byte[] convertirImagenABytes(MultipartFile imagen) {
        try {
            return imagen.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}