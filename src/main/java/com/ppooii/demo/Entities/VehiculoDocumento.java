package com.ppooii.demo.Entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo_documentos", schema = "ppooii")
public class VehiculoDocumento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo_documento")
    private int id;

    @Column(name = "id_vehiculo", nullable = false)
    private int idVehiculo;

    @Column(name = "id_documento", nullable = false)
    private int idDocumento;

    @Column(name = "fecha_expedicion", nullable = false)
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "estado_documento", nullable = false, length = 20)
    private String estadoDocumento = "En Verificacion";

    public VehiculoDocumento() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }
    public int getIdDocumento() { return idDocumento; }
    public void setIdDocumento(int idDocumento) { this.idDocumento = idDocumento; }
    public LocalDate getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(LocalDate fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getEstadoDocumento() { return estadoDocumento; }
    public void setEstadoDocumento(String estadoDocumento) { this.estadoDocumento = estadoDocumento; }
}