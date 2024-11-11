package es.iesjandula.com.ServicioCitas.Servicios;

import es.iesjandula.com.ServicioCitas.Entidades.Pagos;
import es.iesjandula.com.ServicioCitas.Repositorios.RepositorioPagos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPagos {

    @Autowired
    private RepositorioPagos repositorioPagos;

    //RegistrarPago
    @Transactional
    public Pagos registrarPago(Pagos pago) {
        repositorioPagos.save(pago);
        return pago;
    }
    //ListarPagos
    public List<Pagos> listarPagos() {
        return repositorioPagos.findAll();
    }
    @Transactional
    //Actualizar Datos de un pago
    public Pagos actualizarPago(Long pago_id, Pagos nuevoPago){
        Pagos pago = repositorioPagos.findById(pago_id).orElseThrow(() -> new RuntimeException("No se encontró el registro del pago"));
        pago.setReserva(nuevoPago.getReserva());
        pago.setCantidad(nuevoPago.getCantidad());
        pago.setFecha_pago(nuevoPago.getFecha_pago());
        pago.setMetodo_pago(nuevoPago.getMetodo_pago());
        return repositorioPagos.save(pago);
    }
    @Transactional
    //Eliminar registro del pago
    public void eliminarPago(Long pago_id){
        Pagos pago = repositorioPagos.findById(pago_id).orElseThrow(()-> new RuntimeException("No se encontró el registro"));
        repositorioPagos.deleteById(pago_id);
    }

}