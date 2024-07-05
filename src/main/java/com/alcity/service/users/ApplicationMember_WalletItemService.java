package com.alcity.service.users;


import com.alcity.entity.users.AppMember;
import com.alcity.entity.users.AppMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.repository.users.ApplicationMember_WalletItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ApplicationMember_WalletItemService implements ApplicationMember_WalletItemRepository {


    @Autowired
    ApplicationMember_WalletItemRepository applicationMember_WalletItemRepository;

    @Override
    public <S extends AppMember_WalletItem> S save(S entity) {
        return applicationMember_WalletItemRepository.save(entity);
    }

    @Override
    public <S extends AppMember_WalletItem> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember_WalletItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<AppMember_WalletItem> findByApplicationMemberAndWalletItem(AppMember applicationMember, WalletItem walletItem) {
        return applicationMember_WalletItemRepository.findByApplicationMemberAndWalletItem(applicationMember,walletItem);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AppMember_WalletItem> findAll() {
        return null;
    }

    @Override
    public Iterable<AppMember_WalletItem> findAllById(Iterable<Long> longs) {
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
    public void delete(AppMember_WalletItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember_WalletItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<AppMember_WalletItem> findByAmount(Float amount) {
        return null;
    }
}
