package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Cliente;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.HistorialPago;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.ClienteRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HistorialPagoService historialPagoService;



    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }


    @Transactional
    public Boolean guadarCliente(ClienteRequest clienteRequest){
        Cliente cliente = new Cliente();
        cliente.setIdcliente(clienteRequest.getIdcliente());
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setDireccion(clienteRequest.getDireccion());
        cliente.setNumerotelefono(clienteRequest.getNumerotelefono());
        cliente.setCorreoelectronico(clienteRequest.getCorreoelectronico());
        Cliente nuevocliente = clienteRepository.save(cliente);
        HistorialPago historialPago = new HistorialPago();
        historialPago.setIdpago(clienteRequest.getIdpago());
        historialPago.setFechapago(clienteRequest.getFechapago());
        historialPago.setMetodopago(clienteRequest.getMetodopago());
        historialPago.setMontopagado(clienteRequest.getMontopagado());
        historialPago.setEstadopago(clienteRequest.getEstadopago());
        historialPago.setIdpago(clienteRequest.getIdcliente());
        historialPago.setCliente(cliente);
        historialPagoService.registrar(historialPago);
        return nuevocliente.getIdcliente()>0;
    }
    public Cliente actualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Cliente eliminar(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }
        return cliente;
    }
}