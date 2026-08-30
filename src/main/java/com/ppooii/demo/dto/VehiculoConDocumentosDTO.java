package com.ppooii.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.ppooii.demo.Entities.Vehiculo;

public class VehiculoConDocumentosDTO {

    private Vehiculo vehiculo;
    private List<DocumentoAsociarDTO> documentos;

    public static class DocumentoAsociarDTO {
        private int idDocumento;
        private LocalDate fechaExpedicion;
        private LocalDate fechaVencimiento;

        public int getIdDocumento() { return idDocumento; }
        public void setIdDocumento(int idDocumento) { this.idDocumento = idDocumento; }
        public LocalDate getFechaExpedicion() { return fechaExpedicion; }
        public void setFechaExpedicion(LocalDate fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }
        public LocalDate getFechaVencimiento() { return fechaVencimiento; }
        public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    public List<DocumentoAsociarDTO> getDocumentos() { return documentos; }
    public void setDocumentos(List<DocumentoAsociarDTO> documentos) { this.documentos = documentos; }
}