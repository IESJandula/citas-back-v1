package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPagos extends JpaRepository<Pagos, Long> {
}
