package com.alcity.service.alobject;

import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.repository.alobject.ALCityAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ALCityAttributeService implements ALCityAttributeRepository {

    @Autowired
    ALCityAttributeRepository ALCityAttributeRepository;

    @Override
    public <S extends ALCityAttribute> S save(S entity) {
        return ALCityAttributeRepository.save(entity);
    }

    @Override
    public <S extends ALCityAttribute> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityAttribute> findById(Long id) {

        return ALCityAttributeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityAttribute> findAll() {
        return ALCityAttributeRepository.findAll();
    }

    @Override
    public Iterable<ALCityAttribute> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityAttribute entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityAttribute> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityAttribute> findByName(String name) {
        return null;
    }

    @Override
    public Collection<ALCityAttribute> findByOwnerId(Long ownerId) {
        return ALCityAttributeRepository.findByOwnerId(ownerId);
    }


    @Override
    public Collection<ALCityAttribute> findByOwnerIdAndAttributeOwnerType(Long instanceId,AttributeOwnerType ownerType) {
        Collection<ALCityAttribute> alCityAttributes = ALCityAttributeRepository.findByOwnerId(instanceId);

        ArrayList<ALCityAttribute> outputAttributes = new ArrayList<ALCityAttribute>();

        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable) {
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if(ownerType == AttributeOwnerType.PuzzleGroup_ObjectInstance_Property)
                    outputAttributes = alCityAttributes.stream().
                            filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.PuzzleGroup_ObjectInstance_Property))
                            .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));




            return outputAttributes;
    }


}
