package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service;

import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Empleado;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.TipoEmpleado;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request.EmpleadoRequest;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.response.ResultadoResponse;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository.EmpleadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpleadoService {

    public EmpleadoRepository empleadoRepository;

    public List<Empleado> buscarPorLetra(String letra) {
        return empleadoRepository.findByNombreStartingWith(letra);
    }

    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    public ResultadoResponse guardarEmpleados(EmpleadoRequest empleado) {
        String mensaje = "Empleado registrado correctamente";
        Boolean respuesta = true;
        try {
            Empleado objEmpleado = new Empleado();
            if (empleado.getIdempleado() > 0){
                objEmpleado.setIdempleado(empleado.getIdempleado());
            }
            objEmpleado.setNombre(empleado.getNombre());
            objEmpleado.setNumerotelefono(empleado.getNumerotelefono());
            objEmpleado.setCorreoelectronico(empleado.getCorreoelectronico());
            TipoEmpleado tipoEmpleado = new TipoEmpleado();
            tipoEmpleado.setIdtipoempleado(empleado.getIdtipoempleado());
            objEmpleado.setTiposempleados(tipoEmpleado);
            empleadoRepository.save(objEmpleado);
        }
        catch (Exception e){
            mensaje = "Empleado no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
    public void eliminarEmpleado(Integer idempleado) {
        empleadoRepository.deleteById(idempleado);
    }
}