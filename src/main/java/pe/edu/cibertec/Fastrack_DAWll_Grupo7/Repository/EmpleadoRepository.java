package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    List<Empleado> findByNombre(String nombre);

}
