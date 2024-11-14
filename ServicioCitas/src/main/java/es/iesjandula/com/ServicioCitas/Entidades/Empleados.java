package es.iesjandula.com.ServicioCitas.Entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Empleados {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empleado_id;

    private String nombre;
    private String especialidad;


    //Relaciones

    @OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Horarios> horarios ;


    public Empleados(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    public Empleados() {}

    public Long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(Long empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }
}