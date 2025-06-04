package com.alcity.service.puzzle;

import com.alcity.dto.alobject.AttributeDTOSave;
import com.alcity.dto.pgimport.PGObjectImportDTO;
import com.alcity.dto.pgimport.PGObjectVariableImportDTO;
import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.puzzle.ObjectInPGRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.RendererService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.alobject.ActionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class ObjectInPGService implements ObjectInPGRepository {

    @Autowired
    ObjectInPGRepository objectInPGRepository;

    @Autowired
    ActionService puzzleObjectActionService;

    @Autowired
    ActionService objectActionService;

    @Autowired
    @Qualifier("PGRepository")
    PGRepository pgRepository;

    @Lazy
    @Autowired
    ObjectService objectService;

    @Autowired
    RendererService actionRendererService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Override
    public <S extends ALCityObjectInPG> S save(S entity) {
        return objectInPGRepository.save(entity);
    }

    @Override
    public <S extends ALCityObjectInPG> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObjectInPG> findById(Long id) {
        return objectInPGRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObjectInPG> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityObjectInPG> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        objectInPGRepository.deleteById(aLong);
    }

    @Override
    public void delete(ALCityObjectInPG entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityObjectInPG> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityObjectInPG> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<ALCityObjectInPG> findByCode(String code) {
        return objectInPGRepository.findByCode(code);
    }

    @Override
    public Optional<ALCityObjectInPG> findByCodeAndTitle(String code, String title) {
        return objectInPGRepository.findByCodeAndTitle(code,title);
    }

    @Override
    public Collection<ALCityObjectInPG> findByalCityObject(ALCityObject cityObject) {
        return objectInPGRepository.findByalCityObject(cityObject);
    }

    @Override
    public Collection<ALCityObjectInPG> findByPuzzleGroup(PuzzleGroup puzzleGroup) {
        return objectInPGRepository.findByPuzzleGroup(puzzleGroup);
    }

    @Autowired
    private AppMemberRepository appMemberRepository;



    public ALCityObjectInPG importObjInPG(PGObjectImportDTO dto,PuzzleGroup puzzleGroup) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<ALCityObject> alCityObjectOptional = objectService.findById(dto.getObjectId());
        ALCityObjectInPG  alCityObjectInPG=null;
        alCityObjectInPG = new ALCityObjectInPG(dto.getTitle(), dto.getCode(),puzzleGroup,alCityObjectOptional.get(),
                1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        objectInPGRepository.save(alCityObjectInPG);
        Collection<PGObjectVariableImportDTO> variableImportDTOS = dto.getProperties();
        attributeService.importPGVariables(variableImportDTOS,puzzleGroup);
        objectActionService.importActions(dto.getActions(),puzzleGroup.getId());


        return alCityObjectInPG;
    }
        public ALCityObjectInPG save(CityObjectInPGDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleGroup> puzzleGroupOptional =  pgRepository.findByTitle(dto.getPuzzleGroup());
        Optional<ALCityObject> alCityObjectOptional =  objectService.findById(dto.getAlCityObjectId());
        ALCityObjectInPG alCityObjectInPG=null;
        if(puzzleGroupOptional.isPresent())
        if (code.equalsIgnoreCase("Save")) { //Save
            alCityObjectInPG = new ALCityObjectInPG(dto.getTitle(), dto.getCode(),puzzleGroupOptional.get(),alCityObjectOptional.get(),
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            objectInPGRepository.save(alCityObjectInPG);
        }else{//edit
            Optional<ALCityObjectInPG> alCityObjectInPGOptional= objectInPGRepository.findById(dto.getId());
            if(alCityObjectInPGOptional.isPresent()) {
                alCityObjectInPG = alCityObjectInPGOptional.get();
                alCityObjectInPG.setCode(dto.getCode());
                alCityObjectInPG.setTitle(dto.getTitle());
                alCityObjectInPG.setVersion(alCityObjectInPG.getVersion()+1);
                alCityObjectInPG.setUpdated(DateUtils.getNow());
                alCityObjectInPG.setUpdatedBy(createdBy.get());
                objectInPGRepository.save(alCityObjectInPG);
            }
        }
        return alCityObjectInPG;
    }


}
