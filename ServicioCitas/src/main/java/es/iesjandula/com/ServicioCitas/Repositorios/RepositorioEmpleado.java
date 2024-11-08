package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioEmpleado extends JpaRepository<Empleados, Long> {

    Optional<Empleados> findByNombre(String nombre);



}
