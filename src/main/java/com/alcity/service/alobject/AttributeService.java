package com.alcity.service.alobject;

import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.dto.alobject.AttributeValueDTOSave;
import com.alcity.dto.pgimport.PGObjectVariableImportDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.ALCityInstanceInPL;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
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
        return attributeRepository.saveAll(entities);
    }
    @Transactional
    public AttributeValue copyAnAttributeValue(Attribute attribute,Long fromOwner,Long toOwner,AttributeOwnerType newOwnerType){
        Collection<AttributeValue> values = attribute.getAttributeValues();
        AttributeValue newAttributeValue=null;
         Optional<AttributeValue> attributeValueOptional = values.stream().filter(value -> value.getOwnerId().equals(fromOwner)).collect(Collectors.toList()).stream().findFirst();
   //           if(attributeValueOptional.isPresent()) {
                  AttributeValue value = attributeValueOptional.get();
                   newAttributeValue =new AttributeValue(value.getBooleanValue(),value.getIntValue(),value.getLongValue(), value.getStringValue(), value.getObjectValue(),
                          value.getDoubleValue(),value.getBinaryContentId(),value.getExpressionValue(),value.getExpression(),value.getBindedAttributeId(),value.getAttributeId(), value.getVersion(),value.getCreated() ,value.getUpdated(),
                          value.getCreatedBy(),value.getUpdatedBy(),toOwner,newOwnerType);
          //    }
        attributeValueRepository.save(newAttributeValue);
        return newAttributeValue;
    }

    public  Collection<Attribute> copyAttributes(Collection<Attribute> attributes,Long toOwner,AttributeOwnerType newOwnerType) {
        Collection<Attribute> copyAttributes = new ArrayList<>();
        Iterator<Attribute> iterator = attributes.iterator();
        while(iterator.hasNext()){
            Attribute attribute = iterator.next();
            Attribute newAttribute = copyAnAttribute(attribute,toOwner,newOwnerType);
            copyAttributes.add(newAttribute);
        }
        return  copyAttributes;
    }

        @Transactional
    public  Attribute copyAnAttribute(Attribute attribute,Long toOwner,AttributeOwnerType newOwnerType){
        Attribute newAttribute = new Attribute(attribute.getName(),toOwner,newOwnerType,attribute.getDataType(),attribute.getVersion(),attribute.getCreated(),attribute.getUpdated(),
                attribute.getCreatedBy(),attribute.getUpdatedBy());
        AttributeValue newValue =null;
        Collection<AttributeValue> newValues =new ArrayList<>();
        Collection<AttributeValue> values = attribute.getAttributeValues();
        Optional<AttributeValue> valueOptional = values.stream().findFirst();
        if(valueOptional.isPresent()) {
            AttributeValue value = valueOptional.get();
            newValue =new AttributeValue(value.getBooleanValue(),value.getIntValue(),value.getLongValue(), value.getStringValue(), value.getObjectValue(),
                    value.getDoubleValue(),value.getBinaryContentId(),value.getExpressionValue(),value.getExpression(),value.getBindedAttributeId(),newAttribute, value.getVersion(),value.getCreated() ,value.getUpdated(),
                    value.getCreatedBy(),value.getUpdatedBy(),toOwner,newOwnerType);
        newValues.add(newValue);
        }
        newAttribute.setAttributeValues(newValues);
        attributeRepository.save(newAttribute);
        return newAttribute;
    }

    @Transactional
    public void copyOneAttributeFromInstanceToInstance(Attribute attribute , ALCityInstanceInPL from ,ALCityInstanceInPL to,AttributeOwnerType ownerType) {
        if(attribute.getOwnerId().equals(from.getId())) { // attribute is for instance
            //copy attribute and values
            Attribute copiedAttribute = copyAnAttribute(attribute, to.getId(), attribute.getAttributeOwnerType());
        }else {
            Long pgo = from.getAlCityObjectInPG().getId();
            if (attribute.getOwnerId().equals(pgo)) { // attribute is belong to puzzle group object
                Collection<AttributeValue> values = attribute.getAttributeValues();
                Optional<AttributeValue> isValueOverWiteByInstance = values.stream().filter(value -> value.getOwnerId().equals(from.getId())).collect(Collectors.toList()).stream().findFirst();
                Long newOwnerOfValue = 0L;
                if(isValueOverWiteByInstance.isPresent())
                    newOwnerOfValue = from.getId();
                else {
                    newOwnerOfValue=pgo;
                }
                //copy attribute value only and so owner of attribute value is the instance yet
              copyAnAttributeValue(attribute, newOwnerOfValue, to.getId(),ownerType);
            }
        }
    }


    public Collection<Attribute> copyALLAttributesFromInstanceToInstance(Collection<Attribute> attributes,ALCityInstanceInPL from ,ALCityInstanceInPL to,AttributeOwnerType ownerType){
        Collection<Attribute>  copyAttributes = new ArrayList<>();

        Iterator<Attribute> iterator =attributes.iterator();
        while(iterator.hasNext()) {
                Attribute attribute = iterator.next();
            copyOneAttributeFromInstanceToInstance(attribute,from,to,ownerType);
         }

        return copyAttributes;
    }
    @Transactional
    public Attribute copyOneAttributesFromPLToPL(Attribute attribute , PuzzleLevel source ,PuzzleLevel target,AttributeOwnerType ownerType) {
            //copy attribute and values
            Attribute copiedAttribute = copyAnAttribute(attribute, target.getId(), attribute.getAttributeOwnerType());
            return copiedAttribute;
     }

    public Collection<Attribute> copyALLAttributesFromPLToPL(Collection<Attribute> attributes, PuzzleLevel source , PuzzleLevel target, AttributeOwnerType ownerType){
        Collection<Attribute>  copyAttributes = new ArrayList<>();

        Iterator<Attribute> iterator =attributes.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            copyOneAttributesFromPLToPL(attribute,source,target,ownerType);
        }
        return copyAttributes;
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

    public Collection<Attribute> findByOwnerId(Long ownerId) {
        return attributeRepository.findByOwnerId(ownerId);
    }

    @Override
    public Collection<Attribute> findByOwnerIdAndAttributeOwnerType(Long ownerId, AttributeOwnerType OwnerType) {
        return attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,OwnerType);
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

        //fetch Object Action Handler for finding parameters
        Renderer renderer = objectActionOptional.get().getActionRenderer();

        //fetch parameters for parent handler
        Collection<Attribute> parameters = attributeRepository.findByOwnerId(renderer.getId());

        Iterator<Attribute> itr = parameters.iterator();
        while (itr.hasNext()) {
            Attribute parameter = itr.next();
            Collection<AttributeValue> outputValues = new ArrayList<>();
            Collection<AttributeValue> parameterValues = parameter.getAttributeValues();
            Optional<AttributeValue> isPuzzleGroupObjectActionHasValue = parameterValues.stream().filter(value -> value.getOwnerId().equals(ownerId)).findFirst();
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


    public Collection<Attribute> findPuzzleLevelRulePostAction(Long ownerId,AttributeOwnerType ownerType) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        Collection<Attribute> parameters = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);
        return parameters;
    }
    public Collection<Attribute> findPuzzleLevelRulePostActionParameters(Long ownerId,AttributeOwnerType ownerType) {
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch RulePostAction parameters
        Collection<Attribute> parameters = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);
        return parameters;
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
    public Long getObjectForThisPOG(Long pgo_id) {
        // return pgo id for an instance
        Long objectId=-1L;
        Optional<ALCityObjectInPG> objectInPGOptional = alCityObjectInPGService.findById(pgo_id);
        if(objectInPGOptional.isPresent())
            objectId = objectInPGOptional.get().getAlCityObject().getId();
        return  objectId;
    }
    public Collection<AttributeValue> removeIf(Collection<AttributeValue> list,AttributeOwnerType type){
        Iterator<AttributeValue> itr = list.iterator();
        Collection<AttributeValue>  output = new ArrayList<>();
        while(itr.hasNext()){
            AttributeValue value=itr.next();
            if(!value.getOwnerType().equals(type))
                output.add(value);
        }
        return output;
    }
    public Collection<Attribute> defined_properties_in_pg_object(Collection<Attribute> properties,Long pgo_id,long object_id) {
        Collection<Attribute> defined_properties_in_object = new ArrayList<>();
        Iterator<Attribute> itr = properties.iterator();
        while(itr.hasNext()){
            Attribute attribute = itr.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Collection<AttributeValue> newAttributeValues= new ArrayList<>();
            Optional<AttributeValue> is_pgo_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property)).findFirst();
            Optional<AttributeValue> is_object_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerType().equals(AttributeOwnerType.Object_Property)).findFirst();
            if(is_object_has_value.isEmpty() && is_pgo_has_value.isPresent()  ) { // value define and init in pgo only
                newAttributeValues.add(is_pgo_has_value.get());
            }else if(is_object_has_value.isPresent() && is_pgo_has_value.isEmpty()){//value is defined in object only
                newAttributeValues.add(is_object_has_value.get());
            }else if(is_object_has_value.isPresent() && is_pgo_has_value.isPresent()){//value defined in object but re init in pgo
                newAttributeValues.add(is_pgo_has_value.get());
            }
            attribute.setAttributeValues(newAttributeValues);
            defined_properties_in_object.add(attribute);
        }
        defined_properties_in_object = removeUnRelatedAttributeValues(defined_properties_in_object,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

        return defined_properties_in_object;
    }
    public Collection<Attribute> removeUnRelatedAttributeValues(Collection<Attribute>  inputs,AttributeOwnerType type){
        Collection<Attribute> outputAttributes = new ArrayList<>();
        Iterator<Attribute>  itr = inputs.iterator();
        while(itr.hasNext()){
            Collection<AttributeValue>  output = new ArrayList<>();
            Attribute attribute = itr.next();
            Collection<AttributeValue> values = attribute.getAttributeValues();
            Iterator<AttributeValue> valueIterator = values.iterator();
            while(valueIterator.hasNext()){
                AttributeValue value=valueIterator.next();
                if(!value.getOwnerType().equals(type))
                    output.add(value);
            }
            attribute.setAttributeValues(output);
            outputAttributes.add(attribute);
        }
        return outputAttributes;
    }
    public Collection<Attribute> findPropertiesForPuzzleGroupObject(Long pgo_Id,AttributeOwnerType ownerType){
        //find properties for a object as parent of pgo + find properties for a pgo

        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        Collection<Attribute> temp_Bug_properties_in_an_object = new ArrayList<>();

        //check is_defined_and_init_in_pgo_only ?
        Collection<Attribute> properties_for_pgo = attributeRepository.findByOwnerIdAndAttributeOwnerType(pgo_Id,ownerType);
        // state 1 : if properties_for_pgo is not empty mean that properties is_defined_and_init_in_instance_only
        outputAttributes = removeUnRelatedAttributeValues(properties_for_pgo,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);

        // check is_defined_in_object_and_reinit_in_pog ?
        // find object - get parent object id for this pgo
        Long object_id = getObjectForThisPOG(pgo_Id);

        //fetch properties for a parent object of this pgo
        Collection<Attribute> properties_in_a_object = attributeRepository.findByOwnerId(object_id);
        properties_in_a_object = properties_in_a_object.stream().filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Property)).collect(Collectors.toList());
        Iterator<Attribute> iterator = properties_in_a_object.iterator();
        while(iterator.hasNext()){
            Attribute attribute = iterator.next();
            Collection<AttributeValue> values = attributeValueRepository.findByAttributeId(attribute);
            System.out.println(values.size());
            attribute.setAttributeValues(values);
            temp_Bug_properties_in_an_object.add(attribute);
        }
        //state 2 + state 3 : if properties_in_a_pg_object is not empty mean that variables is defined in parent(object)
        //sate 2 : properties are defined in object and initialize there only
        //state 3 : properties are defined in object but re-initialize in pgo
        //following method find variables and values for state 2 and 3
        Collection<Attribute> defined_properties_in_a_object = defined_properties_in_pg_object(temp_Bug_properties_in_an_object,pgo_Id,object_id);
        outputAttributes.addAll(defined_properties_in_a_object);
        return outputAttributes;
    }
    public Collection<Attribute> findPropertiesForALCityObject(Long object_id,AttributeOwnerType ownerType){
        //find properties for a object as parent of pgo + find properties for a pgo
       Collection<Attribute> properties_for_object = attributeRepository.findByOwnerIdAndAttributeOwnerType(object_id,ownerType);


        return properties_for_object;
    }

    public Collection<Attribute> getVariablesValuesForThisOwner(Collection<Attribute> attributes , Long ownerId ){
        Collection<Attribute> output_attributes = new ArrayList<>();
        Collection<AttributeValue> values = new ArrayList<>();

        Iterator<Attribute> itr = attributes.iterator();
        while(itr.hasNext()) {
            Collection<AttributeValue> output_values = new ArrayList<>();
            Attribute attribute = itr.next();
            values = attribute.getAttributeValues();
            Optional<AttributeValue> attributeValueOptional = values.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(ownerId)).findFirst();
            if(attributeValueOptional.isPresent()){
                output_values.add(attributeValueOptional.get());
                attribute.setAttributeValues(output_values);
            }
            output_attributes.add(attribute);
        }

        return output_attributes;
    }

    public Collection<Attribute> findVariablesForPuzzleGroupObject(Long ownerId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        //fetch variables for an object in a puzzle group
        Collection<Attribute> variables_PGO = attributeRepository.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);
        outputAttributes = getVariablesValuesForThisOwner(variables_PGO,ownerId);
        return outputAttributes;
    }

    public Long getPGOForThisInstance(Long instanceId) {
        // return pgo id for an instance
        Long objectInPGId=-1L;
        Optional<ALCityInstanceInPL> alCityInstanceInPLOptional = aLCityInstanceInPLService.findById(instanceId);
        if(alCityInstanceInPLOptional.isPresent())
            objectInPGId = alCityInstanceInPLOptional.get().getAlCityObjectInPG().getId();
        return  objectInPGId;
    }

    public Collection<Attribute> defined_variables_in_pog(Collection<Attribute> variables,Long instanceId,long pgo_id) {
        Collection<Attribute> defined_variables_in_a_pg_object = new ArrayList<>();
        Iterator<Attribute> itr = variables.iterator();
        while(itr.hasNext()){
            Attribute attribute = itr.next();
            Collection<AttributeValue> attributeValuesByInstances = attribute.getAttributeValues();
            Collection<AttributeValue> attributeValuesByPOG = attribute.getAttributeValues();
            attributeValuesByInstances = attributeValuesByInstances.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(instanceId)).collect(Collectors.toList());

            if(!attributeValuesByInstances.isEmpty()){   // mean that attribute is defined in pog but  re-init in instance
                attribute.setAttributeValues(attributeValuesByInstances);
            }else { // mean that attribute is defined and init in pog only
                attributeValuesByPOG = attributeValuesByPOG.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(pgo_id)).collect(Collectors.toList());
                attribute.setAttributeValues(attributeValuesByPOG);
            }
            defined_variables_in_a_pg_object.add(attribute);
        }
        return defined_variables_in_a_pg_object;
    }

    public Collection<Attribute> findInstanceVariables(Long instanceId,AttributeOwnerType ownerType){
        Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
        Collection<Attribute> temp_Bug_variables_in_a_pg_object = new ArrayList<>();

        //check is_defined_and_init_in_instance_only ?
        Collection<Attribute> variables_for_instance = attributeRepository.findByOwnerIdAndAttributeOwnerType(instanceId,ownerType);
        // state 1 : if variables_for_instance is not empty mean that variables is_defined_and_init_in_instance_only
        outputAttributes = variables_for_instance;

        // check is_defined_in_pgo_and_reinit_in_instance ?
        // find pgo - get parent object id for this instance
        Long pgo_id = getPGOForThisInstance(instanceId);

        //fetch variables for a parent object of this instance
        Collection<Attribute> variables_in_a_pg_object = attributeRepository.findByOwnerId(pgo_id);
         variables_in_a_pg_object = variables_in_a_pg_object.stream().filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Variable)).collect(Collectors.toList());
         Iterator<Attribute> iterator = variables_in_a_pg_object.iterator();
         while(iterator.hasNext()){
             Attribute attribute = iterator.next();
            Collection<AttributeValue> values = attributeValueRepository.findByAttributeId(attribute);
            System.out.println(values.size());
            attribute.setAttributeValues(values);
             temp_Bug_variables_in_a_pg_object.add(attribute);
         }
        //state 2 + state 3 : if variables_in_a_pg_object is not empty mean that variables is defined in parent(pgo)
        //sate 2 : variables are defined in pgo and initialize there only
        //state 3 : variables are defined in pgo but initialize in instance
        //following method find variables and values for state 2 and 3
        Collection<Attribute> defined_variables_in_a_pgo = defined_variables_in_pog(temp_Bug_variables_in_a_pg_object,instanceId,pgo_id);
        outputAttributes.addAll(defined_variables_in_a_pgo);

        return outputAttributes;
    }
    public Collection<Attribute> UnownBuginLoad_values(Collection<Attribute> inputs){
        Collection<Attribute> outputs= new ArrayList<>();
        Iterator<Attribute> iterator = inputs.iterator();
        while(iterator.hasNext()){
            Attribute attribute = iterator.next();
            Collection<AttributeValue> values = attributeValueRepository.findByAttributeId(attribute);
            System.out.println(values.size());
            attribute.setAttributeValues(values);
            outputs.add(attribute);
        }
        return outputs;
    }
    public Collection<Attribute> defined_properties_in_instance(Long instanceId,Long pgo_id,long object_id) {
        Collection<Attribute> outputs = new ArrayList<>();
        Collection<Attribute> properties_for_instance = attributeRepository.findByOwnerIdAndAttributeOwnerType(instanceId,AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        outputs.addAll(properties_for_instance);


        Collection<Attribute> properties_for_pgo = attributeRepository.findByOwnerIdAndAttributeOwnerType(pgo_id,AttributeOwnerType.Puzzle_Group_Object_Property);
        properties_for_pgo = UnownBuginLoad_values(properties_for_pgo);
        Iterator<Attribute> itreator_properties_for_pgo = properties_for_pgo.iterator();

        while(itreator_properties_for_pgo.hasNext()){
            Attribute attribute = itreator_properties_for_pgo.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Collection<AttributeValue> newAttributeValues= new ArrayList<>();
            Optional<AttributeValue> is_instance_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(instanceId)).findFirst();
            Optional<AttributeValue> is_pgo_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(pgo_id)).findFirst();
            if(is_instance_has_value.isEmpty() && is_pgo_has_value.isPresent()  ) { // value define and init in pgo only
                newAttributeValues.add(is_pgo_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isEmpty()){//value is defined in instance only
                newAttributeValues.add(is_instance_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isPresent()){//value is defined in pgo but re init in instance
                newAttributeValues.add(is_instance_has_value.get());
            }
            attribute.setAttributeValues(newAttributeValues);
            outputs.add(attribute);
        }


        Collection<Attribute> properties_for_object = attributeRepository.findByOwnerIdAndAttributeOwnerType(object_id,AttributeOwnerType.Object_Property);
        properties_for_object = UnownBuginLoad_values(properties_for_object);

        Iterator<Attribute> itreator_properties_for_object = properties_for_object.iterator();
        while(itreator_properties_for_object.hasNext()) {
            Attribute attribute = itreator_properties_for_object.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Collection<AttributeValue> newAttributeValues= new ArrayList<>();
            Optional<AttributeValue> is_instance_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(instanceId)).findFirst();
            Optional<AttributeValue> is_pgo_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(pgo_id)).findFirst();
            Optional<AttributeValue> is_object_has_value = attributeValues.stream().filter(attributeValue -> attributeValue.getOwnerId().equals(object_id)).findFirst();

            if(is_instance_has_value.isEmpty() && is_pgo_has_value.isEmpty() && is_object_has_value.isEmpty()){// property is not defined at all
                    //do nothing
                System.out.println("property is not defined at all");
            }else if(is_instance_has_value.isEmpty() && is_pgo_has_value.isEmpty() && is_object_has_value.isPresent()){ //property is defined in object only
                newAttributeValues.add(is_object_has_value.get());
            }else if(is_instance_has_value.isEmpty() && is_pgo_has_value.isPresent() && is_object_has_value.isEmpty()){ //property is defined in pgo only
                newAttributeValues.add(is_pgo_has_value.get());
            }else if(is_instance_has_value.isEmpty() && is_pgo_has_value.isPresent() && is_object_has_value.isPresent()){ // property is defined object and re init pgo
                newAttributeValues.add(is_pgo_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isEmpty() && is_object_has_value.isEmpty()){ // property is defined and init in instance only
                newAttributeValues.add(is_instance_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isEmpty() && is_object_has_value.isPresent()){ // property is defined in object and re init in instance
                newAttributeValues.add(is_instance_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isPresent() && is_object_has_value.isEmpty()){ // property is defined in pgo and re init in instance
                newAttributeValues.add(is_instance_has_value.get());
            }else if(is_instance_has_value.isPresent() && is_pgo_has_value.isPresent() && is_object_has_value.isPresent()){  // property is defined in object and re init in pgo and instance
                newAttributeValues.add(is_instance_has_value.get());
            }
            attribute.setAttributeValues(newAttributeValues);
            outputs.add(attribute);
        }

        return  outputs;
    }public Collection<Attribute> findInstanceProperties(Long instanceId,AttributeOwnerType ownerType){
       //     Collection<Attribute> outputAttributes = new ArrayList<Attribute>();
            Collection<Attribute> definedAttributes = new ArrayList<Attribute>();

   /*     //check is_defined_and_init_in_instance_only ?
        Collection<Attribute> properties_for_instance = attributeRepository.findByOwnerIdAndAttributeOwnerType(instanceId,ownerType);
        // state 1 : if properties_for_instance is not empty mean that properties is_defined_and_init_in_instance_only
        outputAttributes = properties_for_instance;

        // check is_defined_in_pgo_and_reinit_in_instance ?
        // find pgo - get pg object id for this instance
        Long pgo_id = getPGOForThisInstance(instanceId);
        //fetch properties for a pg object of this instance

        Collection<Attribute> temp_Bug_properties_in_a_pg_object = new ArrayList<>();
        Collection<Attribute> properties_in_a_pg_object = attributeRepository.findByOwnerId(pgo_id);
        properties_in_a_pg_object = properties_in_a_pg_object.stream().filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property)).collect(Collectors.toList());
        Iterator<Attribute> iterator = properties_in_a_pg_object.iterator();
        while(iterator.hasNext()){
            Attribute attribute = iterator.next();
            Collection<AttributeValue> values = attributeValueRepository.findByAttributeId(attribute);
            System.out.println(values.size());
            attribute.setAttributeValues(values);
            temp_Bug_properties_in_a_pg_object.add(attribute);
        }
        outputAttributes.addAll(temp_Bug_properties_in_a_pg_object);

        Collection<Attribute> temp_Bug_properties_in_a_object = new ArrayList<>();
        Long object_id = getObjectForThisPOG(pgo_id);
        Collection<Attribute> properties_in_a_object = attributeRepository.findByOwnerId(object_id);
        properties_in_a_object = properties_in_a_object.stream().filter(attribute -> attribute.getAttributeOwnerType().equals(AttributeOwnerType.Object_Property)).collect(Collectors.toList());
        Iterator<Attribute> iterator_object = properties_in_a_object.iterator();

        while(iterator_object.hasNext()){
            Attribute attribute = iterator_object.next();
            Collection<AttributeValue> values = attributeValueRepository.findByAttributeId(attribute);
            System.out.println(values.size());
            attribute.setAttributeValues(values);
            temp_Bug_properties_in_a_object.add(attribute);
        }
        outputAttributes.addAll(temp_Bug_properties_in_a_object);
*/
        Long pgo_id = getPGOForThisInstance(instanceId);
        Long object_id = getObjectForThisPOG(pgo_id);

        definedAttributes = defined_properties_in_instance(instanceId,pgo_id,object_id);
        return definedAttributes;


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
            outputAttributes = findInstanceVariables(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Instance_Puzzle_Group_Object_Property) {
            outputAttributes = findInstanceProperties(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Level_Variable) {
            outputAttributes = findPuzzleLevelVariable(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Level_Rule_Post_Action) {
            outputAttributes = findPuzzleLevelRulePostAction(ownerId,ownerType);
        }
        if(ownerType == AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter) {
            outputAttributes = findPuzzleLevelRulePostActionParameters(ownerId,ownerType);
        }
        return outputAttributes;
        }
/*
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
*/
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
                   // attribute.setAttributeOwnerType(attributeOwnerType);
                  //  attribute.setDataType(dataType);
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
                    throw new UniqueConstraintException(-1,"Unique Constraint in" + Attribute.class , "Error",savedRecord.getId() );
                }
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
            }else{
                try {
                    savedRecord  = save(dto,"Edit");
                } catch (RuntimeException e) {
                    throw new UniqueConstraintException(-1,"Unique Constraint in" + Attribute.class , "Error",savedRecord.getId() );
                }
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");

            }
            responseObjects.add(responseObject);
        }
        return responseObjects;
    }
    public Collection<ALCityResponseObject> importPGVariables(Collection<PGObjectVariableImportDTO> dtos, PuzzleGroup puzzleGroup) {
        Collection<ALCityResponseObject> responseObjects = new ArrayList<>();
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Attribute savedRecord = new Attribute();
        Iterator<PGObjectVariableImportDTO> itr = dtos.iterator();
        while(itr.hasNext()) {
            PGObjectVariableImportDTO dto = itr.next();
            ALCityResponseObject responseObject = new ALCityResponseObject();
            if (dto.getId() == null || dto.getId() <= 0L) { //save
                try {
                    Attribute attribute=null;
                    DataType dataType =  DataType.getByTitle(dto.getDataType());
                    attribute = new Attribute(dto.getName(), puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,
                            dataType ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                    attributeRepository.save(attribute);
                            AttributeValue  attributeValue = DTOUtil.getAttributeValueFromVariableImport(dto,attribute,createdBy.get());
                    attributeValueRepository.save(attributeValue);
                } catch (RuntimeException e) {
                    throw new UniqueConstraintException(-1, "Unique Constraint in" + Attribute.class, "Error", savedRecord.getId());
                }
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
            }
        }
        return responseObjects;
    }


}
