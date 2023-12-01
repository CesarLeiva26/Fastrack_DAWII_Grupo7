package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Controller.backoffice;

import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Empleado;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.EmpleadoRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.response.ResultadoResponse;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service.EmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/empleado")
public class EmpleadoController {

    private EmpleadoService empleadoService;
    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam String nombre, Model model) {
        List<Empleado> empleados = empleadoService.buscarPorNombre(nombre);
        model.addAttribute("listaEmpleado", empleados);
        return "backoffice/empleado/frmEmpleado"; // Asegúrate de que este sea el nombre correcto de tu vista
    }

    @GetMapping("")
    public String frmEmpleado(Model model){
        model.addAttribute("listaEmpleado", empleadoService.listarEmpleados());
        return "backoffice/empleado/frmEmpleado";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }


    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarEmpleados(@RequestBody EmpleadoRequest empleadoRequest){
        return empleadoService.guardarEmpleados(empleadoRequest);
    }
}
