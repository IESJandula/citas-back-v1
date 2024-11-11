package es.iesjandula.com.ServicioCitas.Controladores;

import es.iesjandula.com.ServicioCitas.Entidades.Servicios;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ControladorServicios {

    @Autowired
    private RepositorioServicios repositorioServicios;

    @GetMapping
    public List<Servicios> listarServicios() {
        return repositorioServicios.findAll();
    }

    @GetMapping("/id")
    public Servicios buscarServicioId(Long id) {
        return repositorioServicios.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontro el servicio"));
    }
    @PostMapping("/ingresar")
    public Servicios crearServicio(@RequestBody Servicios servicio) {
        return repositorioServicios.save(servicio);
    }
    @PutMapping("/modificar")
    public Servicios modificarServicio(@RequestBody Servicios nuevoServicio,@PathVariable Long id) {
        Servicios servicio= repositorioServicios.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el servicio"));
        servicio.setNombre(nuevoServicio.getNombre());
        servicio.setDuracion(nuevoServicio.getDuracion());
        servicio.setPrecio(nuevoServicio.getPrecio());
        return repositorioServicios.save(servicio);

    }
    @DeleteMapping("/borrar")
    public void borrarServicio(@PathVariable Long id) {
        Servicios servicio= repositorioServicios.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el servicio"));
        repositorioServicios.delete(servicio);
    }

}
