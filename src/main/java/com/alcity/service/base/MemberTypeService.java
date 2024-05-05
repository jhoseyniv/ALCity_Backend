package com.alcity.service.base;


import com.alcity.dto.user.MemberTypeDTO;
import com.alcity.entity.base.MemberType;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.base.MemberTypeRepository;
import com.alcity.service.users.ApplicationMemberService;
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
    ApplicationMemberService applicationMemberService;

    @Override
    public <S extends MemberType> S save(S entity) {
        return memberTypeRepository.save(entity);
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
    public MemberType findByValue(String value) {
        return null;
    }

    @Override
    public MemberType save(MemberTypeDTO memberTypeDTO) {
        Optional<ApplicationMember> createdBy = applicationMemberService.findById(memberTypeDTO.getCreatedById());
        Optional<ApplicationMember> updatedBy = applicationMemberService.findById(memberTypeDTO.getUpdatedById());

        MemberType memberType = new MemberType(memberTypeDTO.getLabel(), memberTypeDTO.getValue(), memberTypeDTO.getVersion(),
                memberTypeDTO.getCreated(), memberTypeDTO.getUpdated(), createdBy.get(), updatedBy.get());
        memberTypeRepository.save(memberType);

        return memberType;
    }
}
