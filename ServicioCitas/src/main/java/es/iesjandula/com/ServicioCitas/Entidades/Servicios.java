package es.iesjandula.com.ServicioCitas.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Servicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicio_id;

    private String nombre;
    private Long duracion;
    private double precio;

    public Servicios(Long servicio_id, String nombre, Long duracion, double precio) {
        this.servicio_id = servicio_id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
    }
    public Servicios() {

    }
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Reservas> reserva;


    public Long getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(Long servicio_id) {
        this.servicio_id = servicio_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Reservas> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reservas> reserva) {
        this.reserva = reserva;
    }
}
