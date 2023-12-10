package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.dto.view.UserViewModel;
import bg.softuni.homefurniture.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User getUserByEmail(String email);

    List<User> findAllByOrderByCreatedOnDesc();
}
