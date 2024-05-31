package com.bicicletas.trayectos.logica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bicicletas.trayectos.dataAccess.ReporteRepository;
import com.bicicletas.trayectos.dataAccess.UsuarioRepository;
import com.bicicletas.trayectos.modelo.Reporte;
import com.bicicletas.trayectos.modelo.Usuario;

public class UsuarioServicesTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private UsuarioServices usuarioServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreacionUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Kevin");
        usuario.setEmail("kevin@example.com");
        usuario.setTelefono(123456789);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Integer id = usuarioServices.creacionUsuario(1, "Kevin", "kevin@example.com", 123456789);

        assertNotNull(id);
        assertEquals(1, id);
    }

    @Test
    void testCreacionUsuarioFalla() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(null);

        Integer id = usuarioServices.creacionUsuario(1, "Kevin", "kevin@example.com", 123456789);

        assertEquals(-1, id);
    }

    @Test
    void testBuscarFeedUsuario() {
        List<Reporte> reportes = new ArrayList<>();
        Reporte reporte1 = new Reporte();
        reporte1.setId(1);
        reporte1.setDescripcion("Reporte 1");

        Reporte reporte2 = new Reporte();
        reporte2.setId(2);
        reporte2.setDescripcion("Reporte 2");

        reportes.add(reporte1);
        reportes.add(reporte2);

        when(usuarioRepository.feedUsuario(1)).thenReturn(reportes);

        List<Reporte> result = usuarioServices.buscarFeedUsuario(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Reporte 1", result.get(0).getDescripcion());
        assertEquals("Reporte 2", result.get(1).getDescripcion());
    }
}
