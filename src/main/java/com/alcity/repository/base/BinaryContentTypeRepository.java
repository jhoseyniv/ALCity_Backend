package com.alcity.repository.base;

import com.alcity.entity.base.BinaryContentType;
import com.alcity.entity.base.ClientType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface BinaryContentTypeRepository extends CrudRepository<BinaryContentType,Long> {
    Optional<BinaryContentType> findById(Long id);
    Collection<BinaryContentType> findAll();
    BinaryContentType findByLabel(String label);
    BinaryContentType findByValue(String value);

}
