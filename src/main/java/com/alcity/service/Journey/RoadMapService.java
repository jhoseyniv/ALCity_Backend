package com.alcity.service.Journey;

import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.RoadMap;
import com.alcity.repository.journey.RoadMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RoadMapService implements RoadMapRepository {
    @Autowired
    RoadMapRepository roadMapRepository;


    
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
