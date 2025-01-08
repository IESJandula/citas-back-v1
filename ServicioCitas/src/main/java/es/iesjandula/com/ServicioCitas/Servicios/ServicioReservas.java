package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.DTO.ReservasDTO;
import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioReservas;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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

    // Hacer una reserva con manejo de horarios fijos
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

        // Definir las horas fijas disponibles
        List<String> horasDisponibles = List.of("08:00", "09:00", "10:00", "11:00", "12:00");
        // Inicialmente todas las horas están disponibles (true)
        List<Boolean> horasDisponiblesEstado = List.of(true, true, true, true, true);

        // Obtener las reservas en la fecha seleccionada
        List<Reservas> reservasEnMismaFecha = repositorioReservas.findByFecha(reservasDTO.getFecha());

        // Verificar las horas ocupadas
        for (Reservas reserva : reservasEnMismaFecha) {
            String horaReservada = reserva.getHorario().toString();  // Suponiendo que getHorario() devuelve LocalTime
            int index = horasDisponibles.indexOf(horaReservada); // Compara con la hora como String
            if (index != -1) {
                horasDisponiblesEstado.set(index, false); // Marcar como ocupada
            }
        }

        // Verificar si la hora seleccionada está disponible
        int indexSeleccionado = horasDisponibles.indexOf(reservasDTO.getHoraSeleccionada());
        if (indexSeleccionado == -1 || !horasDisponiblesEstado.get(indexSeleccionado)) {
            throw new IllegalArgumentException("La hora seleccionada ya ha sido reservada o no está disponible.");
        }

        // Convertir la hora seleccionada de String a LocalTime
        LocalTime horaSeleccionada = LocalTime.parse(reservasDTO.getHoraSeleccionada());  // Convertir a LocalTime

        // Crear la nueva reserva
        Reservas nuevaReserva = new Reservas();
        nuevaReserva.setCliente(optionalCliente.get());
        nuevaReserva.setServicio(optionalServicio.get());
        nuevaReserva.setFecha(reservasDTO.getFecha());
        nuevaReserva.setHorario(horaSeleccionada);  // Asignar la hora como LocalTime

        // Guardar la nueva reserva
        return repositorioReservas.save(nuevaReserva);
    }

    public List<Reservas> mostrarReservas(ReservasDTO reservasDTO) {
        return repositorioReservas.findAll();
    }


    // Método para comprobar si una hora está disponible
    private boolean esHoraDisponible(Reservas reserva, String horaSeleccionada) {
        List<String> horasDisponibles = reserva.getHorasDisponibles();
        List<Boolean> horasDisponiblesEstado = reserva.getHorasDisponiblesEstado();

        int index = horasDisponibles.indexOf(horaSeleccionada);
        if (index != -1) {
            return horasDisponiblesEstado.get(index); // Si la hora está disponible (true) o no (false)
        }
        return false; // Si la hora no existe en la lista, considerarla no disponible
    }

    public List<Reservas> findAll() {
        return repositorioReservas.findAll();
    }

    public List<Reservas> encontrarCitasPorFecha(LocalDate fecha) {
        return repositorioReservas.findByFecha(fecha);
    }

    public void guardarReserva(Reservas reserva) {
        repositorioReservas.save(reserva); // Guarda la reserva en la base de datos
    }
}


