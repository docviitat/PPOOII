package com.ppooii.demo.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos", schema = "ppooii")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int id;

    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private String tipoVehiculo;

    @Column(name = "placa", nullable = false, unique = true, length = 6)
    private String placa;

    @Column(name = "tipo_servicio", nullable = false, length = 2)
    private String tipoServicio;

    @Column(name = "tipo_combustible", nullable = false, length = 20)
    private String tipoCombustible;

    @Column(name = "capacidad_pasajeros", nullable = false)
    private int capacidadPasajeros;

    @Column(name = "color", nullable = false, length = 7)
    private String color;

    @Column(name = "modelo", nullable = false)
    private int modelo;

    @Column(name = "marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "linea", nullable = false, length = 100)
    private String linea;

    public Vehiculo() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(String tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getModelo() { return modelo; }
    public void setModelo(int modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getLinea() { return linea; }
    public void setLinea(String linea) { this.linea = linea; }
}