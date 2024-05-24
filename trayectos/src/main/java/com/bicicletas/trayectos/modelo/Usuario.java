package com.bicicletas.trayectos.modelo;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    String nombre;
    String email;
    Integer telefono;

    @OneToMany(mappedBy = "reporteID", cascade = CascadeType.ALL)
    List <Reporte> reportes = new ArrayList<>();
}
