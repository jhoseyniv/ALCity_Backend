package com.alcity.service.base;

import com.alcity.entity.base.BinaryContent;
import com.alcity.repository.base.BinaryContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BinaryContentService implements BinaryContentRepository {

    @Autowired
    BinaryContentRepository binaryContentRepository;
    @Override
    public <S extends BinaryContent> S save(S entity) {
        return binaryContentRepository.save(entity);
    }

    @Override
    public <S extends BinaryContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BinaryContent> findById(Long id) {
        return binaryContentRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<BinaryContent> findAll() {
        return null;
    }

    @Override
    public Iterable<BinaryContent> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BinaryContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BinaryContent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public BinaryContent findByfileName(String fileName) {
        return binaryContentRepository.findByfileName(fileName);
    }

    @Override
    public Collection<BinaryContent> findBySize(String value) {
        return null;
    }
}
