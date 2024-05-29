package com.bicicletas.trayectos.dataAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bicicletas.trayectos.modelo.*;

@Repository
public interface ReporteRepository
extends JpaRepository <Reporte, Integer> 
{
    @Query("UPDATE Reporte r SET r.likes = r.likes + 1 WHERE r.id = :idReporte")
    void incrementarLikes(@Param("idReporte") Integer idReporte);
}
