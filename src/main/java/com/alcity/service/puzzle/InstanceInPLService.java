package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.CityObjectInPLDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.InstanceInPLRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class InstanceInPLService implements InstanceInPLRepository {

    @Autowired
    InstanceInPLRepository instanceInPLRepository;

    @Override
    public <S extends ALCityInstanceInPL> S save(S entity) {
        return instanceInPLRepository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private ObjectInPGService objectInPGService;
    public ALCityInstanceInPL save(CityObjectInPLDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPuzzleLevelId());
        Optional<ALCityObjectInPG> alCityObjectInPGOptional =  objectInPGService.findById(dto.getAlCityObjectInPGId());
        if(puzzleLevelOptional.isEmpty() || alCityObjectInPGOptional.isEmpty()) return null;

        ALCityInstanceInPL instance=null;
            if (code.equalsIgnoreCase("Save")) { //Save
                instance = new ALCityInstanceInPL(dto.getName(), dto.getRow(),dto.getCol(),dto.getZorder(),alCityObjectInPGOptional.get(),
                        puzzleLevelOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                instanceInPLRepository.save(instance);
            }else{//edit
                Optional<ALCityInstanceInPL> alCityInstanceInPLOptional= instanceInPLRepository.findById(dto.getId());
                if(alCityInstanceInPLOptional.isPresent()) {
                    instance = alCityInstanceInPLOptional.get();
                    instance.setName(dto.getName());
                    instance.setRow(dto.getRow());
                    instance.setCol(dto.getCol());
                    instance.setzOrder(dto.getZorder());
                    instance.setAlCityObjectInPG(alCityObjectInPGOptional.get());
                    instance.setPuzzleLevel(puzzleLevelOptional.get());
                    instanceInPLRepository.save(instance);
                }
            }
        return instance;
    }


    @Override
    public <S extends ALCityInstanceInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityInstanceInPL> findById(Long id) {
        return instanceInPLRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityInstanceInPL> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityInstanceInPL> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        instanceInPLRepository.deleteById(aLong);
    }

    @Override
    public void delete(ALCityInstanceInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityInstanceInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityInstanceInPL> findByRow(Integer row) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByCol(Integer col) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByzOrder(Integer zOrder) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl) {
        return instanceInPLRepository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }



 }
