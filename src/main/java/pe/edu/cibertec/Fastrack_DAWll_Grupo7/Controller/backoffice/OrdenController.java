package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Orden;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.OrdenRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.response.ResultadoResponse;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service.OrdenService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/orden")
public class OrdenController {

    private final OrdenService ordenService;

    @GetMapping("")
   public String listarOrden(Model model){
       model.addAttribute("listaOrdenes", ordenService.listarOrdenes());
       return "backoffice/orden/frmOrden";
   }

    @PostMapping("/guardarorden")
    @ResponseBody
    public ResultadoResponse guardarOrden(@RequestBody OrdenRequest ordenRequest) {
        String mensaje = "";
        Boolean respuesta;
        respuesta = ordenService.guardarOrden(ordenRequest);
        mensaje = respuesta ? "Se guardó correctamente la orden" : "No se pudo guardar la orden";
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}