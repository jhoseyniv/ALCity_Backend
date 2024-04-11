package com.alcity.repository.users;

import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApplicationMember_WalletItemRepository extends CrudRepository<ApplicationMember_WalletItem,Long> {
    Optional<ApplicationMember_WalletItem> findById(Long id);
    Optional<ApplicationMember_WalletItem> findByApplicationMemberAndWalletItem(ApplicationMember applicationMember, WalletItem walletItem);
    Collection<ApplicationMember_WalletItem> findAll();
    Collection<ApplicationMember_WalletItem> findByAmount(String amount);

}
