package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioServicios;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicios {

    @Autowired
    private RepositorioServicios repositorioServicios;

    @Transactional
    public Servicios añadirServicio(Servicios servicio) {
        return repositorioServicios.save(servicio);
    }
    public List<Servicios> listarServicios() {
        return repositorioServicios.findAll();
    }
    public Optional<Servicios> buscarServicio(Long id) {
        return repositorioServicios.findById(id);

    }

    @Transactional
    public void eliminarServicio(Long id) {
        Servicios servicio= repositorioServicios.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el servicio"));
        repositorioServicios.delete(servicio);
    }
    @Transactional
    public Servicios modificarServicio(Long id, Servicios servicioNuevo) {
        Servicios servicio= repositorioServicios.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el servicio"));

        servicio.setNombre(servicioNuevo.getNombre());
        servicio.setDuracion(servicioNuevo.getDuracion());
        servicio.setPrecio(servicioNuevo.getPrecio());
        return repositorioServicios.save(servicio);

    }
}
