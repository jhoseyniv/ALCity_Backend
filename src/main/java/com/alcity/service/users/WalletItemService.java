package com.alcity.service.users;

import com.alcity.entity.users.WalletItem;
import com.alcity.repository.users.WalletItemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class WalletItemService implements WalletItemRespository {

    @Autowired
    WalletItemRespository walletItemRespository;


    @Override
    public <S extends WalletItem> S save(S entity) {
        return walletItemRespository.save(entity);
    }

    @Override
    public <S extends WalletItem> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WalletItem> findById(Long id) {
        return walletItemRespository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<WalletItem> findAll() {
        return walletItemRespository.findAll();
    }

    @Override
    public Iterable<WalletItem> findAllById(Iterable<Long> longs) {
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
    public void delete(WalletItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WalletItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public WalletItem findByLabel(String label) {
        return walletItemRespository.findByLabel(label);
    }

    @Override
    public WalletItem findByValue(String value) {
        return walletItemRespository.findByValue(value);
    }
}
