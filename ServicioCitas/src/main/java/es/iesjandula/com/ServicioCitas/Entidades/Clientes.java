package es.iesjandula.com.ServicioCitas.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Clientes {

    @Id
    @GeneratedValue
    private int cliente_id;

    private String nombre;
    private String email;
    private String telefono;


    public Clientes(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;

    }


    //Relaciones
    @OneToMany(mappedBy = "Reservas",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Clientes> clientes ;



    public Clientes(){}


    public int getIdCliente() {
        return cliente_id;
    }

    public void setIdCliente(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

