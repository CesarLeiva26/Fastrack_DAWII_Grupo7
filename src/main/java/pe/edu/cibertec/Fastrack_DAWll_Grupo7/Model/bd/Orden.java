package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idorden;
    @Column(name = "fechacreacion")
    private Date fechacreacion;
    @Column(name = "quienrecepciona")
    private String quienrecepciona;
    @Column(name = "track")
    private String track;
    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    private Vehiculo vehiculo;
    @ManyToOne
    @JoinColumn(name = "idestadoorden")
    private TipoEstadoOrden tipoEstadoOrden;
}