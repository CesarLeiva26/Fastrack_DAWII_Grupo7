package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNomrol(String nomrol);
}
