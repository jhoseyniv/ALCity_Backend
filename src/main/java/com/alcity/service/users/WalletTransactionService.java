package com.alcity.service.users;


import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.repository.users.WalletItemRespository;
import com.alcity.repository.users.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class WalletTransactionService implements WalletTransactionRepository {

  @Autowired
  WalletTransactionRepository walletTransactionRepository;

  @Override
  public <S extends WalletTransaction> S save(S entity) {
    return walletTransactionRepository.save(entity);
  }

  @Override
  public <S extends WalletTransaction> Iterable<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<WalletTransaction> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public Collection<WalletTransaction> findAll() {
    return null;
  }

  @Override
  public Iterable<WalletTransaction> findAllById(Iterable<Long> longs) {
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
  public void delete(WalletTransaction entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends WalletTransaction> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public WalletItem findByTransactionDate(String label) {
    return null;
  }

  @Override
  public Collection<WalletTransaction> findByAmount(Float amount) {
    return null;
  }
}
