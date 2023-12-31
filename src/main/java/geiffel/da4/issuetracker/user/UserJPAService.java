package geiffel.da4.issuetracker.user;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
@Primary
public class UserJPAService implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserJPAService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    @Override
    public User create(User newUser) throws ResourceAlreadyExistsException {
        Optional<User> user = userRepository.findById(newUser.getId());
        if(user.isPresent()) {
            throw new ResourceAlreadyExistsException("User", newUser.getId());
        } else {

            userRepository.save(newUser);
        }
        return newUser;
    }

    @Override
    public void update(Long id, User updatedUser) throws ResourceNotFoundException {
        if(userRepository.existsById(id)) {
            userRepository.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }
}
