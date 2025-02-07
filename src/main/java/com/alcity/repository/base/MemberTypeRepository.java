package com.alcity.repository.base;

import com.alcity.dto.appmember.MemberTypeDTO;
import com.alcity.entity.base.MemberType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface MemberTypeRepository extends CrudRepository<MemberType,Long> {
    Optional<MemberType> findById(Long id);
    Collection<MemberType> findAll();
    MemberType findByLabel(String label);
    Optional<MemberType> findByValue(String value);
    MemberType save(MemberTypeDTO memberTypeDTO);

}
