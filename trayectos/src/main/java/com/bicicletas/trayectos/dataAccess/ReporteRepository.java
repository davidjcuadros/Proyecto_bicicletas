package com.bicicletas.trayectos.dataAccess;

import com.bicicletas.trayectos.modelo.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    @Query("UPDATE Reporte r SET r.likes = r.likes + 1 WHERE r.id = :idReporte")
    void incrementarLikes(@Param("idReporte") Integer idReporte);

    @Query("SELECT r FROM Reporte r WHERE r.latitud BETWEEN :minLat AND :maxLat AND r.longitud BETWEEN :minLon AND :maxLon")
    List<Reporte> findByLocation(@Param("minLat") float minLat, @Param("maxLat") float maxLat, @Param("minLon") float minLon, @Param("maxLon") float maxLon);

    @Query("SELECT r FROM Reporte r WHERE r.usuario.id = :idUsuario ORDER BY r.likes DESC")
    List<Reporte> findTopReportesByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT r FROM Reporte r ORDER BY r.likes DESC")
    List<Reporte> findAllOrderByLikesDesc();

    @Query("SELECT r FROM Reporte r WHERE r.descripcion LIKE %:keyword%")
    List<Reporte> findByDescripcionContaining(@Param("keyword") String keyword);

    @Query("SELECT COUNT(r) FROM Reporte r WHERE r.usuario.id = :idUsuario")
    Long countReportesByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT r FROM Reporte r WHERE r.likes > :minLikes")
    List<Reporte> findByLikesGreaterThan(@Param("minLikes") Integer minLikes);

    @Query("SELECT r FROM Reporte r WHERE r.id = :idReporte")
    Reporte findReporteById(@Param("idReporte") Integer idReporte);

    @Query("DELETE FROM Reporte r WHERE r.id = :idReporte")
    void deleteReporteById(@Param("idReporte") Integer idReporte);

    @Query("UPDATE Reporte r SET r.descripcion = :descripcion WHERE r.id = :idReporte")
    void actualizarDescripcion(@Param("idReporte") Integer idReporte, @Param("descripcion") String descripcion);
    
    @Query("UPDATE Reporte r SET r.latitud = :latitud, r.longitud = :longitud WHERE r.id = :idReporte")
    void actualizarUbicacion(@Param("idReporte") Integer idReporte, @Param("latitud") float latitud, @Param("longitud") float longitud);

    @Query("UPDATE Reporte r SET r.imagen = :imagen WHERE r.id = :idReporte")
    void actualizarImagen(@Param("idReporte") Integer idReporte, @Param("imagen") byte[] imagen);

    @Query(value = "INSERT INTO Reporte (latitud, longitud, descripcion, likes, imagen, usuario_id) VALUES (:latitud, :longitud, :descripcion, :likes, :imagen, :usuario_id)", nativeQuery = true)
    void insertarReporte(@Param("latitud") float latitud, @Param("longitud") float longitud, @Param("descripcion") String descripcion, @Param("likes") Integer likes, @Param("imagen") byte[] imagen, @Param("usuario_id") Integer usuarioId);

    @Query("UPDATE Reporte r SET r.likes = r.likes + :incremento WHERE r.id = :idReporte")
    void incrementarLikesConValor(@Param("idReporte") Integer idReporte, @Param("incremento") Integer incremento);

    @Query("UPDATE Reporte r SET r.usuario.id = :idUsuario WHERE r.id = :idReporte")
    void actualizarUsuario(@Param("idReporte") Integer idReporte, @Param("idUsuario") Integer idUsuario);

    @Query("DELETE FROM Reporte r WHERE r.likes < :minLikes")
    void deleteReportesWithLessThanMinLikes(@Param("minLikes") Integer minLikes);

}
