package es.iesjandula.com.ServicioCitas.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pago_id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reservas reserva;

    private Double cantidad;

    private LocalDate fecha_pago;
    private String metodo_pago;

    public Pagos(Long pago_id, Reserva reserva, Double cantidad, LocalDate fecha_pago, String metodo_pago) {
        this.pago_id = pago_id;
        this.reserva = reserva;
        this.cantidad = cantidad;
        this.fecha_pago = fecha_pago;
        this.metodo_pago = metodo_pago;
    }

    public Pagos() {
    }

    public Long getPago_id() {
        return pago_id;
    }

    public void setPago_id(Long pago_id) {
        this.pago_id = pago_id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
}
