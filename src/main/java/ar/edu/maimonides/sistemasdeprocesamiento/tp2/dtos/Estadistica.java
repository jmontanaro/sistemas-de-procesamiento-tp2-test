package ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos;

import java.math.BigDecimal;

/**
 * Created by simetrias on 20/06/2015.
 */
public class Estadistica {

    private String uuid;
    private BigDecimal creditos;
    private BigDecimal debitos;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getDebitos() {
        return debitos;
    }

    public void setDebitos(BigDecimal debitos) {
        this.debitos = debitos;
    }
}
