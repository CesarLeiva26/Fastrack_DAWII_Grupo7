package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "envios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idenvio;

    @ManyToOne
    @JoinColumn(name = "idorden", referencedColumnName = "idorden")
    private Orden orden;
}