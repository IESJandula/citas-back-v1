package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.Entidades.Horarios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioHorarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioHorarios {

    @Autowired
    private RepositorioHorarios repositorioHorarios;

    //Metodos CRUD

    public Horarios añadirHorario(Horarios horario) {
        return repositorioHorarios.save(horario);

    }
    public List<Horarios> listarHorarios() {
        return repositorioHorarios.findAll();
    }

    public Optional<Horarios> buscarHorario(Long id) {
        return repositorioHorarios.findById(id);
    }

    public Horarios cambiarHorario(Long id, Horarios horarioCambiado) {
        Horarios horario = repositorioHorarios.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el horario"));

        horario.setDiaSemana(horarioCambiado.getDiaSemana());
        horario.setHoraInicio(horarioCambiado.getHoraInicio());
        horario.setHoraFin(horarioCambiado.getHoraFin());

        return repositorioHorarios.save(horario);
    }
    public void eliminarHorario(Long id) {
       Horarios horaio = repositorioHorarios.findById(id)
               .orElseThrow(() -> new RuntimeException("No existe el horario"));
       repositorioHorarios.delete(horaio);
    }


}


