package com.alcity.service.Journey;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.journey.JourneyRepository;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.users.ApplicationMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class JourneyService implements JourneyRepository {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    BinaryContentService binaryContentService;
    @Autowired
    ApplicationMemberService applicationMemberService;
    @Override
    public <S extends Journey> S save(S entity) {
        Optional<BinaryContent> graphic =binaryContentService.findById(entity.getGraphic().getId());
        if(graphic.isPresent()) {
            System.out.println(entity.getGraphic().getFileName());
        }
        return journeyRepository.save(entity);
    }

    @Override
    public <S extends Journey> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Journey> findById(Long id) {
        return journeyRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Journey> findAll() {
        return journeyRepository.findAll();
    }

    @Override
    public Collection<Journey> findByTitleContains(String criteria) {
        return journeyRepository.findByTitleContains(criteria);
    }

    @Override
    public Iterable<Journey> findAllById(Iterable<Long> longs) {
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
    public void delete(Journey entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Journey> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Journey findByTitle(String title) {
        return journeyRepository.findByTitle(title);
    }

    @Override
    public JourneyDTO save(JourneyDTO journeyDTO) {
        BinaryContentDTO graphicDTO = journeyDTO.getGraphic();
        Optional<BinaryContent> binaryContentIsExist = binaryContentService.findById(graphicDTO.getId());
        //applicationMemberService.findById()
        if(binaryContentIsExist.isPresent()) {
//            Journey savedJourney = new Journey(journeyDTO.getTitle(),binaryContentIsExist.get(),binaryContentDTO.getVersion(),
//                    binaryContentDTO.getCreated(), binaryContentDTO.getUpdated(),);
        }
        else{

        }

        JourneyDTO savedJourneyDTO = null;

        return savedJourneyDTO;
    }


}
