package com.alcity.repository.appmember;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletItemChangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletItemChangeRateRepository  extends CrudRepository<WalletItemChangeRate,Long> {
    Optional<WalletItemChangeRate> findById(Long id);

    Optional<WalletItemChangeRate> findByFromCurrencyAndToCurrency(WalletItem fromCurrency, WalletItem toCurrency);

}
