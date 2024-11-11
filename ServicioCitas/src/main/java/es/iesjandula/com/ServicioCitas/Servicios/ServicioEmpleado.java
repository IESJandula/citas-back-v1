package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Empleados;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioEmpleado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplateEditor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleado {

    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    //Agregar empleados
    @Transactional
    public Empleados registrarEmpleado(Empleados empleado) {
        Optional<Empleados> EmpleadoExistente = repositorioEmpleado.findByNombre(empleado.getNombre());
        if (EmpleadoExistente.isPresent()) {
            throw new RuntimeException("El empleado ya existe");
        } else {
            return repositorioEmpleado.save(empleado);
        }
    }


    //Eliminar empleados
    @Transactional
    public void eliminarEmpleados(Empleados empleado) {
        Optional<Empleados> EmpleadoExistente = repositorioEmpleado.findByNombre(empleado.getNombre());
        if (EmpleadoExistente.isPresent()) {
            repositorioEmpleado.delete(EmpleadoExistente.get());
        }


    }

    //Mostrar empleados
    @Transactional
    public Optional<Empleados> listarEmpleado(String nombre) {
        return repositorioEmpleado.findByNombre(nombre);
    }



    //Modificar empleados
    @Transactional
    public Empleados modificarEmpleado(Long id, Empleados EmpleadoActualizado) {

        Empleados EmpleadoExistente = repositorioEmpleado.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado con ID " + id + " no encontrado"));


        EmpleadoExistente.setNombre(EmpleadoActualizado.getNombre());
        EmpleadoExistente.setEspecialidad(EmpleadoActualizado.getEspecialidad());




        return repositorioEmpleado.save(EmpleadoActualizado);
    }
}