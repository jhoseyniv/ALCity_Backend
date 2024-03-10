package com.alcity.service.base;

import com.alcity.entity.base.BinaryContentType;
import com.alcity.repository.base.BinaryContentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BinaryContentTypeService implements BinaryContentTypeRepository {

   @Autowired
    BinaryContentTypeRepository binaryContentTypeRepository;
    @Override
    public <S extends BinaryContentType> S save(S entity) {
        return binaryContentTypeRepository.save(entity);
    }

    @Override
    public <S extends BinaryContentType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BinaryContentType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<BinaryContentType> findAll() {
        return null;
    }

    @Override
    public Iterable<BinaryContentType> findAllById(Iterable<Long> longs) {
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
    public void delete(BinaryContentType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BinaryContentType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public BinaryContentType findByLabel(String label) {
        return null;
    }

    @Override
    public BinaryContentType findByValue(String value) {
        return null;
    }
}
