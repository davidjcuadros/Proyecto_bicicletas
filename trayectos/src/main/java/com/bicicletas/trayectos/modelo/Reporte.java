package com.bicicletas.trayectos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
    

    @Column(nullable = false)
    private Integer likes = 0; // Se inicializa en 0 por defecto

    @Lob
    @Column(nullable = false)
    private byte[] imagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}