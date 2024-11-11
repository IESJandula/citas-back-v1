package es.iesjandula.com.ServicioCitas.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserva_id;

    @ManyToOne
    @JoinColumn(name ="cliente_id", nullable = false)
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name ="empleado_id", nullable = false)
    private Empleados empleado;

    @ManyToOne
    @JoinColumn(name ="servicio_id", nullable = false)
    private Servicios servicio;

    private LocalDate fecha;
    private LocalDateTime hora;

    public Reservas(Long reserva_id, Clientes cliente, Empleados empleado, Servicios servicio, LocalDate fecha, LocalDateTime hora) {
        this.reserva_id = reserva_id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;

    }

    public Long getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public Reservas() {
    }
}
