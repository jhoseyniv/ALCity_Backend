package com.alcity.service.alobject;

import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.dto.alobject.AttributeValueDTOSave;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.ALCityInstanceInPL;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.alobject.ActionRepository;
import com.alcity.repository.alobject.AttributeRepository;
import com.alcity.repository.alobject.AttributeValueRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.puzzle.InstanceInPLService;
import com.alcity.service.puzzle.ObjectInPGService;
import com.alcity.service.puzzle.ObjectService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ObjectInPGService alCityObjectInPGService;
    @Autowired
    private InstanceInPLService aLCityInstanceInPLService;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    ActionRepository actionService;

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
        attributeRepository.deleteById(aLong);
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


    public Collection<Attribute> findAttributesForActionHandler(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //find object action from database
        //fetch parameters for a handler
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(ownerId);

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Action_Handler_Parameter)).findFirst();

            if (isActionHasValue.isPresent())
                outputValues.add(isActionHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }

    public Collection<Attribute> findAttributesForObjectActionHandler(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //find object action from database
        Optional<ObjectAction> objectActionOptional = actionService.findById(ownerId);
        if (objectActionOptional.isEmpty()) return outputAttributes;

        //fetch Object Action Handler
        Renderer renderer = objectActionOptional.get().getActionRenderer();

        //fetch parameters for parent handler
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(renderer.getId());

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Action_Handler_Parameter)).findFirst();

            if (isObjectActionHasValue.isPresent())
                outputValues.add(isObjectActionHasValue.get());
            else if (isActionHasValue.isPresent())
                outputValues.add(isActionHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }

    public Collection<Attribute> findAttributesForPuzzleGroupObjectActionHandler(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //find object action from database
        Optional<ObjectAction> objectActionOptional = actionService.findById(ownerId);
        if (objectActionOptional.isEmpty()) return outputAttributes;

        //fetch Object Action Handler
        Renderer renderer = objectActionOptional.get().getActionRenderer();

        //fetch parameters for parent handler
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(renderer.getId());

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isPuzzleGroupObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Action_Handler_Parameter)).findFirst();

            if (isPuzzleGroupObjectActionHasValue.isPresent())
                outputValues.add(isPuzzleGroupObjectActionHasValue.get());
            else if (isObjectActionHasValue.isPresent())
                outputValues.add(isObjectActionHasValue.get());
            else if (isActionHasValue.isPresent())
                outputValues.add(isActionHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }

    public Collection<Attribute> findAttributesForInstancePuzzleGroupObjectActionHandler(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //find object action from database
        Optional<ObjectAction> objectActionOptional = actionService.findById(ownerId);
        if (objectActionOptional.isEmpty()) return outputAttributes;

        //fetch Object Action Handler
        Renderer renderer = objectActionOptional.get().getActionRenderer();

        //fetch parameters for parent handler
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(renderer.getId());

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isInstancePuzzleGroupObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Instance_Puzzle_Group_Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isPuzzleGroupObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Object_Action_Handler_Parameter)).findFirst();
            Optional<AttributeValue> isActionHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Action_Handler_Parameter)).findFirst();

            if (isInstancePuzzleGroupObjectActionHasValue.isPresent())
                outputValues.add(isInstancePuzzleGroupObjectActionHasValue.get());
            else if (isPuzzleGroupObjectActionHasValue.isPresent())
                outputValues.add(isPuzzleGroupObjectActionHasValue.get());
            else if (isObjectActionHasValue.isPresent())
                outputValues.add(isObjectActionHasValue.get());
            else if (isActionHasValue.isPresent())
                outputValues.add(isActionHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }

    public Collection<Attribute> findPropertiesForObject(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for a object
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(ownerId);

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isObjectHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Object_Property)).findFirst();

            if (isObjectHasValue.isPresent())
                outputValues.add(isObjectHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }
    public Collection<Attribute> findVariablesForObject(Long ownerId) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for a object
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(ownerId);

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isObjectHasValue = parameterValues.stream().filter(value -> value.getOwnerType().equals(AttributeOwnerType.Object_Property)).findFirst();

            if (isObjectHasValue.isPresent())
                outputValues.add(isObjectHasValue.get());

            parameter.setAttributeValues(outputValues);
            outputAttributes.add(parameter);
        }
        return outputAttributes;
    }


    public Collection<Attribute> findPuzzleLevelVariable(Long ownerId,AttributeOwnerType ownerType) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch variables for a pl
        Collection<Attribute> variables = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);
        return variables;
    }
     public Collection<Attribute> getPropertiesWithValues(Collection<Attribute> object_parameters , Long pgo_id ){
        Collection<Attribute> attributes = new ArrayList<>();
        Collection<AttributeValue> values = new ArrayList<>();
        Collection<AttributeValue> pog_values = new ArrayList<>();

        Iterator<Attribute> itr_obj = object_parameters.iterator();
        while(itr_obj.hasNext()) {
            Attribute obj_parameter = itr_obj.next();
            values = obj_parameter.getAttributeValues();
            Optional<AttributeValue> pog_valueOptional = values.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(pgo_id)).findFirst();
            if(pog_valueOptional.isPresent()){
                pog_values.add(pog_valueOptional.get());
                obj_parameter.setAttributeValues(pog_values);
            }
            attributes.add(obj_parameter);
        }

        return attributes;
    }

    public Collection<Attribute> findPropertiesForPuzzleGroupObject(Long ownerId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for an object in a puzzle group
        Collection<Attribute> puzzle_Group_Object_parameters = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);

        //fetch properties for a parent object of this puzzle group object
        Long objectId=-1L;
        Optional<ALCityObjectInPG> alCityObjectInPGOptional = alCityObjectInPGService.findById(ownerId);
        if(alCityObjectInPGOptional.isPresent())
            objectId = alCityObjectInPGOptional.get().getAlCityObject().getId();
        Collection<Attribute> object_parameters = attributeRepository.findByOwnerId(objectId);

        outputAttributes = getPropertiesWithValues(object_parameters,ownerId);
        outputAttributes.addAll(puzzle_Group_Object_parameters);
        return outputAttributes;
    }
    public Collection<Attribute> findVariablesForPuzzleGroupObject(Long ownerId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for an object in a puzzle group
        Collection<Attribute> puzzle_Group_Object_variables = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);

        //fetch properties for a parent object of this puzzle group object
        Long objectId=-1L;
        Optional<ALCityObjectInPG> alCityObjectInPGOptional = alCityObjectInPGService.findById(ownerId);
        if(alCityObjectInPGOptional.isPresent())
            objectId = alCityObjectInPGOptional.get().getAlCityObject().getId();
        Collection<Attribute> object_parameters = attributeRepository.findByOwnerId(objectId);

        //outputAttributes = getPropertiesWithValues(object_parameters,ownerId,);
        outputAttributes.addAll(puzzle_Group_Object_variables);
        return outputAttributes;
    }
    public Collection<Attribute> findInstancePuzzleGroupObjectVariable(Long ownerId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for an object in a puzzle group
        Collection<Attribute> Instance_puzzle_Group_Object_variables = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);

        //fetch variables for a parent object of this puzzle group object instance
        Long objectObjectInPGId=-1L;
        Optional<ALCityInstanceInPL> alCityInstanceInPLOptional = aLCityInstanceInPLService.findById(ownerId);
        if(alCityInstanceInPLOptional.isPresent())
            objectObjectInPGId = alCityInstanceInPLOptional.get().getAlCityObjectInPG().getId();
        Collection<Attribute> object_in_pg_parameters = attributeRepository.findByOwnerIdAndAttributeOwnerType(objectObjectInPGId,AttributeOwnerType.Puzzle_Group_Object_Variable);
        outputAttributes = getPropertiesWithValues(object_in_pg_parameters,ownerId);
        outputAttributes.addAll(Instance_puzzle_Group_Object_variables);
        return outputAttributes;
    }
    public Collection<Attribute> findInstancePuzzleGroupObjectProperties(Long ownerId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch properties for an object in a puzzle group
        Collection<Attribute> Instance_puzzle_Group_Object_properties = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);

        //fetch properties for parent object of this puzzle group object instance
        Long objectIdInPuzzleGroup=-1L;
        Long objectId=-1L;
        Optional<ALCityInstanceInPL> alCityInstanceInPLOptional = aLCityInstanceInPLService.findById(ownerId);
        if(alCityInstanceInPLOptional.isPresent()) {
            objectIdInPuzzleGroup = alCityInstanceInPLOptional.get().getAlCityObjectInPG().getId();
            objectId = alCityInstanceInPLOptional.get().getAlCityObjectInPG().getAlCityObject().getId();
        }
        Collection<Attribute> object_in_pg_properties = attributeRepository.findByOwnerIdAndAttributeOwnerType(objectIdInPuzzleGroup,AttributeOwnerType.Puzzle_Group_Object_Property);

       // Collection<Attribute> propertiesForPuzzleGroupObject = new ArrayList<Attribute>();
        outputAttributes = getPropertiesWithValues(object_in_pg_properties,ownerId);
        outputAttributes.addAll(Instance_puzzle_Group_Object_properties);

        Collection<Attribute> object_properties = attributeRepository.findByOwnerIdAndAttributeOwnerType(objectId,AttributeOwnerType.Object_Property);
        Collection<Attribute> propertiesForObject = new ArrayList<Attribute>();
        propertiesForObject = getPropertiesWithValues(object_properties,ownerId);

        outputAttributes.addAll(propertiesForObject);
        return outputAttributes;
    }
    public Collection<Attribute> findByOwnerIdAndAttributeOwnerTypeNew(Long ownerId, AttributeOwnerType ownerType) {

        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();

        if(ownerType == AttributeOwnerType.Action_Handler_Parameter) {
            outputAttributes = findAttributesForActionHandler(ownerId);
        }
        if(ownerType == AttributeOwnerType.Object_Action_Handler_Parameter) {
            outputAttributes = findAttributesForObjectActionHandler(ownerId);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter) {
            outputAttributes = findAttributesForPuzzleGroupObjectActionHandler(ownerId);
        }
        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Action_Handler_Parameter) {
            outputAttributes = findAttributesForInstancePuzzleGroupObjectActionHandler(ownerId);
        }
        if(ownerType == AttributeOwnerType.Object_Property) {
            outputAttributes = findPropertiesForObject(ownerId);
        }
        if(ownerType == AttributeOwnerType.Object_Variable) {
            outputAttributes = findVariablesForObject(ownerId);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Property) {
            outputAttributes = findPropertiesForPuzzleGroupObject(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Variable) {
            outputAttributes = findVariablesForPuzzleGroupObject(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Variable) {
            outputAttributes = findInstancePuzzleGroupObjectVariable(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Property) {
            outputAttributes = findInstancePuzzleGroupObjectProperties(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable) {
            outputAttributes = findPuzzleLevelVariable(ownerId,ownerType);
        }
        return outputAttributes;
        }

    @Override
    public Collection<Attribute> findByOwnerIdAndAttributeOwnerType(Long ownerId, AttributeOwnerType ownerType) {
        Collection<Attribute> alCityAttributes = attributeRepository.findByOwnerId(ownerId);

        ArrayList<Attribute> outputAttributes = new ArrayList<Attribute>();

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Level_Rule_Post_Action)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Rule_Post_Action))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Variable))

                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Instance_Puzzle_Group_Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Instance_Puzzle_Group_Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));

