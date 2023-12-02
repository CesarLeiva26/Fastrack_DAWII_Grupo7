package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Vehiculo;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.VehiculoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VehiculoService {

    private VehiculoRepository vehiculoRepository;
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }
}
