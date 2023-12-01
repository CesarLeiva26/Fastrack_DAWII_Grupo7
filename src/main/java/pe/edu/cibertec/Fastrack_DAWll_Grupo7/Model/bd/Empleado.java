package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idempleado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "numerotelefono")
    private String  numerotelefono;
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @ManyToOne
    @JoinColumn(name = "idtipoempleado")
    private TipoEmpleado tiposempleados;
}
