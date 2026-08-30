package com.ppooii.demo.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.Documento;

@Repository("IDocumentoRepo")
public interface DocumentoRepository extends JpaRepository<Documento, Serializable> {
    Documento findById(int id);
    Documento findByCodigoDocumento(String codigo);
}