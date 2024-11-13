package es.iesjandula.com.ServicioCitas.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservasDTO {

    private Long cliente_id;;
    private Long servicio_id;
    private LocalDate fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
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

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public ReservasDTO(Long cliente_id, Long servicio_id, LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.cliente_id = cliente_id;
        this.servicio_id = servicio_id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    
}
