package es.iesjandula.com.ServicioCitas.Controladores;

import es.iesjandula.com.ServicioCitas.DTO.ReservasDTO;
import es.iesjandula.com.ServicioCitas.Entidades.Reservas;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioReservas;
import es.iesjandula.com.ServicioCitas.Servicios.ServicioReservas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ControladorReservas {

    @Autowired
    private RepositorioReservas repositorioReservas;

    @Autowired
    private ServicioReservas servicioReservas;

    @GetMapping
    public List<Reservas> listaReservas() {
      return  repositorioReservas.findAll();
    }
    @PostMapping("/agregar")
    public void hacerReserva(@RequestBody ReservasDTO ReservaDTO) {
        servicioReservas.hacerReserva(ReservaDTO);
    }
    @GetMapping("/citasPorFecha")
    public List<Reservas> mostrarReservaPorFecha(@RequestParam LocalDate fecha){
      return  repositorioReservas.findByFecha(fecha);
    }
}
