package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProjetLocalService extends LocalService<Projet, Long> implements ProjetService {

    public ProjetLocalService(List<Projet> allValues) {
        super(allValues);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return super.getAll();
    }

    @Override
    public Projet getById(Long l) {
        return super.getByIdentifier(l);
    }

    @Override
    public Projet create(Projet projet) throws ResourceAlreadyExistsException {
        try {
            this.findByProperty(projet.getId());
            throw new ResourceAlreadyExistsException("Projet", projet.getId());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(projet);
            return projet;
        }
    }

    @Override
    public void update(Long id, Projet newProjet) throws ResourceNotFoundException {
        IndexAndValue<Projet> found = this.findById(id);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), newProjet);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        IndexAndValue<Projet> found = this.findById(id);
        this.allValues.remove(found.index());
    }

    public IndexAndValue<Projet> findById(Long id) {
        return super.findByProperty(id);
    }
}
