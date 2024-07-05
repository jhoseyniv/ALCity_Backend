package com.alcity.service.base;


import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.users.AppMember;
import com.alcity.repository.base.WalletItemTypeRepository;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.utility.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;;
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
    private ApplicationMemberRepository applicationMemberRepository;

    public WalletItemType save(WalletItemTypeDTO dto, String code) {
        AppMember createdBy = applicationMemberRepository.findByUsername("admin");
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
