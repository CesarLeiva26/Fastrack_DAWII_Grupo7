package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Cliente;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Orden;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.TipoEstadoOrden;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Vehiculo;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.OrdenRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.ClienteRepository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.OrdenRepository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.TipoEstadoOrdenRepository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.VehiculoRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdenService {

    private  OrdenRepository ordenRepository;
    private  ClienteRepository clienteRepository;
    private  TipoEstadoOrdenRepository tipoEstadoOrdenRepository;
    private  VehiculoRepository vehiculoRepository;



    @Transactional
    public Boolean guardarOrden(OrdenRequest ordenRequest) {
        try {
            Cliente cliente = clienteRepository.findById(ordenRequest.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            TipoEstadoOrden tipoEstadoOrden = tipoEstadoOrdenRepository.findById(ordenRequest.getIdTipoEstadoOrden())
                    .orElseThrow(() -> new RuntimeException("Tipo de estado de orden no encontrado"));

            Vehiculo vehiculo = vehiculoRepository.findById(ordenRequest.getIdVehiculo())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
            Orden orden = new Orden();
            orden.setCliente(cliente);
            orden.setFechacreacion(new Timestamp(System.currentTimeMillis()));
            orden.setQuienrecepciona(ordenRequest.getQuienRecepciona());
            orden.setTrack(ordenRequest.getTrack());
            orden.setVehiculo(vehiculo);
            orden.setTipoEstadoOrden(tipoEstadoOrden);
            Orden nuevaOrden = ordenRepository.save(orden);
            return nuevaOrden.getIdorden() != null && nuevaOrden.getIdorden() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la orden");
        }
    }

    public List<Orden> listarOrdenes(){
        return ordenRepository.findAll();
    }
}