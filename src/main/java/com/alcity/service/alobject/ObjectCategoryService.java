package com.alcity.service.alobject;

import com.alcity.dto.alobject.ObjectCategoryDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.alobject.ObjectCategoryRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ObjectCategoryService implements ObjectCategoryRepository {

    @Autowired
    ObjectCategoryRepository objectCategoryRepository;

    @Override
    public <S extends ObjectCategory> S save(S entity) {
        return objectCategoryRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    public ObjectCategory save(ObjectCategoryDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        ObjectCategory objectCategory=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            objectCategory = new ObjectCategory(dto.getLabel(), dto.getValue(), 1L,
                    DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            objectCategoryRepository.save(objectCategory);
        }else{//edit
            Optional<ObjectCategory> objectCategoryOptional= objectCategoryRepository.findById(dto.getId());
            if(objectCategoryOptional.isPresent()) {
                objectCategory = objectCategoryOptional.get();
                objectCategory.setLabel(dto.getLabel());
                objectCategory.setValue(dto.getValue());
                objectCategory.setVersion(objectCategory.getVersion()+1);
                objectCategory.setUpdated(DateUtils.getNow());
                objectCategory.setUpdatedBy(createdBy.get());
                objectCategoryRepository.save(objectCategory);
            }
        }
        return objectCategory;
    }

    @Override
    public <S extends ObjectCategory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectCategory> findById(Long id) {
        if(id == null) return Optional.empty();
        return objectCategoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectCategory> findAll() {
        return objectCategoryRepository.findAll();
    }

    @Override
    public Iterable<ObjectCategory> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        objectCategoryRepository.deleteById(aLong);
    }

    @Override
    public void delete(ObjectCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ObjectCategory findByLabel(String label) {
        return objectCategoryRepository.findByLabel(label);
    }

    @Override
    public ObjectCategory findByValue(String value) {
        return objectCategoryRepository.findByValue(value);
    }
}
