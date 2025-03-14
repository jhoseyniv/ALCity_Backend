package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.dto.puzzle.CityObjectInPLDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.ALCityInstanceInPLRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ALCityInstanceInPLService implements ALCityInstanceInPLRepository {

    @Autowired
    @Qualifier("ALCityInstanceInPLRepository")
    ALCityInstanceInPLRepository repository;

    @Override
    public <S extends ALCityInstanceInPL> S save(S entity) {
        return repository.save(entity);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private ALCityObjectInPGService alCityObjectInPGService;
    public ALCityInstanceInPL save(CityObjectInPLDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPuzzleLevelId());
        Optional<ALCityObjectInPG> alCityObjectInPGOptional =  alCityObjectInPGService.findById(dto.getAlCityObjectInPGId());
        if(puzzleLevelOptional.isEmpty() || alCityObjectInPGOptional.isEmpty()) return null;

        ALCityInstanceInPL alCityInstanceInPL=null;
            if (code.equalsIgnoreCase("Save")) { //Save
                alCityInstanceInPL = new ALCityInstanceInPL(dto.getName(), dto.getRow(),dto.getCol(),dto.getZorder(),alCityObjectInPGOptional.get(),
                        puzzleLevelOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
                repository.save(alCityInstanceInPL);
            }else{//edit
                Optional<ALCityInstanceInPL> alCityInstanceInPLOptional= repository.findById(dto.getId());
                if(alCityInstanceInPLOptional.isPresent()) {
                    alCityInstanceInPL = alCityInstanceInPLOptional.get();
                    alCityInstanceInPL.setName(dto.getName());
                    alCityInstanceInPL.setRow(dto.getRow());
                    alCityInstanceInPL.setCol(dto.getCol());
                    alCityInstanceInPL.setzOrder(dto.getZorder());
                    alCityInstanceInPL.setAlCityObjectInPG(alCityObjectInPGOptional.get());
                    alCityInstanceInPL.setPuzzleLevel(puzzleLevelOptional.get());
                    repository.save(alCityInstanceInPL);
                }
            }
        return alCityInstanceInPL;
    }


    @Override
    public <S extends ALCityInstanceInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityInstanceInPL> findById(Long id) {
        return repository.findById(id);
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
        repository.deleteById(aLong);
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
        return repository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }



 }
