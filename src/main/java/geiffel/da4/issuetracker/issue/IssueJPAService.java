package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class IssueJPAService implements IssueService {

    private IssueRepository issueRepository;

    private static final String ISSUE = "Issue";  // Compliant
    @Autowired
    public IssueJPAService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new ResourceNotFoundException(ISSUE, id);
        }
    }

    @Override
    public Issue create(Issue newIssue) {
        if(issueRepository.existsById(newIssue.getCode())){
            throw new ResourceAlreadyExistsException(ISSUE, newIssue.getCode());
        } else {
            return issueRepository.save(newIssue);
        }
    }

    @Override
    public void update(Long code, Issue updatedIssue) {
        if(issueRepository.existsById(code)) {
            issueRepository.save(updatedIssue);
        } else {
            throw new ResourceNotFoundException(ISSUE, code);
        }
    }

    @Override
    public void delete(Long code) {
        if(issueRepository.existsById(code)) {
            issueRepository.deleteById(code);
        } else {
            throw new ResourceNotFoundException(ISSUE, code);
        }
    }
}
