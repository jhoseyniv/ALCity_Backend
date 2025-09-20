package com.alcity.repository.appmember;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface WalletItemRespository extends CrudRepository<WalletItem,Long> {
    Optional<WalletItem> findById(Long id);
    Collection<WalletItem> findAll();
    WalletItem findByLabel(String label);
    Optional<WalletItem> findByValue(String value);
    Optional<WalletItem> findByIcon(BinaryContent icon);
    Optional<WalletItem> findByBaseCurrency(boolean baseCurrency);
}
