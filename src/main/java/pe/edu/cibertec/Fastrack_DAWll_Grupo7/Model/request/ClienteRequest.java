package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteRequest {


    private Integer idcliente;
    private Integer idpago;
    private String nombre;
    private String direccion;
    private String numerotelefono;
    private String correoelectronico;
    private Date fechapago;
    private Double montopagado;
    private String metodopago;
    private String estadopago;




}
