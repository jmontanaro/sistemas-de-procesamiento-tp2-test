package ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos;

import java.math.BigDecimal;

/**
 * Created by simetrias on 20/06/2015.
 */
public class Saldo {

    private String uuid;
    private BigDecimal saldo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
