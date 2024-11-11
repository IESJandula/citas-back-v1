package es.iesjandula.com.ServicioCitas.Controladores;

import es.iesjandula.com.ServicioCitas.Entidades.Empleados;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioEmpleado;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Empleado")
public class ControladorEmpleado {
    @Autowired
    private ServicioEmpleado servicioEmpleado;
    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    //Agregar empleado
    @PostMapping("/Empleado/registro")
    public Empleados registrarempleado(@RequestBody Empleados empleado) {
        return servicioEmpleado.registrarEmpleado(empleado);
    }

    //Eliminar empleado
    @DeleteMapping("/eliminar")
    public void eliminarEmpleado(@RequestBody Empleados empleado) {
        servicioEmpleado.eliminarEmpleados(empleado);
    }

    //Mostrar empleado
    @GetMapping("/mostrarempleado/{email}")
    public Optional<Empleados> mostrarEmpleado(@PathVariable String nombre) {
        return servicioEmpleado.listarEmpleado(nombre);
    }

    //Modificar empleado
    @PutMapping("/ModificarEmpleado")
    public Empleados modificarEmpleado(@RequestBody Empleados nuevoEmpleado, @PathVariable String nombre) {
        Empleados empleado= repositorioEmpleado.findByNombre(nombre).orElseThrow(()-> new
                RuntimeException("No se encontro el empleado"));
        empleado.setNombre(nuevoEmpleado.getNombre());
        empleado.setEspecialidad(nuevoEmpleado.getEspecialidad());


        return repositorioEmpleado.save(empleado);

    }

}