package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.DTO.ReservasDTO;
import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioReservas;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioServicios;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioReservas {

    @Autowired
    private RepositorioReservas repositorioReservas;
    @Autowired
    private RepositorioCliente repositorioCliente;
    @Autowired
    private RepositorioServicios repositorioServicios;

    // Listar reservas
    public List<Reservas> findAll() {
        return repositorioReservas.findAll();
    }

    // Encontrar reserva por ID
    public Optional<Reservas> findById(Long id) {
        return repositorioReservas.findById(id);
    }

    // Hacer una reserva
    @Transactional
    public Reservas hacerReserva(ReservasDTO reservasDTO) {
        // Verificar que el cliente existe
        Optional<Clientes> optionalCliente = repositorioCliente.findById(reservasDTO.getCliente_id());
        if (optionalCliente.isEmpty()) {
            throw new IllegalArgumentException("El cliente con el ID: " + reservasDTO.getCliente_id() + " no existe.");
        }

        // Verificar que el servicio existe
        Optional<Servicios> optionalServicio = repositorioServicios.findById(reservasDTO.getServicio_id());
        if (optionalServicio.isEmpty()) {
            throw new IllegalArgumentException("El servicio con el ID: " + reservasDTO.getServicio_id() + " no existe.");
        }

        // Obtener las reservas existentes para la misma fecha y servicio
        List<Reservas> reservasEnMismaFecha = repositorioReservas.findByFecha(reservasDTO.getFecha()); // Filtrar por la misma fecha

        for (Reservas reserva : reservasEnMismaFecha) {
            // Verificar si hay solapamiento entre la nueva reserva y una existente
            boolean existeReservaHora =
                    (reservasDTO.getHoraInicio().isBefore(reserva.getHoraFin()) && reservasDTO.getHoraFin().isAfter(reserva.getHoraInicio())) ||
                            (reservasDTO.getHoraInicio().equals(reserva.getHoraInicio()) || reservasDTO.getHoraFin().equals(reserva.getHoraFin())) ||
                            (reservasDTO.getHoraInicio().isAfter(reserva.getHoraInicio()) && reservasDTO.getHoraFin().isBefore(reserva.getHoraFin()));

            if (existeReservaHora && reserva.getServicio().getServicio_id().equals(reservasDTO.getServicio_id())) {
                throw new IllegalArgumentException("Ya existe una reserva para este servicio en la misma fecha y hora.");
            }
        }

        // Crear la nueva reserva
        Reservas nuevaReserva = new Reservas();
        nuevaReserva.setCliente(optionalCliente.get());
        nuevaReserva.setHoraInicio(reservasDTO.getHoraInicio());
        nuevaReserva.setHoraFin(reservasDTO.getHoraFin());
        nuevaReserva.setFecha(reservasDTO.getFecha());
        nuevaReserva.setServicio(optionalServicio.get());

        // Guardar la nueva reserva
        return repositorioReservas.save(nuevaReserva);
    }

    // Actualizar una reserva
    @Transactional
    public Reservas actualizarReserva(Long reserva_id, ReservasDTO reservasDTO) {
        // Comprobamos que la reserva existe
        Reservas reserva = repositorioReservas.findById(reserva_id).orElseThrow(() -> new RuntimeException("No se encontró el registro de la reserva"));

        // Verificamos si el nuevo servicio que introducimos existe
        if (reservasDTO.getServicio_id() != null) {
            Optional<Servicios> optionalServicio = repositorioServicios.findById(reservasDTO.getServicio_id());
            if (optionalServicio.isEmpty()) {
                throw new IllegalArgumentException("El servicio con el ID: " + reservasDTO.getServicio_id() + " no existe.");
            }
            // Asignar el nuevo servicio si es válido
            reserva.setServicio(optionalServicio.get());
        }

        // Verificamos si el nuevo cliente existe
        if (reservasDTO.getCliente_id() != null) {
            Optional<Clientes> optionalCliente = repositorioCliente.findById(reservasDTO.getCliente_id());
            if (optionalCliente.isEmpty()) {
                throw new IllegalArgumentException("El cliente con el ID: " + reservasDTO.getCliente_id() + " no existe.");
            }
            // Si el cliente existe, actualizamos la reserva con el nuevo cliente
            reserva.setCliente(optionalCliente.get());
        }

        // Verificamos que no haya conflictos con la nueva fecha y hora
        if (reservasDTO.getHoraInicio() != null || reservasDTO.getFecha() != null) {
            List<Reservas> reservasConflicto = repositorioReservas.findByFecha(reserva.getFecha());
            for (Reservas r : reservasConflicto) {
                boolean existeReservaHora =
                        (reservasDTO.getHoraInicio() != null && reservasDTO.getHoraInicio().isBefore(r.getHoraFin()) &&
                                reservasDTO.getHoraFin().isAfter(r.getHoraInicio())) ||
                                (reservasDTO.getFecha() != null && reservasDTO.getFecha().equals(r.getFecha()) &&
                                        reservasDTO.getHoraInicio().equals(r.getHoraInicio()));

                if (existeReservaHora && r.getServicio().getServicio_id().equals(reserva.getServicio().getServicio_id()) && !r.getReserva_id().equals(reserva.getReserva_id())) {
                    throw new IllegalArgumentException("Ya existe una reserva para este servicio en la misma fecha y hora.");
                }
            }

            // Actualizamos la fecha y la hora si no hay conflictos
            if (reservasDTO.getFecha() != null) {
                reserva.setFecha(reservasDTO.getFecha());
            }
            if (reservasDTO.getHoraInicio() != null) {
                reserva.setHoraInicio(reservasDTO.getHoraInicio());
            }
        }

        return repositorioReservas.save(reserva);
    }

    // Eliminar una reserva
    @Transactional
    public void eliminarReserva(Long reserva_id) {
        // Paso 1: Verificar que la reserva existe
        Reservas reserva = repositorioReservas.findById(reserva_id)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de la reserva con ID: " + reserva_id));

        // Paso 2: Eliminar la reserva
        repositorioReservas.delete(reserva);
    }

    // Buscar reservas por fecha
    public List<Reservas> encontrarCitasPorFecha(LocalDate fecha) {
        return repositorioReservas.findByFecha(fecha);
    }
}
