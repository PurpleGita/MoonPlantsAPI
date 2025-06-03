package tec.fullmoonsapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModule, Long> {
}
