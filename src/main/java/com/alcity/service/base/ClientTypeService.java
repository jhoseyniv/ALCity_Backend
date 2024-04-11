package com.alcity.service.base;

import com.alcity.entity.base.ClientType;
import com.alcity.repository.base.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ClientTypeService  implements ClientTypeRepository {
    @Autowired
    private ClientTypeRepository clientTypeRepository;

    @Override
    public <S extends ClientType> S save(S entity) {
        return clientTypeRepository.save(entity);
    }

    @Override
    public <S extends ClientType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ClientType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ClientType> findAll() {
        return clientTypeRepository.findAll();
    }

    @Override
    public Iterable<ClientType> findAllById(Iterable<Long> longs) {
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
    public void delete(ClientType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ClientType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ClientType findByLabel(String label) {
        return clientTypeRepository.findByLabel(label);
    }

    @Override
    public ClientType findByValue(String value) {
        return clientTypeRepository.findByValue(value);
    }
}
