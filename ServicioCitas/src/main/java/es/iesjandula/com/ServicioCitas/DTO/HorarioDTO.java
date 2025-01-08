package es.iesjandula.com.ServicioCitas.DTO;

import java.time.LocalTime;

public class HorarioDTO {

    private LocalTime hora;
    private boolean disponible;

    // Constructor, getters y setters
    public HorarioDTO(LocalTime hora, boolean disponible) {
        this.hora = hora;
        this.disponible = disponible;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
