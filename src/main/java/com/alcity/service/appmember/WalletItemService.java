package com.alcity.service.appmember;

import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.WalletItemTypeRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class WalletItemService implements WalletItemRespository {

    @Autowired
    WalletItemRespository walletItemRespository;

    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    WalletItemTypeRepository walletItemTypeRepository;
    @Autowired
    BinaryContentRepository binaryContentRepository;

    public WalletItem save(WalletItemDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<WalletItemType> walletItemType = walletItemTypeRepository.findByValue(dto.getWalletItemType());
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        if(icon.isEmpty()) {
            //save defualt binary content....
        }
        WalletItem walletItem=null;
        Optional<WalletItem> walletItemOptional= walletItemRespository.findByValue(dto.getValue());

        if (code.equalsIgnoreCase("Save")) { //Save
            walletItem = new WalletItem(walletItemType.get() ,icon.get(),dto.getLabel(),dto.getValue() , 1L,
                    DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            walletItemRespository.save(walletItem);
        }else{//edit
            walletItemOptional= walletItemRespository.findById(dto.getId());
            if(walletItemOptional.isPresent()) {
                walletItem = walletItemOptional.get();
                walletItem.setWalletItemType(walletItemType.get());
                walletItem.setLabel(dto.getLabel());
                walletItem.setValue(dto.getValue());
                walletItem.setIcon(icon.get());
                walletItem.setVersion(walletItem.getVersion()+1);
                walletItem.setUpdated(DateUtils.getNow());
                walletItem.setUpdatedBy(createdBy);
                walletItemRespository.save(walletItem);
            }
        }
        return walletItem;
    }

    @Override
    public <S extends WalletItem> S save(S entity) {
        return walletItemRespository.save(entity);
    }

    @Override
    public <S extends WalletItem> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WalletItem> findById(Long id) {
        return walletItemRespository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<WalletItem> findAll() {
        return walletItemRespository.findAll();
    }

    @Override
    public Iterable<WalletItem> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        walletItemRespository.deleteById(aLong);
    }

    @Override
    public void delete(WalletItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WalletItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public WalletItem findByLabel(String label) {
        return walletItemRespository.findByLabel(label);
    }

    @Override
    public Optional<WalletItem> findByValue(String value) {
        return walletItemRespository.findByValue(value);
    }

    @Override
    public Optional<WalletItem> findByIcon(BinaryContent icon) {
        return walletItemRespository.findByIcon(icon);
    }


}
