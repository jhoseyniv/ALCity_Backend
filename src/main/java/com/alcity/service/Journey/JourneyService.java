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
        Optional<BinaryContent> graphic =binaryContentService.findById(entity.getGraphic().getId());
        if(graphic.isPresent()) {
            System.out.println(entity.getGraphic().getFileName());
        }
        return journeyRepository.save(entity);
    }
    public Journey save(JourneyDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        if(icon.isEmpty()) {
            //save defualt binary content....
        }
        Journey journey=null;
        Optional<Journey> journeyOptional= journeyRepository.findByTitle(dto.getTitle());
        if (code.equalsIgnoreCase("Save")) { //Save
            journey = new Journey(dto.getTitle() ,icon.get(),1L,
                    DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            journeyRepository.save(journey);
        }else{//edit
            journeyOptional= journeyRepository.findById(dto.getId());
            if(journeyOptional.isPresent()) {
                journey = journeyOptional.get();
                journey.setTitle(dto.getTitle());
                journey.setGraphic(icon.get());
                journey.setVersion(journey.getVersion()+1);
                journey.setUpdated(DateUtils.getNow());
                journey.setUpdatedBy(createdBy);
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
    public Journey save(JourneyDTO journeyDTO) throws UniqueConstraintException {
        Optional<BinaryContent> binaryContentIsExist;
        Journey journey = null;
        Optional<AppMember> createdBy = appMemberRepository.findById(journeyDTO.getCreatedById());
        Optional<AppMember> updatedBy = appMemberRepository.findById(journeyDTO.getUpdatedById());

        if(journeyDTO.getId() != null ) {
            binaryContentIsExist = binaryContentService.findById(journeyDTO.getIconId());
            if (binaryContentIsExist.isPresent()) {
                journey = new Journey(journeyDTO.getTitle(), binaryContentIsExist.get(), journeyDTO.getVersion(),
                        journeyDTO.getCreated(), journeyDTO.getUpdated(), createdBy.get(), updatedBy.get());
                journeyRepository.save(journey);
            }
        }

        return journey;
    }


}
