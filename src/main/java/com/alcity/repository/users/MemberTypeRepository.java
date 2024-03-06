package com.alcity.repository.users;

import com.alcity.entity.users.MemberType;
import com.alcity.entity.users.UserGender;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface MemberTypeRepository extends CrudRepository<MemberType,Long> {
    Optional<MemberType> findById(Long id);
    Collection<MemberType> findAll();
    MemberType findByLabel(String label);
    MemberType findByValue(String value);

}
