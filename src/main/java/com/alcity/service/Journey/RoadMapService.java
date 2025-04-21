package com.alcity.service.Journey;

import com.alcity.dto.journey.JourneyStepRecord;
import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.dto.journey.RoadMapUpdatePos;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.journey.JourneyRepository;
import com.alcity.repository.journey.RoadMapRepository;
import com.alcity.service.base.BinaryContentService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class RoadMapService implements RoadMapRepository {
    @Autowired
    RoadMapRepository roadMapRepository;

    @Autowired
    AppMemberRepository appMemberRepository;

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    BinaryContentService binaryContentService;

    @Override
    public <S extends RoadMap> S save(S entity) {
        return roadMapRepository.save(entity);
    }

    @Override
    public <S extends RoadMap> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RoadMap> findById(Long id) {
        return roadMapRepository.findById(id);
    }

    @Override
    public Collection<RoadMap> findByJourney(Journey journey) {
        return roadMapRepository.findByJourney(journey);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<RoadMap> findAll() {
        return null;
    }

    @Override
    public Iterable<RoadMap> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        roadMapRepository.deleteById(aLong);
    }
    public Collection<RoadMap> updateAll(Collection<RoadMapUpdatePos> dtos) {
        Collection<RoadMap> roadMaps = new ArrayList<>();
        Iterator<RoadMapUpdatePos> iterator = dtos.iterator();
        while(iterator.hasNext()){
            RoadMapUpdatePos newRoadMap =  iterator.next();
            Optional<RoadMap> roadMapOptional = roadMapRepository.findById(newRoadMap.getRoadMapId());
            if(roadMapOptional.isEmpty()) return  null;
            RoadMap roadMap = roadMapOptional.get();
            roadMap.setYpos(newRoadMap.getNewPosY());
            roadMapRepository.save(roadMap);
            roadMaps.add(roadMap);
        }
        return roadMaps;
    }

    public RoadMap save(RoadMapDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        RoadMap roadMap=null;
        Optional<BinaryContent> binaryContentOptional= binaryContentService.findById(dto.getGraphicId());
        Optional<Journey>  journeyOptional = journeyRepository.findById(dto.getJourneyId());

        if(journeyOptional.isEmpty() || binaryContentOptional.isEmpty())  return  null;

        if (code.equalsIgnoreCase("Save")) { //Save
            roadMap = new RoadMap(dto.getXpos() ,dto.getYpos(),binaryContentOptional.get(),journeyOptional.get(),
                    1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
            roadMapRepository.save(roadMap);
        }else{//edit
            Optional<RoadMap> roadMapOptional= roadMapRepository.findById(dto.getId());
            if(roadMapOptional.isPresent()) {
                roadMap = roadMapOptional.get();
                roadMap.setJourney(journeyOptional.get());
                roadMap.setVersion(roadMap.getVersion()+1);
                roadMap.setUpdated(DateUtils.getNow());
                roadMap.setUpdatedBy(createdBy.get());
                roadMap.setXpos(dto.getXpos());
                roadMap.setYpos(dto.getYpos());
                roadMap.setGraphic(binaryContentOptional.get());
                roadMapRepository.save(roadMap);
            }
        }
        return roadMap;
    }
    @Override
    public void delete(RoadMap entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RoadMap> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Journey save(RoadMapDTO roadMapDTO) {
        return roadMapRepository.save(roadMapDTO);
    }
}
