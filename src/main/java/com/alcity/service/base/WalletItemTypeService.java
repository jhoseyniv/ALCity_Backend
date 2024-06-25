package com.alcity.service.base;


import com.alcity.entity.base.WalletItemType;
import com.alcity.repository.base.WalletItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class WalletItemTypeService implements WalletItemTypeRepository {

    @Autowired
    WalletItemTypeRepository  walletItemTypeRepository;
    @Override
    public <S extends WalletItemType> S save(S entity) {
        return walletItemTypeRepository.save(entity);
    }

    @Override
    public <S extends WalletItemType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WalletItemType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<WalletItemType> findAll() {
        return walletItemTypeRepository.findAll();
    }

    @Override
    public Iterable<WalletItemType> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        walletItemTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(WalletItemType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WalletItemType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public WalletItemType findByLabel(String label) {
        return null;
    }

    @Override
    public WalletItemType findByValue(String value) {
        return null;
    }
}
