package com.alcity.service.appmember;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppMember_WalletItemService implements AppMember_WalletItemRepository {


    @Autowired
    AppMember_WalletItemRepository appMember_WalletItemRepository;

    @Override
    public <S extends AppMember_WalletItem> S save(S entity) {
        return appMember_WalletItemRepository.save(entity);
    }

    @Override
    public <S extends AppMember_WalletItem> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember_WalletItem> findById(Long id) {
        if(id == null){ return Optional.empty(); }
        return appMember_WalletItemRepository.findById(id);
    }

    @Override
    public Optional<AppMember_WalletItem> findByApplicationMemberAndWalletItem(AppMember applicationMember, WalletItem walletItem) {
        return appMember_WalletItemRepository.findByApplicationMemberAndWalletItem(applicationMember,walletItem);
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
