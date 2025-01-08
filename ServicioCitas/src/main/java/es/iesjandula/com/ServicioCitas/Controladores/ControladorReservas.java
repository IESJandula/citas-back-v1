package es.iesjandula.com.ServicioCitas.Controladores;

import es.iesjandula.com.ServicioCitas.DTO.ReservasDTO;
import es.iesjandula.com.ServicioCitas.Entidades.Clientes;
import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioCliente;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioReservas;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen // Permite CORS solo desde este origen
public class ControladorReservas {

    @Autowired
    private ServicioReservas servicioReservas;

    @Autowired
    private ServicioCliente servicioClientes;

    @Autowired
    private ServicioServicios servicioServicios;  // Servicio para manejar los servicios

    // Lista de horas fijas predefinidas
    private static final List<String> HORAS_FIJAS = Arrays.asList("09:00", "10:00", "11:00", "12:00", "13:00", "17:00","18:00","19:00","20:00");

    @PostMapping
    public ResponseEntity<String> realizarReserva(@RequestBody ReservaRequest reservaRequest) {
        // Validamos que el cliente existe
        Clientes cliente = servicioClientes.buscarClientePorId(reservaRequest.getClienteId());
        if (cliente == null) {
            return ResponseEntity.status(400).body("Cliente no encontrado.");
        }

        // Validamos que la hora seleccionada es una hora fija
        if (!HORAS_FIJAS.contains(reservaRequest.getHorario())) {
            return ResponseEntity.status(400).body("La hora seleccionada no es válida.");
        }

        // Validamos que el servicio existe
        Servicios servicio = servicioServicios.buscarServicioPorId(reservaRequest.getServicio());
        if (servicio == null) {
            return ResponseEntity.status(400).body("Servicio no encontrado.");
        }

        // Convertimos la fecha y hora a los tipos correspondientes
        LocalDate fecha = LocalDate.parse(reservaRequest.getFecha());
        LocalTime horario = LocalTime.parse(reservaRequest.getHorario());

        // Creamos la nueva reserva
        Reservas nuevaReserva = new Reservas();
        nuevaReserva.setFecha(fecha);
        nuevaReserva.setHorario(horario);
        nuevaReserva.setCliente(cliente); // Asociamos el cliente encontrado
        nuevaReserva.setServicio(servicio); // Asociamos el servicio encontrado

        // Guardamos la reserva
        servicioReservas.guardarReserva(nuevaReserva);

        // Respondemos con un mensaje de éxito
        return ResponseEntity.ok("Reserva realizada exitosamente!");

    }

    @GetMapping("/mostrartodas")
    public List<Reservas> mostrarReservas(ReservasDTO reservasDTO) {
        return servicioReservas.mostrarReservas(reservasDTO);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<String>> obtenerHorasOcupadas(@RequestParam String fecha) {
        // Convertimos la fecha que recibimos como String a LocalDate
        LocalDate fechaSeleccionada = LocalDate.parse(fecha);

        // Obtenemos las reservas para esa fecha
        List<Reservas> reservas = servicioReservas.encontrarCitasPorFecha(fechaSeleccionada);

        // Creamos una lista con las horas ocupadas
        List<String> horasOcupadas = new ArrayList<>();
        for (Reservas reserva : reservas) {
            horasOcupadas.add(reserva.getHorario().toString());
        }

        // Devolvemos la lista de horas ocupadas
        return ResponseEntity.ok(horasOcupadas);
    }

    // Clase interna para la solicitud
    public static class ReservaRequest {
        private Long clienteId;  // El ID del cliente registrado
        private String fecha;
        private String horario;
        private Long servicio;  // El ID del servicio seleccionado

        // Getters y setters
        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getHorario() {
            return horario;
        }

        public void setHorario(String horario) {
            this.horario = horario;
        }

        public Long getServicio() {
            return servicio;
        }

        public void setServicio(Long servicio) {
            this.servicio = servicio;
        }
    }
}





