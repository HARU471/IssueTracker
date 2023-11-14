package geiffel.da4.issuetracker.commentaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    @Query(value = "SELECT c FROM Commentaire c WHERE c.author.id=:id")
    List<Commentaire> getAllByAuthorId(@Param("id") Long id);

    @Query(value = "SELECT c FROM Commentaire c WHERE c.issue.code=:id")
    List<Commentaire> getAllByIssueId(@Param("id") Long id);

}
