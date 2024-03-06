package com.alcity.repository.users;

import com.alcity.entity.users.ClientType;
import com.alcity.entity.users.MemberType;
import com.alcity.entity.users.UserGender;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ClientTypeRepository extends CrudRepository<ClientType,Long> {
    Optional<ClientType> findById(Long id);
    Collection<ClientType> findAll();
    ClientType findByLabel(String label);
    ClientType findByValue(String value);

}
