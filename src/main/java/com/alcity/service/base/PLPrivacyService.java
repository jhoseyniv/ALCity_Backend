package com.alcity.service.base;

import com.alcity.dto.base.PLPrivacyDTO;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.PLPrivacyRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PLPrivacyService implements PLPrivacyRepository {

   @Autowired
   @Qualifier("PLPrivacyRepository")

   PLPrivacyRepository plPrivacyRepository;
    @Override
    public <S extends PLPrivacy> S save(S entity) {
        return plPrivacyRepository.save(entity);
    }

    @Override
    public <S extends PLPrivacy> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLPrivacy> findById(Long id) {
        return plPrivacyRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLPrivacy> findAll() {
        return plPrivacyRepository.findAll();
    }

    @Override
    public Iterable<PLPrivacy> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plPrivacyRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLPrivacy entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLPrivacy> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PLPrivacy findByLabel(String label) {

     return plPrivacyRepository.findByLabel(label);
    }

    @Override
    public PLPrivacy findByValue(String value) {
        return plPrivacyRepository.findByValue(value);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    public PLPrivacy save(PLPrivacyDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLPrivacy plPrivacy=null;

        if (code.equalsIgnoreCase("Save")) { //Save
            plPrivacy = new PLPrivacy(dto.getLabel(), dto.getValue(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            plPrivacyRepository.save(plPrivacy);
        }else{//edit
            Optional<PLPrivacy> plPrivacyOptional= plPrivacyRepository.findById(dto.getId());
            if(plPrivacyOptional.isPresent()) {
                plPrivacy = plPrivacyOptional.get();
                plPrivacy.setLabel(dto.getLabel());
                plPrivacy.setValue(dto.getValue());
                plPrivacy.setVersion(plPrivacy.getVersion()+1);
                plPrivacy.setUpdated(DateUtils.getNow());
                plPrivacyRepository.save(plPrivacy);
            }
        }


        return plPrivacy;
    }

}
