package com.ppooii.demo.Entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vehiculo_documento")
public class VehiculoDocumento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;

    @Column(name = "id_documento", nullable = false)
    private Long idDocumento;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "estado_documento", length = 20)
    private String estadoDocumento;

    @Lob
    @Column(name = "documento_pdf", columnDefinition = "LONGBLOB")
    private String documentoPdfBase64; // PDF file payload stored as Base64 string

    public VehiculoDocumento() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Long idVehiculo) { this.idVehiculo = idVehiculo; }

    public Long getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Long idDocumento) { this.idDocumento = idDocumento; }

    public LocalDate getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(LocalDate fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getEstadoDocumento() { return estadoDocumento; }
    public void setEstadoDocumento(String estadoDocumento) { this.estadoDocumento = estadoDocumento; }

    public String getDocumentoPdfBase64() { return documentoPdfBase64; }
    public void setDocumentoPdfBase64(String documentoPdfBase64) { this.documentoPdfBase64 = documentoPdfBase64; }
}