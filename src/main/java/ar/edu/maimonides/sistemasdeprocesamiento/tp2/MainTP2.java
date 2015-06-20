package ar.edu.maimonides.sistemasdeprocesamiento.tp2;

import ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos.Estadistica;
import ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos.Movimiento;
import ar.edu.maimonides.sistemasdeprocesamiento.tp2.dtos.Saldo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 *
 * http://localhost:8080/tp2/apirest/calcular/registro (POST)
 * http://localhost:8080/tp2/apirest/calcular/saldos (GET)
 * http://localhost:8080/tp2/apirest/calcular/estadisticas (GET)
 * http://localhost:8080/tp2/apirest/calcular/cuentaspasadas (GET)
 *
 * Created by simetrias on 16/06/2015.
 */
public class MainTP2 {

    public static void main(String[] args) {

        System.out.println("Creando movimientos");

        List<Movimiento> movimientos = crearMovimientos();

        enviarMovimientos(movimientos);

        pedirSaldos();

        pedirCuentasPasadas();

        pedirEstadisticas();

    }

    private static void pedirEstadisticas() {

        System.out.println("Solicitando saldos al server");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("/tp2/apirest/calcular/estadisticas");

        Response response = target.request().get();

        if (response.getStatus() != 200)  {
            System.out.println("ERROR - Al solicitar estadísticas - status: " + response.getStatus());
        }
        List<Estadistica> estadisticas = (List<Estadistica>) response.getEntity();
        System.out.println("Se obtuvieron " + estadisticas.size() + " estadísticas");

        for (Estadistica estaditica : estadisticas) {

            System.out.println("UUID: "+  estaditica.getUuid() + " - Créditos: " + estaditica.getCreditos() + " - Débitos: "  + estaditica.getDebitos());
        }

        System.out.println("Fin de pedido de estdísticas");
    }

    private static void pedirCuentasPasadas() {

        System.out.println("Solicitando cuentas pasadas al server");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("/tp2/apirest/calcular/cuentaspasadas");

        Response response = target.request().get();

        if (response.getStatus() != 200)  {
            System.out.println("ERROR - Al solicitar cuenta pasada - status: " + response.getStatus());
        }
        List<Saldo> saldos = (List<Saldo>) response.getEntity();
        System.out.println("Se obtuvieron " + saldos.size() + " cuentas pasadas");
        for (Saldo saldo : saldos) {
            System.out.println("UUID: "+  saldo.getUuid() + " - SALDO: " + saldo.getSaldo());
        }

        System.out.println("Fin de pedido de cuentas pasadas");
    }

    private static void pedirSaldos() {
        System.out.println("Solicitando saldos al server");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("/tp2/apirest/calcular/saldos");

        Response response = target.request().get();

        if (response.getStatus() != 200)  {
            System.out.println("ERROR - Al solicitar saldo status: " + response.getStatus());
        }
        List<Saldo> saldos = (List<Saldo>) response.getEntity();
        System.out.println("Se obtuvieron " + saldos.size() + " saldos");
        for (Saldo saldo : saldos) {
            System.out.println("UUID: "+  saldo.getUuid() + " - SALDO: " + saldo.getSaldo());
        }

        System.out.println("Fin de pedido de saldos");
    }

    private static void enviarMovimientos(List<Movimiento> movimientos) {
        System.out.println("Enviando " + movimientos.size() + " movimientos al calculador");
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080").path("/tp2/apirest/calcular/registro");

        int cantErrores = 0;
        for (Movimiento movimiento : movimientos) {

            try {
                Response response = target.request().post(Entity.json(movimiento));

                if (response.getStatus() != 200) {
                    System.out.println("ERROR - STATUS NO ES 200 AL POSTEAR MOVIMIENTOS");
                    cantErrores++;
                }

            } catch (Exception e){
                e.printStackTrace();
                cantErrores++;
            }

        }

        if (cantErrores != 0){
            System.out.println("ERROR - Ocurrieron " + cantErrores + " durante el posteo de datos");
        } else {
            System.out.println("OK - posteo de datos realizado con éxito");
        }

        System.out.println("Fin envio movimientos");
    }

    private static List<Movimiento> crearMovimientos() {
        List<Cliente> clientes = crearClientes(50);

        List<Movimiento> result = new ArrayList<Movimiento>();

        for (int i = 0; i < 1000000; i++) {
            Cliente c = clientes.get(i % 50);
            Movimiento m = new Movimiento();
            m.setNombre(c.nombre);
            m.setApellido(c.apellido);
            m.setUuid(c.nroCuenta);
            m.setSaldo(c.saldo);

            if (Math.random() > 0.5) {
                m.setDebitos(new BigDecimal(monto()));
                m.setCreditos(new BigDecimal(0));
            } else {
                m.setDebitos(new BigDecimal(0));
                m.setCreditos(new BigDecimal(monto()));
            }
            m.setTope(new BigDecimal(20000));
            result.add(m);
        }
        return result;
    }

    private static double monto() {
        return 500 + Math.random() * 500;
    }

    private static List<Cliente> crearClientes(int i) {
        List<Cliente> result = new ArrayList<Cliente>();

        for (int j = 0; j < i; j++) {

            Cliente c = new Cliente();
            c.nombre = String.format("Nombre %.0f", Math.floor(10 + 10 * Math.random()));
            c.apellido = String.format("Apellido %.0f", Math.floor(10 + 10 * Math.random()));
            c.nroCuenta = UUID.randomUUID().toString();
            c.saldo = new BigDecimal(0);
            result.add(c);
        }

        return result;
    }

    private static class Cliente {
        String nombre;
        String apellido;
        String nroCuenta;
        BigDecimal saldo;
    }
}
