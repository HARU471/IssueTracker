package geiffel.da4.issuetracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/issues")
public class IssueController  {
    private IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("")
    public List<Issue> getAll(){
        return issueService.getAll();
    }

    @GetMapping("{code}")
    public Issue getIssueByCode(@PathVariable("code") Long value) {

        return issueService.getByCode(value);
    }

    @PostMapping("")
    public ResponseEntity<Issue> createIssue(Issue issue){
        Issue created = issueService.create(issue);
        return ResponseEntity.created(URI.create("/issues/"+created.getCode().toString())).build();
    }

    @PutMapping("{code}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long code, @RequestBody Issue issue){
        issueService.update(code, issue);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Issue> deleteIssue(@PathVariable Long id){
        issueService.delete(id);
        return ResponseEntity.noContent().build();
    }

}