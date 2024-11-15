package es.iesjandula.com.ServicioCitas.Controladores;


import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Clientes")
public class ControladorCliente {

    @Autowired
    private ServicioCliente servicioCliente;
    @Autowired
    private RepositorioCliente repositorioCliente;

    //Agregar Clientes
    @PostMapping("/registro")
    public Clientes registrarCliente(@RequestBody Clientes cliente) {
        return servicioCliente.registrarCliente(cliente);
    }

    //Eliminar cliente
    @DeleteMapping("/eliminar")
    public void eliminarCliente(@RequestBody Clientes cliente) {
        servicioCliente.eliminarCliente(cliente);
    }

    //Mostrar clientes
    @GetMapping("/mostrarcliente/{email}")
    public Optional<Clientes> mostrarCliente(@PathVariable String email) {
        return servicioCliente.listarClientes(email);
    }

    //Modificar clientes
    @PutMapping("/ModificarClientes")
    public Clientes modificarCliente(@RequestBody Clientes nuevoCliente, @PathVariable String email) {
        Clientes cliente= repositorioCliente.findByEmail(email).orElseThrow(()-> new
                RuntimeException("No se encontro el cliente"));
        cliente.setNombre(nuevoCliente.getNombre());
        cliente.setEmail(nuevoCliente.getEmail());
        cliente.setTelefono(nuevoCliente.getTelefono());

        return repositorioCliente.save(cliente);

    }

    @GetMapping
    public List<Clientes> listarClientes() {
        return repositorioCliente.findAll();
    }



}
