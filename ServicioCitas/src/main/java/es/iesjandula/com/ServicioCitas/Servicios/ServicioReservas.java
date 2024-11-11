package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.DTO.ReservasDTO;
import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Pagos;
import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioCliente;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioReservas;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioServicios;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //Listar reservas
    public List<Reservas> findAll(){
        return repositorioReservas.findAll();
    }
    //Encontrar reserva por ID
    public Optional<Reservas> findById(Long id){
        return repositorioReservas.findById(id);
    }
    //Hacer una reserva
    @Transactional
    public Reservas hacerReserva(ReservasDTO reservasDTO){
        //Verificar que el cliente existe
        Optional<Clientes> optionalCliente = repositorioCliente.findById(reservasDTO.getCliente_id());
        if (optionalCliente.isEmpty()){
            throw new IllegalArgumentException("El cliente con el ID: "+ reservasDTO.getCliente_id()+" no existe.");
        }
        //Verificar que el servicio existe
        Optional<Servicios> optionalServicio = repositorioServicios.findById(reservasDTO.getServicio_id());
        if (optionalServicio.isEmpty()){
            throw new IllegalArgumentException("El servicio con el ID: "+reservasDTO.getServicio_id()+" no existe.");

        }
        //Comprobar que no haya conflicto en fecha y hora para el mismo servicio
        List<Reservas> reservasEnMismaFechaHora = repositorioReservas.findAll(); // Obtiene todas las reservas
        for (Reservas reserva : reservasEnMismaFechaHora) {
            if (reserva.getFecha().equals(reservasDTO.getFecha()) &&
                    reserva.getHora().equals(reservasDTO.getHora()) &&
                    reserva.getServicio().getServicio_id().equals(reservasDTO.getServicio_id())) {
                throw new IllegalArgumentException("Ya existe una reserva para este servicio en la misma fecha y hora.");
            }
        }

        //Ya cubierto los conflictos, creamos la reserva
        Reservas nuevaReserva = new Reservas();
        nuevaReserva.setCliente(optionalCliente.get());
        nuevaReserva.setHora(reservasDTO.getHora());
        nuevaReserva.setFecha(reservasDTO.getFecha());
        nuevaReserva.setServicio(optionalServicio.get());

        //Guardamos la reserva
        return repositorioReservas.save(nuevaReserva);
    }
    //Actualizar una reserva
    @Transactional
    public Reservas actualizarReserva(Long reserva_id, ReservasDTO reservasDTO){

        //Comprobamos que la reserva existe
        Reservas reserva = repositorioReservas.findById(reserva_id).orElseThrow(() -> new RuntimeException("No se encontró el registro de la reserva"));

        //Verificamos si el nuevo servicio que introducimos existe
        if (reservasDTO.getServicio_id() != null) {
            Optional<Servicios> optionalServicio = repositorioServicios.findById(reservasDTO.getServicio_id());
            if (optionalServicio.isEmpty()) {
                throw new IllegalArgumentException("El servicio con el ID: " + reservasDTO.getServicio_id() + " no existe.");
            }
            // Asignar el nuevo servicio si es válido
            reserva.setServicio(optionalServicio.get());
        }
        //También, por si la reserva cambia de cliente, añado la posibilidad de que se pueda cambiar, para ello, compruebo que el nuevo cliente existe.
        if (reservasDTO.getCliente_id() != null) {
            Optional<Clientes> optionalCliente = repositorioCliente.findById(reservasDTO.getCliente_id());
            if (optionalCliente.isEmpty()) {
                throw new IllegalArgumentException("El cliente con el ID: " + reservasDTO.getCliente_id() + " no existe.");
            }
            // Si el cliente existe, actualizamos la reserva con el nuevo cliente
            reserva.setCliente(optionalCliente.get());
        }
        //Tenemos que incluir la posiblidad de cambiar la cita a otra hora, para ello, hay que evitar conflictos con otras horas de otra fecha.
        if (reservasDTO.getHora() != null || reservasDTO.getFecha() != null) {
            // Si se proporciona una nueva hora o fecha, verificamos si ya hay una reserva con el mismo servicio
            List<Reservas> reservasConflicto = repositorioReservas.findAll(); // Obtener todas las reservas
            for (Reservas r : reservasConflicto) {
                boolean mismoDia = r.getFecha().equals(reserva.getFecha());
                boolean mismaHora = r.getHora().equals(reservasDTO.getHora());
                boolean mismoServicio = r.getServicio().getServicio_id().equals(reserva.getServicio().getServicio_id());
                boolean mismaReserva = r.getReserva_id().equals(reserva.getReserva_id());

                // Verificamos si hay una reserva en el mismo servicio y fecha/hora
                if (mismoDia && (mismaHora || reservasDTO.getHora() != null) && mismoServicio && !mismaReserva) {
                    throw new IllegalArgumentException("Ya existe una reserva para este servicio en la misma fecha y hora.");
                }
            }

            // Si no hay conflicto, actualizamos la fecha y/o la hora
            if (reservasDTO.getFecha() != null) {
                reserva.setFecha(reservasDTO.getFecha());
            }
            if (reservasDTO.getHora() != null) {
                reserva.setHora(reservasDTO.getHora());
            }
        }

        return repositorioReservas.save(reserva);
    }

    //Por último, añadimos un servicio para eliminar reservas
    @Transactional
    public void eliminarReserva(Long reserva_id) {
        // Paso 1: Verificar que la reserva existe
        Reservas reserva = repositorioReservas.findById(reserva_id)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de la reserva con ID: " + reserva_id));

        // Paso 2: Eliminar la reserva
        repositorioReservas.delete(reserva);
    }

}
