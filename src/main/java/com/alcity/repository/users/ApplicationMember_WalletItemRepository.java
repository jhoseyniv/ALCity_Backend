package com.alcity.repository.users;

import com.alcity.entity.users.AppMember;
import com.alcity.entity.users.AppMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationMember_WalletItemRepository extends CrudRepository<AppMember_WalletItem,Long> {
    Optional<AppMember_WalletItem> findById(Long id);
    Optional<AppMember_WalletItem> findByApplicationMemberAndWalletItem(AppMember applicationMember, WalletItem walletItem);
    Collection<AppMember_WalletItem> findAll();
    Collection<AppMember_WalletItem> findByAmount(Float amount);

}
