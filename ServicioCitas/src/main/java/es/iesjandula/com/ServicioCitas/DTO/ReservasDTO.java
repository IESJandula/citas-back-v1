package es.iesjandula.com.ServicioCitas.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservasDTO {

    private Long cliente_id;
    private Long empleado_id;
    private Long servicio_id;
    private LocalDate fecha;
    private LocalDateTime hora;
    private String estado;

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(Long empleado_id) {
        this.empleado_id = empleado_id;
    }

    public Long getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(Long servicio_id) {
        this.servicio_id = servicio_id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ReservasDTO(Long cliente_id, Long empleado_id, Long servicio_id, LocalDate fecha, LocalDateTime hora, String estado) {
        this.cliente_id = cliente_id;
        this.empleado_id = empleado_id;
        this.servicio_id = servicio_id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }
    
    
}
