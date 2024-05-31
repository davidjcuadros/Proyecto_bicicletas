package com.bicicletas.trayectos.logica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.bicicletas.trayectos.dataAccess.ReporteRepository;
import com.bicicletas.trayectos.modelo.Reporte;

public class ReporteServicesTests {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteServices reporteServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublicar() {
        byte[] imagenBytes = "fakeImageContent".getBytes();
        Reporte reporte = new Reporte();
        reporte.setId(1);
        reporte.setLatitud(27.0f);
        reporte.setLongitud(42.0f);
        reporte.setDescripcion("Descripción del reporte");
        reporte.setImagen(imagenBytes);

        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);

        Integer idReporte = reporteServices.publicar(27.0f, 42.0f, 1, "Descripción del reporte", imagenBytes);

        assertNotNull(idReporte);
        assertEquals(1, idReporte);
    }

    @Test
    void testActualizarLikes() {
        Reporte reporte = new Reporte();
        reporte.setId(1);
        reporte.setLikes(0);

        when(reporteRepository.findById(1)).thenReturn(Optional.of(reporte));
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);

        boolean result = reporteServices.actualizarLikes(1);

        assertTrue(result);
        assertEquals(1, reporte.getLikes());
    }

    @Test
    void testObtenerTodosLosReportes() {
        Reporte reporte1 = new Reporte();
        reporte1.setId(1);
        reporte1.setDescripcion("Reporte 1");

        Reporte reporte2 = new Reporte();
        reporte2.setId(2);
        reporte2.setDescripcion("Reporte 2");

        List<Reporte> reportes = Arrays.asList(reporte1, reporte2);

        when(reporteRepository.findAll()).thenReturn(reportes);

        List<Reporte> result = reporteServices.obtenerTodosLosReportes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Reporte 1", result.get(0).getDescripcion());
        assertEquals("Reporte 2", result.get(1).getDescripcion());
    }


    @Test
    void testConvertirImagenABytes() {
        byte[] imagenBytes = "fakeImageContent".getBytes();
        MultipartFile imagen = new MockMultipartFile("imagen.jpg", imagenBytes);

        byte[] result = reporteServices.convertirImagenABytes(imagen);

        assertArrayEquals(imagenBytes, result);
    }
}