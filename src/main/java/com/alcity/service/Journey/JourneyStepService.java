package com.alcity.service.Journey;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.repository.journey.JourneyStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class JourneyStepService implements JourneyStepRepository {

    @Autowired
    JourneyStepRepository journeyStepRepository;
    @Override
    public <S extends JourneyStep> S save(S entity) {
        return journeyStepRepository.save(entity);
    }

    @Override
    public <S extends JourneyStep> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JourneyStep> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<JourneyStep> findAll() {
        return null;
    }

    @Override
    public Iterable<JourneyStep> findAllById(Iterable<Long> longs) {
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
    public void delete(JourneyStep entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends JourneyStep> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<JourneyStep> findByXpos(Integer xpos) {
        return null;
    }

    @Override
    public Collection<JourneyStep> findByYpos(Integer ypos) {
        return null;
    }

    @Override
    public Journey findByTitle(String title) {
        return null;
    }
}
