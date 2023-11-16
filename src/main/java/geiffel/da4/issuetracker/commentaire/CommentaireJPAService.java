package geiffel.da4.issuetracker.commentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CommentaireJPAService implements CommentaireService {
    private CommentaireRepository commentaireRepository;
    @Autowired
    public CommentaireJPAService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if (commentaire.isPresent()) {
            return commentaire.get();
        } else {
            throw new ResourceNotFoundException("Commentaire", id);
        }
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        return commentaireRepository.getAllByAuthorId(id);
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        return commentaireRepository.getAllByIssueId(code);
    }

    @Override
    public Commentaire create(Commentaire commentaire6) {
        if(commentaireRepository.existsById(commentaire6.getId())){
            throw new ResourceAlreadyExistsException("Issue", commentaire6.getId());
        } else {
            return commentaireRepository.save(commentaire6);
        }
    }

    @Override
    public void update(Long id, Commentaire update) {
        if(commentaireRepository.existsById(id)) {
            commentaireRepository.save(update);
        } else {
            throw new ResourceNotFoundException("Commentaire", id);
        }
    }

    @Override
    public void delete(Long id) {
        commentaireRepository.deleteById(id);
    }
}
