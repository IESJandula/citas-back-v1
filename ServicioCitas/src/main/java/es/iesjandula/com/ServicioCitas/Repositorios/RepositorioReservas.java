package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioReservas extends JpaRepository<Reservas, Long> {
    List<Reservas> findByFecha(LocalDate fecha);

}
