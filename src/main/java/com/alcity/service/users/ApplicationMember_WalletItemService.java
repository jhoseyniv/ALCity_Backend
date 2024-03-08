package com.alcity.service.users;


import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.repository.users.ApplicationMember_WalletItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ApplicationMember_WalletItemService implements ApplicationMember_WalletItemRepository {


    @Autowired
    ApplicationMember_WalletItemRepository applicationMember_WalletItemRepository;

    @Override
    public <S extends ApplicationMember_WalletItem> S save(S entity) {
        return applicationMember_WalletItemRepository.save(entity);
    }

    @Override
    public <S extends ApplicationMember_WalletItem> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ApplicationMember_WalletItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ApplicationMember_WalletItem> findAll() {
        return null;
    }

    @Override
    public Iterable<ApplicationMember_WalletItem> findAllById(Iterable<Long> longs) {
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
    public void delete(ApplicationMember_WalletItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ApplicationMember_WalletItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ApplicationMember_WalletItem> findByAmount(String amount) {
        return null;
    }
}
