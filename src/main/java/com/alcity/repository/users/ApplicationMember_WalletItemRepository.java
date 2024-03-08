package com.alcity.repository.users;

import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationMember_WalletItemRepository extends CrudRepository<ApplicationMember_WalletItem,Long> {
    Optional<ApplicationMember_WalletItem> findById(Long id);
    Collection<ApplicationMember_WalletItem> findAll();
    Collection<ApplicationMember_WalletItem> findByAmount(String amount);

}
