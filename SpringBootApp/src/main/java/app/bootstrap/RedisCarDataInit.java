package app.bootstrap;

import lombok.extern.slf4j.Slf4j;
import model.redis.Car;
import model.redis.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.RedisCarRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class RedisCarDataInit implements CommandLineRunner{

    private RedisCarRepository redisCarRepository;

    @Autowired
    public RedisCarDataInit(RedisCarRepository redisCarRepository) {
        this.redisCarRepository = redisCarRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Bootstrapping car data [REDIS]");
        Wheel wheel = Wheel.builder().manyfacturer("wheel 1 manufacturer").size(10).build();
        Wheel wheel2 = Wheel.builder().manyfacturer("wheel 2 manufacturer 2").size(30).build();
        Wheel wheel3 = Wheel.builder().manyfacturer("wheel 3 manufacturer ").size(5).build();
        Wheel wheel4 = Wheel.builder().manyfacturer("wheel 4 manufacturer 4").size(57).build();

        Car car = Car.builder().id(10L).manufacturer("Honda").wheelList(Arrays.asList(wheel, wheel2)).build();
        Car car2 = Car.builder().id(11L).manufacturer("Audi").wheelList(Arrays.asList(wheel3, wheel4)).build();

        redisCarRepository.save(car);
        redisCarRepository.save(car2);

        List<Car> result = redisCarRepository.findAll();
        log.info("Results {} ", result);
    }
}
