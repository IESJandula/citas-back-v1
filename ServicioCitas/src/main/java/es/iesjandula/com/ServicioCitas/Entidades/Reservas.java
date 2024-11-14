package es.iesjandula.com.ServicioCitas.Entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserva_id;

    @ManyToOne
    @JoinColumn(name ="cliente_id", nullable = false)
    @JsonManagedReference
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name ="servicio_id", nullable = false)
    @JsonManagedReference
    private Servicios servicio;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Reservas(Long reserva_id, Clientes cliente, Servicios servicio, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.reserva_id = reserva_id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

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


    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Reservas() {
    }
}
