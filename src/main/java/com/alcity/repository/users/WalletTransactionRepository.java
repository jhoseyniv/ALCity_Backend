package com.alcity.repository.users;

import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface WalletTransactionRepository extends CrudRepository<WalletTransaction,Long> {

    Optional<WalletTransaction> findById(Long id);
    Collection<WalletTransaction> findAll();
    WalletItem findByTransactionDate(String label);
    Collection<WalletTransaction> findByAmount(Float amount);

}