//        if(ownerType == AttributeOwnerType.Puzzle_Level_Instance_Property)
//            outputAttributes = alCityAttributes.stream().
//                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Level_Instance_Property))
//                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Object_Action_Handler_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Action_Handler_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Instance_Puzzle_Group_Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Puzzle_Group_Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));

        if(ownerType == AttributeOwnerType.Action_Handler_Parameter)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Action_Handler_Parameter))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Object_Property)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Property))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Object_Variable)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Variable))
                    .collect(Collectors.toCollection(ArrayList::new));
        if(ownerType == AttributeOwnerType.Puzzle_Group_Object)
            outputAttributes = alCityAttributes.stream().
                    filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object))
                    .collect(Collectors.toCollection(ArrayList::new));

        return outputAttributes;
    }
    @Override
    public Optional<Attribute> findByOwnerIdAndName(Long ownerId, String name) {
        return attributeRepository.findByOwnerIdAndName(ownerId,name);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    public Attribute save(AttributeDTOSave newValue, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<Attribute> attributeOptional =  attributeRepository.findById(newValue.getId());
        AttributeOwnerType attributeOwnerType =  AttributeOwnerType.getByTitle(newValue.getOwnerType());
        DataType dataType =  DataType.getByTitle(newValue.getDataType());
        Attribute attribute=null;
        AttributeValue attributeValue=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            attribute = new Attribute(newValue.getName(), newValue.getOwnerId(),attributeOwnerType,dataType ,
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());

            attributeRepository.save(attribute);
            AttributeValueDTOSave valueDTO = newValue.getAttributeValueDTOSave();
            Optional<Attribute> bindedAttributeOptional =  attributeRepository.findById(valueDTO.getAttributeId());
            Attribute bindedAttribute=null;
            if(bindedAttributeOptional.isPresent())
                bindedAttribute =bindedAttributeOptional.get();
            attributeValue = new AttributeValue(valueDTO.getBooleanValue(),valueDTO.getIntValue(),valueDTO.getLongValue(),valueDTO.getStringValue(),
                    valueDTO.getObjectValue(),valueDTO.getDoubleValue(),valueDTO.getBinaryContentId(), valueDTO.getExpressionValue(),valueDTO.getExpression(),bindedAttribute ,attribute,
                    1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),attribute.getOwnerId(),attribute.getAttributeOwnerType());
            attributeValueRepository.save(attributeValue);
        }else{//edit
            if(attributeOptional.isPresent()) {
                attribute = attributeOptional.get();
                DTOUtil.saveNewAttributeValue(attribute,newValue,attributeRepository,attributeValueRepository);
                    attribute.setAttributeOwnerType(attributeOwnerType);
                    attribute.setDataType(dataType);
                    attribute.setName(newValue.getName());
                    attribute.setVersion(attribute.getVersion()+1);
                    attribute.setCreated(DateUtils.getNow());
                    attribute.setUpdated(DateUtils.getNow());
                    attribute.setUpdatedBy(createdBy.get());
                    attributeRepository.save(attribute);

            }

        }
        return attribute;
    }
    public Collection<ALCityResponseObject> saveAll(Collection<AttributeDTOSave> dtos) {
        Collection<ALCityResponseObject> responseObjects = new ArrayList<>();
        Attribute savedRecord = new Attribute();
        Iterator<AttributeDTOSave> itr = dtos.iterator();
        while(itr.hasNext()){
            AttributeDTOSave dto = itr.next();
            ALCityResponseObject responseObject = new ALCityResponseObject();
            if (dto.getId() == null || dto.getId() <= 0L) { //save
                try {
                    savedRecord  = save(dto,"Save");
                } catch (RuntimeException e) {
                    throw new UniqueConstraintException(dto.getName(), dto.getId(), "title must be Unique");
                }
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
            }else{
                try {
                    savedRecord  = save(dto,"Edit");
                } catch (RuntimeException e) {
                    throw new UniqueConstraintException(dto.getName(), dto.getId(), "title must be Unique");
                }
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");

            }
            responseObjects.add(responseObject);
        }
        return responseObjects;
    }


}
