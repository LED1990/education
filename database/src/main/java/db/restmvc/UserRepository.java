package db.restmvc;

import model.restmvc.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query("select u from User u join fetch u.accounts acc join fetch acc.contacts where u.name = :name")
    Optional<User> findUserByNameJpql(@Param("name") String name);

//    @EntityGraph(attributePaths = {"accounts"})//version without @NamedEntityGraph
    @EntityGraph(value = "graph.UserAccountContacts")//version with @NamedEntityGraph - defined on entity
    Optional<User> findByLastName(String lastName);
}
