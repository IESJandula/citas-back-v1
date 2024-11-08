
package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioCliente extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findByEmail(String email);


}
