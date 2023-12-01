package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "tipoestadosorden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEstadoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtipoestadoorden;
    @Column(name = "nombreestado", nullable = false)
    private String nombreEstado;
}
