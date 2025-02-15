package com.alcity.repository.base;

import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.alcity.entity.base.BinaryContent;

import java.io.IOException;
import java.util.Collection;

public interface BinaryContentCustom {
  //  BinaryContent save(String fileName, MultipartFile file) throws IOException;
    void removeForeignKeys(Long id) throws IOException;
  public Collection<BinaryContent> findByCriteria(ContentSearchCriteriaDTO dto);

}
