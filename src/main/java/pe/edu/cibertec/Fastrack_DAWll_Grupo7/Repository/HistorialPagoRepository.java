package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.HistorialPago;

@Repository
public interface HistorialPagoRepository extends JpaRepository<HistorialPago,Integer>{
}
