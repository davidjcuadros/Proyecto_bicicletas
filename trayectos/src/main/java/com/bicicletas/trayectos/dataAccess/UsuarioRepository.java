package com.bicicletas.trayectos.dataAccess;

import com.bicicletas.trayectos.modelo.Usuario;
import com.bicicletas.trayectos.modelo.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT r FROM Reporte r WHERE r.usuario.id = :idUsuario")
    List<Reporte> feedUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nombre%")
    List<Usuario> findByNombreContaining(@Param("nombre") String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    Usuario findByTelefono(@Param("telefono") Integer telefono);

    @Query("SELECT u FROM Usuario u ORDER BY SIZE(u.reportes) DESC")
    List<Usuario> findAllOrderByReportesCountDesc();

    @Query("SELECT u FROM Usuario u WHERE EXISTS (SELECT r FROM Reporte r WHERE r.usuario.id = u.id AND r.likes > :minLikes)")
    List<Usuario> findUsuariosWithReportesHavingLikesGreaterThan(@Param("minLikes") Integer minLikes);

    @Query("SELECT u FROM Usuario u JOIN u.reportes r WHERE r.descripcion LIKE %:keyword%")
    List<Usuario> findUsuariosByReporteDescripcionContaining(@Param("keyword") String keyword);

    @Query("UPDATE Usuario u SET u.nombre = :nombre, u.email = :email, u.telefono = :telefono WHERE u.id = :idUsuario")
    void updateUsuario(@Param("idUsuario") Integer idUsuario, @Param("nombre") String nombre, @Param("email") String email, @Param("telefono") Integer telefono);

    // Query de eliminación
    @Query("DELETE FROM Usuario u WHERE u.id = :idUsuario")
    void deleteUsuarioById(@Param("idUsuario") Integer idUsuario);

    // Query de inserción (aunque normalmente se usa el método save de JpaRepository)
    @Query(value = "INSERT INTO Usuario (nombre, email, telefono) VALUES (:nombre, :email, :telefono)", nativeQuery = true)
    void insertUsuario(@Param("nombre") String nombre, @Param("email") String email, @Param("telefono") Integer telefono);

    // Query de actualización específica
    @Query("UPDATE Usuario u SET u.nombre = :nombre WHERE u.id = :idUsuario")
    void updateUsuarioNombre(@Param("idUsuario") Integer idUsuario, @Param("nombre") String nombre);
}
