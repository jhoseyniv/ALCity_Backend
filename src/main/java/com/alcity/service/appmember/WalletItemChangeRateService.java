package com.alcity.service.appmember;


import com.alcity.dto.appmember.WalletItemChangeRateDTO;
import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletItemChangeRate;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.WalletItemType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.WalletItemChangeRateRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WalletItemChangeRateService implements WalletItemChangeRateRepository {

    WalletItemChangeRateRepository walletItemChangeRateRepository;

    @Autowired
    WalletItemRespository walletItemRespository;

    @Autowired
    private AppMemberRepository appMemberRepository;


    @Override
    public <S extends WalletItemChangeRate> S save(S entity) {
        return null;
    }

    @Override
    public <S extends WalletItemChangeRate> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
    public WalletItemChangeRate save(WalletItemChangeRateDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        WalletItemChangeRate changeRate =null;
        if (code.equalsIgnoreCase("Save")) { //Save
            changeRate = new WalletItemChangeRate(dto.getFromDate() , dto.getRate(), dto.getFromCurrencyId(),dto.getToCurrencyId(),
                    1L,DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            walletItemChangeRateRepository.save(changeRate);
        }else{//edit
            Optional<WalletItemChangeRate> changeRateOptional= walletItemChangeRateRepository.findById(dto.getId());
            if(changeRateOptional.isPresent()) {
                changeRate = changeRateOptional.get();
                changeRate.setFromCurrency(dto.getFromCurrencyId());
                changeRate.setRate(dto.getRate());
                changeRate.setToCurrency(dto.getToCurrencyId());
                changeRate.setVersion(changeRate.getVersion()+1);
                changeRate.setCreated(DateUtils.getNow());
                changeRate.setUpdated(DateUtils.getNow());
                changeRate.setUpdatedBy(createdBy.get());
                changeRate.setCreatedBy(createdBy.get());
                walletItemChangeRateRepository.save(changeRate);
            }
        }
        return changeRate;
    }

    @Override
    public Optional<WalletItemChangeRate> findById(Long id) {
        return walletItemChangeRateRepository.findById(id);
    }

    @Override
    public Optional<WalletItemChangeRate> findByFromCurrencyAndToCurrency(WalletItem fromCurrency, WalletItem toCurrency) {
        return walletItemChangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency,toCurrency);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<WalletItemChangeRate> findAll() {
        return null;
    }

    @Override
    public Iterable<WalletItemChangeRate> findAllById(Iterable<Long> longs) {
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
    public void delete(WalletItemChangeRate entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WalletItemChangeRate> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
