package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Vehiculo;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service.VehiculoService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/vehiculo")
public class VehiculoController {


    private VehiculoService vehiculoService;

    @GetMapping("/listarVehiculos")
    @ResponseBody
    public List<Vehiculo> listaVehiculo(){
        return vehiculoService.listarVehiculos();
    }
}
