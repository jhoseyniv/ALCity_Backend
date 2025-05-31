package com.alcity.service.appmember;


import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.appmember.WalletItemTransactionDTO;
import com.alcity.entity.alenum.WalletTransactionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.entity.base.MemberType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.appmember.WalletTransactionRepository;
import com.alcity.utility.DateUtils;
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

  @Autowired
  private AppMemberRepository appMemberRepository;

  @Autowired
  private WalletItemRespository walletItemRespository;


  @Override
  public <S extends WalletTransaction> S save(S entity) {
    return walletTransactionRepository.save(entity);
  }

  public WalletTransaction save(WalletItemTransactionDTO dto, String code) {
    Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
    WalletTransactionType transactionType = WalletTransactionType.getByTitle(dto.getWalletTransactionType());
    Optional<WalletItem> walletItem = walletItemRespository.findById(dto.getWalletItemId());
    WalletTransaction transaction = new WalletTransaction(DateUtils.getNow(), dto.getAmount(), dto.getIncTransaction(),dto.getDescription(),walletItem.get(),
            dto.getCounterpartyId(), transactionType,1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
    walletTransactionRepository.save(transaction);
    return  transaction;
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
