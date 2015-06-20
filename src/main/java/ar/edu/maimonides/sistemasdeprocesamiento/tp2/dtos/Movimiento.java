package ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos;

import java.math.BigDecimal;

/**
 * Created by simetrias on 13/04/2015.
 */
public class Movimiento {
    private String uuid;
    private String apellido;
    private String nombre;
    private BigDecimal saldo;
    private BigDecimal tope;
    private BigDecimal debitos;
    private BigDecimal creditos;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getTope() {
        return tope;
    }

    public void setTope(BigDecimal tope) {
        this.tope = tope;
    }

    public BigDecimal getDebitos() {
        return debitos;
    }

    public void setDebitos(BigDecimal debitos) {
        this.debitos = debitos;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }
}


/*
*  - cantidad de movimientos
*  - 5 máximos compradores
*  - movimientos a rechazar (sobrepasan maximo tope)
*  - 5 clientes con mayor cantidad de movimientos
* */