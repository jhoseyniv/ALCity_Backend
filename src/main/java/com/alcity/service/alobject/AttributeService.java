package com.alcity.service.alobject;

import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.alobject.AttributeValueDTO;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.alobject.AttributeRepository;
import com.alcity.repository.alobject.AttributeValueRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
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

    @Autowired
    AttributeValueRepository attributeValueRepository;

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
    public Collection<Attribute> findByOwnerIdAndAttributeOwnerType(Long ownerId, AttributeOwnerType ownerType) {
        Collection<Attribute> alCityAttributes = attributeRepository.findByOwnerId(ownerId);

        ArrayList<Attribute> outputAttributes = new ArrayList<Attribute>();

        if(ownerType == AttributeOwnerType.Puzzle_Level_Rule_Post_Action)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Rule_Post_Action))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable)
                outputAttributes = alCityAttributes.stream().
                        filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Variable))
                        .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Level_Instance_Property)
                    outputAttributes = alCityAttributes.stream().
                            filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Instance_Property))
                            .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Level_Instance_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Instance_Property))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Object_Action_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Object_Action_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Level_Instance_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Instance_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Action_Renderer_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Action_Renderer_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.AlCity_Object)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.AlCity_Object))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.ALCity_Object_In_Puzzle_Group)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.ALCity_Object_In_Puzzle_Group))
                    .collect(Collectors.toCollection(ArrayList::new));

        return outputAttributes;
    }

    @Override
    public Optional<Attribute> findByOwnerIdAndName(Long ownerId, String name) {
        return attributeRepository.findByOwnerIdAndName(ownerId,name);
    }
    @Autowired
    private AppMemberRepository applicationMemberRepository;
    public Attribute save(AttributeDTO dto, String code) {
        AppMember createdBy = applicationMemberRepository.findByUsername("admin");
        Optional<Attribute> attributeOptional =  attributeRepository.findById(dto.getId());
        AttributeOwnerType attributeOwnerType =  AttributeOwnerType.getByTitle(dto.getAttributeOwnerType());
        Collection<AttributeValueDTO> valueDTOS = dto.getAttributeValueDTOS();
        AttributeValueDTO valueDTO = new AttributeValueDTO();
        DataType dataType =  DataType.getByTitle(dto.getDataType());
        Attribute attribute=null;
        AttributeValue attributeValue=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            attribute = new Attribute(dto.getName(), dto.getOwnerId(),attributeOwnerType,dataType ,
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);

            attributeRepository.save(attribute);

            //save attribute value
            Iterator<AttributeValueDTO> itr = valueDTOS.iterator();
            if(itr.hasNext())   valueDTO = itr.next();
            attributeValue = new AttributeValue(valueDTO.getBooleanValue(),valueDTO.getIntValue(),valueDTO.getLongValue(),valueDTO.getStringValue(),
                    valueDTO.getObjectValue(),valueDTO.getDoubleValue(),valueDTO.getBinaryContentId(),attribute,attribute,
                    1L,DateUtils.getNow(),DateUtils.getNow(),createdBy,createdBy);
            attributeValueRepository.save(attributeValue);
        }else{//edit
            if(attributeOptional.isPresent()) {
                attribute = attributeOptional.get();
                attribute.setAttributeOwnerType(attributeOwnerType);
                attribute.setDataType(dataType);
                attribute.setName(dto.getName());
                attribute.setVersion(attribute.getVersion()+1);
                attribute.setCreated(DateUtils.getNow());
                attribute.setUpdated(DateUtils.getNow());
                attribute.setUpdatedBy(createdBy);
                attributeRepository.save(attribute);
                Iterator<AttributeValueDTO> itr = valueDTOS.iterator();
                if(itr.hasNext())   valueDTO = itr.next();
                attributeValue = new AttributeValue(valueDTO.getBooleanValue(),valueDTO.getIntValue(),valueDTO.getLongValue(),valueDTO.getStringValue(),
                        valueDTO.getObjectValue(),valueDTO.getDoubleValue(),valueDTO.getBinaryContentId(),attribute,attribute,
                        1L,DateUtils.getNow(),DateUtils.getNow(),createdBy,createdBy);
                attributeValueRepository.save(attributeValue);
            }
        }
        return attribute;
    }


}
