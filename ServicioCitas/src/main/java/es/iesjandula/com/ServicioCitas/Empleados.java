package es.iesjandula.com.ServicioCitas.Entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Empleados {


    @Id
    @GeneratedValue
    private int empleado_id;

    private String nombre;
    private String especialidad;


    //Relaciones


    @OneToMany(mappedBy = "Reservas",cascade = CascadeType.ALL,orphanRemoval = true);
    private List<Empleados> empleados ;

    @OneToMany(mappedBy = "Horarios",cascade = CascadeType.ALL,orphanRemoval = true);
    private List<Empleados> empleados ;


    public Empleados(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    public Empleados() {}

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
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
}