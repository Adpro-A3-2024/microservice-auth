package id.ac.ui.cs.youkosu.microservice_auth.repository;

import id.ac.ui.cs.youkosu.microservice_auth.model.Role;
import id.ac.ui.cs.youkosu.microservice_auth.model.RoleEnum;
import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
