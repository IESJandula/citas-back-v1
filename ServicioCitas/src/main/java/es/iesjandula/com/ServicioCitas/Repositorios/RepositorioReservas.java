package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioReservas extends JpaRepository<Reservas, Long> {
}
