package com.alcity.repository.users;

import com.alcity.entity.users.UserGender;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserGenderRepository extends CrudRepository<UserGender,Long> {
    Optional<UserGender> findById(Long id);
    Collection<UserGender> findAll();
    UserGender findByLabel(String label);
    UserGender findByValue(String value);

}
