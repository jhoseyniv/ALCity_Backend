package com.alcity.repository.appmember;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.ALCityObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface WalletItemRespository extends CrudRepository<WalletItem,Long> {
    Optional<WalletItem> findById(Long id);
    Collection<WalletItem> findAll();
    WalletItem findByLabel(String label);
    Optional<WalletItem> findByValue(String value);
    Optional<WalletItem> findByIcon(BinaryContent icon);

}
