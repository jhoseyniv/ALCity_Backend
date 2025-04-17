package com.alcity.service.Journey;

import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.journey.JourneyRepository;
import com.alcity.service.base.BinaryContentService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;
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
    AppMemberRepository appMemberRepository;
    @Autowired
    BinaryContentRepository binaryContentRepository;

    @Override
    public <S extends Journey> S save(S entity) {
        return journeyRepository.save(entity);
    }
    public Journey save(JourneyDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Journey journey=null;
        Optional<Journey> journeyOptional= journeyRepository.findByTitle(dto.getTitle());
        Optional<BinaryContent> pictureOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonPassedIconOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonCurrenIconOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonLockedIconOptional = binaryContentRepository.findById(dto.getPicId());
        if (code.equalsIgnoreCase("Save")) { //Save
            journey = new Journey(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),dto.getTitle(),dto.getOrdering(),
                    pictureOptional.get(),buttonPassedIconOptional.get(),buttonCurrenIconOptional.get(),buttonLockedIconOptional.get(),
                    dto.getMinToOpenStar(),dto.getMinToPassStar());
            journeyRepository.save(journey);
        }else{//edit
            journeyOptional= journeyRepository.findById(dto.getId());
            if(journeyOptional.isPresent()) {
                journey = journeyOptional.get();
                journey.setTitle(dto.getTitle());
                journey.setPic(pictureOptional.get());
                journey.setVersion(journey.getVersion()+1);
                journey.setUpdated(DateUtils.getNow());
                journey.setUpdatedBy(createdBy.get());
                journeyRepository.save(journey);
            }
        }
        return journey;
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
    public Optional<Journey> findByTitle(String title) {
        return journeyRepository.findByTitle(title);
    }

    @Override
    public Journey save(JourneyDTO dto) throws UniqueConstraintException {
        Journey journey = null;
        Optional<AppMember> createdBy = appMemberRepository.findById(dto.getCreatedById());
        Optional<AppMember> updatedBy = appMemberRepository.findById(dto.getUpdatedById());
        Optional<BinaryContent> pictureOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonPassedIconOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonCurrenIconOptional = binaryContentRepository.findById(dto.getPicId());
        Optional<BinaryContent> buttonLockedIconOptional = binaryContentRepository.findById(dto.getPicId());

        if(dto.getId() != null ) {
            journey = new Journey(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),dto.getTitle(),dto.getOrdering(),
                    pictureOptional.get(),buttonPassedIconOptional.get(),buttonCurrenIconOptional.get(),buttonLockedIconOptional.get(),
                    dto.getMinToOpenStar(),dto.getMinToPassStar());
            journeyRepository.save(journey);
        }

        return journey;
    }


}
