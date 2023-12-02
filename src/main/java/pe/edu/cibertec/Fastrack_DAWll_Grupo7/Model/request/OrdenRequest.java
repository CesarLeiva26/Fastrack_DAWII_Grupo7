package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request;

import lombok.Data;

@Data
public class OrdenRequest {

    private Integer idOrden;
    private Integer idCliente;
    private String quienRecepciona;
    private String track;
    private Integer idVehiculo;
    private Integer idTipoEstadoOrden;
}