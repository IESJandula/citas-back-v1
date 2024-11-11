package es.iesjandula.com.ServicioCitas.Controladores;

import es.iesjandula.com.ServicioCitas.Entidades.Horarios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioHorarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class ControladorHorarios {

    @Autowired
    private RepositorioHorarios repositorioHorarios;

    @GetMapping
    public List<Horarios> listaHorarios() {
        return repositorioHorarios.findAll();
    }

    @GetMapping("/buscar_id")
    public Horarios obtenerHorarios(@RequestParam Long id) {
        return repositorioHorarios.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el horario"));
    }
    @PostMapping("/ingresar")
    public Horarios ingresarHorarios(@RequestBody Horarios horarios) {
        return repositorioHorarios.save(horarios);
    }
    @PutMapping("/modificar")
    public Horarios modificarHorarios(@RequestParam Long id, @RequestBody Horarios horarioNuevo) {
        Horarios horario = repositorioHorarios.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el horario"));

        horario.setDiaSemana(horarioNuevo.getDiaSemana());
        horario.setHoraInicio(horarioNuevo.getHoraInicio());
        horario.setHoraFin(horarioNuevo.getHoraFin());
        return repositorioHorarios.save(horario);

    }
    @DeleteMapping("/eliminar")
    public void eliminarHorarios(@RequestParam Long id) {
        repositorioHorarios.deleteById(id);
    }
}
