package com.bicicletas.trayectos.logica;

import com.bicicletas.trayectos.dataAccess.ReporteRepository;
import com.bicicletas.trayectos.modelo.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReporteServices {

    @Autowired
    private ReporteRepository reporteRepository;

    public Integer publicar(float latitud, float longitud, Integer idUsuario, String descripcion, String rutaImagen) {
        Reporte reporte = new Reporte();
        reporte.setLatitud(latitud);
        reporte.setLongitud(longitud);
        reporte.setId(idUsuario);
        reporte.setDescripcion(descripcion);
        reporte.setImagen(rutaImagen);

        Optional<Reporte> reporteOptional = reporteRepository.findById(idUsuario);
        if (reporteOptional.isPresent()) {
            reporte = reporteOptional.get();
        }

        return reporteRepository.save(reporte).getId();
    }
}