package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historialpagos")
public class HistorialPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;
    @Column(name = "fechapago")
    private Date fechapago;
    @Column(name = "montopagado")
    private Double montopagado;
    @Column(name = "metodopago")
    private String metodopago;
    @Column(name = "estadopago")
    private String estadopago;
    @OneToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;


}
