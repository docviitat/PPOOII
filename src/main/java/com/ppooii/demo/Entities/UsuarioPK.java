package com.ppooii.demo.Entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "idpersona", nullable = false)
    private Long idpersona;

    public UsuarioPK() {}

    public UsuarioPK(String login, Long idpersona) {
        this.login = login;
        this.idpersona = idpersona;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
    }

    // JPA requires overriding equals() and hashCode() for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPK usuarioPK = (UsuarioPK) o;
        return Objects.equals(login, usuarioPK.login) && 
               Objects.equals(idpersona, usuarioPK.idpersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, idpersona);
    }
}