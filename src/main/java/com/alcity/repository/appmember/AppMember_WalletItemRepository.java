package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AppMember_WalletItemRepository extends CrudRepository<AppMember_WalletItem,Long> {
    Optional<AppMember_WalletItem> findById(Long id);
    Optional<AppMember_WalletItem> findByApplicationMemberAndWalletItem(AppMember applicationMember, WalletItem walletItem);
    Collection<AppMember_WalletItem> findAll();
    Collection<AppMember_WalletItem> findByAmount(Float amount);

}
