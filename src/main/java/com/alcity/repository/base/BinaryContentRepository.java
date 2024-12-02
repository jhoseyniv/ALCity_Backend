package com.alcity.repository.base;

import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.base.BinaryContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface BinaryContentRepository extends CrudRepository<BinaryContent,Long> {
    Optional<BinaryContent> findById(Long id);
    Collection<BinaryContent> findAll();
    BinaryContent findByfileName(String label);
    Collection<BinaryContent> findBySize(Integer size);
    Collection<BinaryContent> findByTag1OrTag2OrTag3OrFileNameAndContentType(String tag1, String tag2, String tag3, String fileName, BinaryContentType contentType);

}
