package com.alcity.repository.base;

import com.alcity.entity.base.DataType;
import com.alcity.entity.base.WalletItemType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface WalletItemTypeRepository  extends CrudRepository<WalletItemType,Long> {
    Optional<WalletItemType> findById(Long id);
    Collection<WalletItemType> findAll();
    WalletItemType findByLabel(String label);
    WalletItemType findByValue(String value);

}
