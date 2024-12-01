package com.alcity.repository.base;

import com.alcity.dto.base.SearchCriteriaDTO;
import com.alcity.entity.base.BinaryContent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface BinaryContentCustom {
  //  BinaryContent save(String fileName, MultipartFile file) throws IOException;
    void removeForeignKeys(Long id) throws IOException;
  public Collection<BinaryContent> findByCriteria(SearchCriteriaDTO dto);

}
