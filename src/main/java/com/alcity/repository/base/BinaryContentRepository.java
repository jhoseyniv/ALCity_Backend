package com.alcity.repository.base;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.ClientType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface BinaryContentRepository extends CrudRepository<BinaryContent,Long> {
    Optional<BinaryContent> findById(Long id);
    Collection<BinaryContent> findAll();
    BinaryContent findByfileName(String label);
    Collection<BinaryContent> findBySize(String value);

}
