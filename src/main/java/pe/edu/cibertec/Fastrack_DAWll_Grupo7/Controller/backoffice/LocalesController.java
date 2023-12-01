package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Controller.backoffice;

import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Locales;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.LocalRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.response.ResultadoResponse;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service.LocalesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/locales")
public class LocalesController {

    private LocalesService localesService;

    @GetMapping("")
    public String frmLocales(Model model){
        model.addAttribute("listaLocales", localesService.listarLocales());
        return "backoffice/locales/frmLocales";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Locales> listarLocales(){
        return localesService.listarLocales();
    }


    @GetMapping("/buscarPorLetra")
    public String buscarPorLetra(@RequestParam(required = false) String letra, Model model) {
        List<Locales> locales;
        if (letra == null || letra.isEmpty() || letra.equalsIgnoreCase("todos")) {
            locales = localesService.listarLocales();
        } else {
            locales = localesService.buscarPorLetra(letra);
        }
        model.addAttribute("listaLocales", locales);
        return "backoffice/locales/frmLocales";
    }


    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarLocales(@RequestBody LocalRequest localRequest) {
        return localesService.guardarLocales(localRequest);
    }

}
