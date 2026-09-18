package com.ppooii.demo.Entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vehiculo_conductor")
public class VehiculoConductor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona conductor;

    @Column(name = "fecha_asociacion", nullable = false)
    private LocalDate fechaAsociacion;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    public VehiculoConductor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Persona getConductor() { return conductor; }
    public void setConductor(Persona conductor) { this.conductor = conductor; }

    public LocalDate getFechaAsociacion() { return fechaAsociacion; }
    public void setFechaAsociacion(LocalDate fechaAsociacion) { this.fechaAsociacion = fechaAsociacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}