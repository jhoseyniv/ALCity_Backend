package com.alcity.service.alobject;

import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.repository.alobject.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttributeService implements AttributeRepository {

    @Autowired
    AttributeRepository attributeRepository;

    @Override
    public <S extends Attribute> S save(S entity) {
        return attributeRepository.save(entity);
    }

    @Override
    public <S extends Attribute> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Attribute> findById(Long id) {

        return attributeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Iterable<Attribute> findAllById(Iterable<Long> longs) {
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
    public void delete(Attribute entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Attribute> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<Attribute> findByName(String name) {
        return null;
    }

    @Override
    public Collection<Attribute> findByOwnerId(Long ownerId) {
        return attributeRepository.findByOwnerId(ownerId);
    }


    @Override
    public Collection<Attribute> findByOwnerIdAndAttributeOwnerType(Long instanceId, AttributeOwnerType ownerType) {
        Collection<Attribute> alCityAttributes = attributeRepository.findByOwnerId(instanceId);

        ArrayList<Attribute> outputAttributes = new ArrayList<Attribute>();

        if(ownerType == AttributeOwnerType.Puzzle_Level_Rule_Post_Action)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Rule_Post_Action))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable)
                outputAttributes = alCityAttributes.stream().
                        filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Variable))
                        .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.PuzzleGroup_Object_Instance_Property)
                    outputAttributes = alCityAttributes.stream().
                            filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.PuzzleGroup_Object_Instance_Property))
                            .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.PuzzleGroup_Object_Instance_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.PuzzleGroup_Object_Instance_Property))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Object_Action_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Object_Action_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.PuzzleGroup_Object_Instance_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.PuzzleGroup_Object_Instance_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));



        return outputAttributes;
    }


}
