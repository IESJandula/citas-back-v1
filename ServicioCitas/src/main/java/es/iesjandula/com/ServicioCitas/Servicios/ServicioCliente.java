package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {

    @Autowired
    private RepositorioCliente repositorioCliente;

    //Agregar Clientes
    @Transactional
    public Clientes registrarCliente(Clientes cliente) {
        Optional<Clientes> clienteExistente = repositorioCliente.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        } else {
            return repositorioCliente.save(cliente);
        }
    }


    //Eliminar clientes
    @Transactional
    public void eliminarCliente(Clientes cliente) {
        Optional<Clientes> clienteExistente = repositorioCliente.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent()) {
            repositorioCliente.delete(clienteExistente.get());
        }


    }

    //Mostrar clientes
    public Optional <Clientes> listarClientes(String email){
        return repositorioCliente.findByEmail(email);
    }



    //Modificar Clientes
    @Transactional
    public Clientes modificarCliente(Long id, Clientes clienteActualizado) {

        Clientes clienteExistente = repositorioCliente.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrado"));


        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());



        return repositorioCliente.save(clienteExistente);
    }




}
