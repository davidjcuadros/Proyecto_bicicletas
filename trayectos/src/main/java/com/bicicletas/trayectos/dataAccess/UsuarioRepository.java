package com.bicicletas.trayectos.dataAccess;

import com.bicicletas.trayectos.modelo.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository 
    extends JpaRepository <Usuario, Integer>
    {

        @Query("SELECT * FROM Reporte WHERE id= :id")
        List<Reporte> feedUsuario(Integer id);

}

