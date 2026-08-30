package com.ppooii.demo.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentos", schema = "ppooii")
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private int id;

    @Column(name = "codigo_documento", nullable = false, unique = true, length = 20)
    private String codigoDocumento;

    @Column(name = "nombre_documento", nullable = false, length = 100)
    private String nombreDocumento;

    @Column(name = "tipos_vehiculo", nullable = false, length = 2)
    private String tiposVehiculo;

    @Column(name = "obligatoriedad", nullable = false, length = 2)
    private String obligatoriedad;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    public Documento() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigoDocumento() { return codigoDocumento; }
    public void setCodigoDocumento(String codigoDocumento) { this.codigoDocumento = codigoDocumento; }
    public String getNombreDocumento() { return nombreDocumento; }
    public void setNombreDocumento(String nombreDocumento) { this.nombreDocumento = nombreDocumento; }
    public String getTiposVehiculo() { return tiposVehiculo; }
    public void setTiposVehiculo(String tiposVehiculo) { this.tiposVehiculo = tiposVehiculo; }
    public String getObligatoriedad() { return obligatoriedad; }
    public void setObligatoriedad(String obligatoriedad) { this.obligatoriedad = obligatoriedad; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}