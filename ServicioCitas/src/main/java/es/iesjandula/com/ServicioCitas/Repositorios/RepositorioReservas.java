package es.iesjandula.com.ServicioCitas.Repositorios;

import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RepositorioReservas extends JpaRepository<Reservas, Long> {
    Optional<Reservas> findByFechaAndHorario(LocalDate fecha, LocalTime horario);

    List<Reservas> findByFecha(LocalDate fecha);
}

