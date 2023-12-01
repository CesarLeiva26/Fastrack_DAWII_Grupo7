package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Locales;

import java.util.List;

@Repository
public interface LocalesRepository extends JpaRepository<Locales, Integer> {
    List<Locales> findByNombrelocalStartingWith(String letra);
}
