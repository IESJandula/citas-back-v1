package es.iesjandula.com.ServicioCitas.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservaId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicios servicio;

    private LocalDate fecha;

    private LocalTime horario;

    @ElementCollection
    @CollectionTable(name = "horarios_reserva", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "hora")
    private List<String> horasDisponibles;  // Lista de horas disponibles

    @ElementCollection
    @CollectionTable(name = "horarios_reserva_estado", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "disponible")
    private List<Boolean> horasDisponiblesEstado;  // Lista de estados (disponible/no disponible)

    // Constructor vacío
    public Reservas() {
    }

    // Getters y setters
    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
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

    public List<String> getHorasDisponibles() {
        return horasDisponibles;
    }

    public void setHorasDisponibles(List<String> horasDisponibles) {
        this.horasDisponibles = horasDisponibles;
    }

    // Nuevos métodos para manejar el estado de las horas
    public List<Boolean> getHorasDisponiblesEstado() {
        return horasDisponiblesEstado;
    }

    public void setHorasDisponiblesEstado(List<Boolean> horasDisponiblesEstado) {
        this.horasDisponiblesEstado = horasDisponiblesEstado;
    }

    // Métodos adicionales para gestionar la disponibilidad de horas
    public void marcarHoraComoReservada(String hora) {
        int index = horasDisponibles.indexOf(hora);
        if (index >= 0) {
            horasDisponiblesEstado.set(index, false); // Marca la hora como no disponible
        }
    }

    public void marcarHoraComoDisponible(String hora) {
        int index = horasDisponibles.indexOf(hora);
        if (index >= 0) {
            horasDisponiblesEstado.set(index, true); // Marca la hora como disponible
        }
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setClienteNombre(String nombre) {
        if (this.cliente == null) {
            this.cliente = new Clientes();  // Asegúrate de que el cliente esté inicializado
        }
        this.cliente.setNombre(nombre);  // Asigna el nombre al cliente
    }

}





