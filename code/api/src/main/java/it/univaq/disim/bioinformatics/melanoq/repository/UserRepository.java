package it.univaq.disim.bioinformatics.melanoq.repository;

import it.univaq.disim.bioinformatics.melanoq.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findOneByUsername(String username);

    User save(User user);


}
