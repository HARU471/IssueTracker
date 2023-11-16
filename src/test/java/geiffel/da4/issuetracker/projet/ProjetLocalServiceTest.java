package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProjetLocalServiceTest {

    private List<Projet> projets = new ArrayList<Projet>();

    private ProjetService projetService;

    @BeforeEach
    void setUp(){
        projets.add(new Projet(1L, "Test"));
        projets.add(new Projet(2L, "Test"));
        projets.add(new Projet(3L, "Test"));
        projets.add(new Projet(4L, "Test"));

        projetService = new ProjetLocalService(projets);
    }

    @Test
    void whenGettingAll_shouldReturn4() {
        assertEquals(4, projetService.getAll().size());
    }

    @Test
    void whenGettingById_shouldReturnProject4() {
        assertEquals(projets.get(3), projetService.getById(4L));
    }

    @Test
    void whenCreate_shouldBeReturnTheSame() {
        Projet unProjet = new Projet(5L, "Test");
        assertEquals(unProjet, projetService.create(unProjet));
    }

    @Test
    void whenCreate_shouldBeAddedToTheList() {
        Projet unProjet = new Projet(5L, "Test");
        assertEquals(projetService.create(unProjet), projetService.getById(5L));
    }

    @Test
    void whenCreatingWithSameId_shouldThrowException() {
        Projet unProjet = new Projet(4L, "Test");
        assertThrows(ResourceAlreadyExistsException.class, ()->projetService.create(unProjet));
    }

    @Test
    void whenUpdating_shouldModifyProjet() {
        Projet initProjet = projets.get(2);
        Projet newProjet = new Projet(initProjet.getId(), "Updaté");

        projetService.update(newProjet.getId(), newProjet);
        Projet updated_projet = projetService.getById(initProjet.getId());
        assertEquals(newProjet, updated_projet);
        assertTrue(projetService.getAll().contains(newProjet));
    }

    @Test
    void whenUpdatingNonExisting_shouldThrowException() {
        Projet projet = projets.get(2);

        assertThrows(ResourceNotFoundException.class, ()->projetService.update(75L, projet));
    }

    @Test
    void whenDeletingExistingProjet_shouldNotBeInProjetsAnymore() {
        Projet projet = projets.get(1);
        Long id = projet.getId();

        projetService.delete(id);
        assertFalse(projetService.getAll().contains(projet));
    }

    @Test
    void whenDeletingNonExisting_shouldThrowException() {
        Long id = 68L;

        assertThrows(ResourceNotFoundException.class, ()->projetService.delete(id));
    }

}
