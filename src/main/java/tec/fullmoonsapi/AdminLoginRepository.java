package tec.fullmoonsapi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminLoginRepository extends JpaRepository<AdminLogin, Long> {
    Optional<AdminLogin> findByUsernameAndPassword(String username, String password);
}