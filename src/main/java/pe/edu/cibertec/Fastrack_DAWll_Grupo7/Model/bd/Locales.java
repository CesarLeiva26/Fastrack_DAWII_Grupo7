package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "locales")
@Entity

public class Locales {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer idlocal;
    @Column(name = "nombrelocal")
    private String nombrelocal;
    @Column(name = "direccionlocal")
    private String direccionlocal;
    @Column(name = "ciudadlocal")
    private String ciudadlocal;
    @Column(name = "codigopostal")
    private String codigopostal;
    @Column(name = "telefonolocal")
    private String telefonolocal;
    @ManyToOne
    @JoinColumn(name = "idempleado")
    private Empleado empleado;
}