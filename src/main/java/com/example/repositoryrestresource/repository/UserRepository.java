package com.example.repositoryrestresource.repository;

import com.example.repositoryrestresource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method will be exposed as a REST endpoint by Spring Data REST.
     * It allows you to find a User entity based on the firstName field.
     */

    User findByFirstName(@Param("firstName") String firstName);

}

