package geiffel.da4.issuetracker.projet;

import java.util.List;

public interface ProjetService {
    List<Projet> getAll();

    Projet getById(Long l);

    Projet create(Projet test);

    void update(Long id, Projet newProjet);

    void delete(Long id);
}
