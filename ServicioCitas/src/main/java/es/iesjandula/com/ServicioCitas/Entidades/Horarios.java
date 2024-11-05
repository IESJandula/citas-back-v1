package es.iesjandula.com.ServicioCitas.Entidades;

import jakarta.persistence.*;

@Entity
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horario_id;

    private String diaSemana;
    private String horaInicio;
    private String horaFin;

    public Horarios(Long horario_id, String diaSemana, String horaInicio, String horaFin) {
        this.horario_id = horario_id;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public Horarios() {

    }
    @ManyToOne
    @JoinColumn(name= "empleado_id")
    private Empleados empleado;

    public Long getHorario_id() {
        return horario_id;
    }

    public void setHorario_id(Long horario_id) {
        this.horario_id = horario_id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
}
