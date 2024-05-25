package com.bicicletas.trayectos.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reporteID")
    private Integer id;

    private float latitud;
    private float longitud;
    private String descripcion;
    private String imagen; // Campo para almacenar la ruta de la imagen
    

    @Column(nullable = false)
    private Integer likes = 0; // Se inicializa en 0 por defecto

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}