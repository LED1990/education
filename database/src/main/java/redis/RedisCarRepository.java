package redis;

import model.redis.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisCarRepository extends JpaRepository<Car, Long> {
}
