package com.alcity.repository.base;

import com.alcity.entity.base.BinaryContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface BinaryContentRepository extends CrudRepository<BinaryContent,Long> {
    Optional<BinaryContent> findById(Long id);
    Collection<BinaryContent> findAll();
    BinaryContent findByfileName(String label);
    Collection<BinaryContent> findBySize(Integer size);
    Collection<BinaryContent> findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(String fileName,String tag1, String tag2, String tag3);

}
