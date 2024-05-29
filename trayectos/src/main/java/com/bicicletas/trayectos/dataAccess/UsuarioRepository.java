package com.bicicletas.trayectos.dataAccess;

import com.bicicletas.trayectos.modelo.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository 
extends JpaRepository <Usuario, Integer>
{
        @Query("SELECT r FROM Reporte r WHERE r.usuario.id = :idUsuario")
        List<Reporte> feedUsuario(@Param("idUsuario") Integer idUsuario);
}

