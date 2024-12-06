package com.alcity.service.base;


import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.WalletItemTypeRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class WalletItemTypeService implements WalletItemTypeRepository {

    @Autowired
    WalletItemTypeRepository  walletItemTypeRepository;
    @Override
    public <S extends WalletItemType> S save(S entity) {
        return walletItemTypeRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    public WalletItemType save(WalletItemTypeDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        WalletItemCategory walletItemCategory = WalletItemCategory.getByTitle(dto.getWalletItemCategory());
        WalletItemType walletItemType=null;
        Optional<WalletItemType> walletItemTypeOptional= walletItemTypeRepository.findByValue(dto.getValue());

        if (code.equalsIgnoreCase("Save")) { //Save
            walletItemType = new WalletItemType(walletItemCategory ,dto.getLabel(),dto.getValue(),dto.getCurrency() , 1L,
                    DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            walletItemTypeRepository.save(walletItemType);
        }else{//edit
            walletItemTypeOptional= walletItemTypeRepository.findById(dto.getId());
            if(walletItemTypeOptional.isPresent()) {
                walletItemType = walletItemTypeOptional.get();
                walletItemType.setWalletItemCategory(walletItemCategory);
                walletItemType.setLabel(dto.getLabel());
                walletItemType.setValue(dto.getValue());
                walletItemType.setCurrency(dto.getCurrency());
                walletItemType.setVersion(walletItemType.getVersion()+1);
                walletItemType.setUpdated(DateUtils.getNow());
                walletItemType.setUpdatedBy(createdBy);
                walletItemTypeRepository.save(walletItemType);
            }
        }
        return walletItemType;
    }

    @Override
    public <S extends WalletItemType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WalletItemType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<WalletItemType> findAll() {
        return walletItemTypeRepository.findAll();
    }

    @Override
    public Iterable<WalletItemType> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        walletItemTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(WalletItemType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WalletItemType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public WalletItemType findByLabel(String label) {
        return null;
    }

    @Override
    public Optional<WalletItemType> findByValue(String value) {
        return walletItemTypeRepository.findByValue(value);
    }


}
