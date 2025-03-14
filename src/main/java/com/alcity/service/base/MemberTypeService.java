package com.alcity.service.base;


import com.alcity.dto.appmember.MemberTypeDTO;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.base.MemberType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.WalletItemType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.MemberTypeRepository;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional

public class MemberTypeService implements MemberTypeRepository {

    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public <S extends MemberType> S save(S entity) {
        return memberTypeRepository.save(entity);
    }

    public MemberType save(MemberTypeDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType=null;
        Optional<MemberType> memberTypeOptional= memberTypeRepository.findByValue(dto.getValue());
        if (code.equalsIgnoreCase("Save")) { //Save
            memberType = new MemberType(dto.getLabel(),dto.getValue(), 1L,
                    DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            memberTypeRepository.save(memberType);
        }else{//edit
            memberTypeOptional= memberTypeRepository.findById(dto.getId());
            if(memberTypeOptional.isPresent()) {
                memberType = memberTypeOptional.get();
                memberType.setLabel(dto.getLabel());
                memberType.setValue(dto.getValue());
                memberType.setVersion(memberType.getVersion()+1);
                memberType.setUpdated(DateUtils.getNow());
                memberType.setUpdatedBy(createdBy.get());
                memberTypeRepository.save(memberType);
            }
        }
        return memberType;
    }

    @Override
    public <S extends MemberType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MemberType> findById(Long id) {
        return memberTypeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<MemberType> findAll() {
        return memberTypeRepository.findAll();
    }

    @Override
    public Iterable<MemberType> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        memberTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(MemberType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MemberType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public MemberType findByLabel(String label) {
        return null;
    }

    @Override
    public Optional<MemberType> findByValue(String value) {
        return Optional.empty();
    }


    @Override
    public MemberType save(MemberTypeDTO memberTypeDTO) {
        Optional<AppMember> createdBy = appMemberRepository.findById(memberTypeDTO.getCreatedById());
        Optional<AppMember> updatedBy = appMemberRepository.findById(memberTypeDTO.getUpdatedById());

        MemberType memberType = new MemberType(memberTypeDTO.getLabel(), memberTypeDTO.getValue(), memberTypeDTO.getVersion(),
                memberTypeDTO.getCreated(), memberTypeDTO.getUpdated(), createdBy.get(), updatedBy.get());
        memberTypeRepository.save(memberType);

        return memberType;
    }
}
