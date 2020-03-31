package db.jms;

import model.jms.SimpleJmsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleJmsMessageRepository extends JpaRepository<SimpleJmsMessage, Long>{
}
