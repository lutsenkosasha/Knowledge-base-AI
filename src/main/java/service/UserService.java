package service;

import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public User createUser(User user) {
        User saved = userRepository.save(user);
        auditLogService.log("User", "CREATE", "User created with id: " + saved.getUserId());
        return saved;
    }

    @Transactional
    public List<User> createMultipleUsers(List<User> users) {
        List<User> saved = userRepository.saveAll(users);
        auditLogService.log("User", "BATCH_CREATE", saved.size() + " users created.");
        return saved;
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setDepartment(updatedUser.getDepartment());
            user.setPost(updatedUser.getPost());
            User saved = userRepository.save(user);
            auditLogService.log("User", "UPDATE", "User updated with id: " + saved.getUserId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        auditLogService.log("User", "DELETE", "User deleted with id: " + id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}