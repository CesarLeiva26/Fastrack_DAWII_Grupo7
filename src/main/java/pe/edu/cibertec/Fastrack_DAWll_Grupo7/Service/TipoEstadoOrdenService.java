package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.TipoEstadoOrden;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.TipoEstadoOrdenRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoEstadoOrdenService {

    private TipoEstadoOrdenRepository tipoEstadoOrdenRepository;

    public List<TipoEstadoOrden> listarTiposEstadoOrden() {
        return tipoEstadoOrdenRepository.findAll();
    }
}
