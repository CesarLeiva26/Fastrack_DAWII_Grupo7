package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Empleado;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Locales;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.LocalRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.response.ResultadoResponse;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.LocalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LocalesService {

    public LocalesRepository localesRepository;

    public List<Locales> listarLocales(){
        return localesRepository.findAll();
    }

    public List<Locales> buscarPorLetra(String letra) {
        return localesRepository.findByNombrelocalStartingWith(letra);
    }

    public void eliminarLocales(Integer idlocal){
         localesRepository.deleteById(idlocal);
    }

    public ResultadoResponse guardarLocales(LocalRequest local) {
        String mensaje = "Local registrado correctamente";
        Boolean respuesta = true;
        try {
            Locales objLocal = new Locales();
            if (local.getIdlocal() != null && local.getIdlocal() > 0) {
                objLocal.setIdlocal(local.getIdlocal());
            }
            objLocal.setNombrelocal(local.getNombrelocal());
            objLocal.setDireccionlocal(local.getDireccionlocal());
            objLocal.setCiudadlocal(local.getCiudadlocal());
            objLocal.setCodigopostal(local.getCodigopostal());
            objLocal.setTelefonolocal(local.getTelefonolocal());
            Empleado empleado = new Empleado();
            empleado.setIdempleado(local.getIdempleado());
            objLocal.setEmpleado(empleado);
            localesRepository.save(objLocal);
        } catch (Exception e) {
            mensaje = "Usuario no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}
