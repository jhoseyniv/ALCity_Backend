package com.alcity.service.learning;

import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.learning.LearningContentRepository;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.service.base.BinaryContentService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningContentService implements LearningContentRepository {

    @Autowired
    LearningContentRepository learningContentRepository;
    @Autowired
    BinaryContentService binaryContentService;

    @Autowired
    private ApplicationMemberRepository applicationMemberRepository;

    @Override
    public <S extends LearningContent> S save(S entity) {
        return learningContentRepository.save(entity);
    }
    public LearningContent save(LearningContentDTO dto, String code) {
        ApplicationMember createdBy = applicationMemberRepository.findByUsername("admin");
        Optional<BinaryContent> binaryContentOptional =  binaryContentService.findById(dto.getBinaryContentId());
        LearningContent learningContent=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            learningContent = new LearningContent(dto.getDescText(), dto.getDescBrief(),binaryContentOptional.get(),
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            learningContentRepository.save(learningContent);
        }else{//edit
            Optional<LearningContent> learningContentOptional= learningContentRepository.findById(dto.getId());
            if(learningContentOptional.isPresent()) {
                learningContent = learningContentOptional.get();
                learningContent.setBinaryContent(binaryContentOptional.get());
                learningContent.setDescText(dto.getDescText());
                learningContent.setDescBrief(dto.getDescBrief());
                learningContent.setVersion(learningContent.getVersion()+1);
                learningContent.setUpdated(DateUtils.getNow());
                learningContent.setUpdatedBy(createdBy);
                learningContentRepository.save(learningContent);
            }
        }
        return learningContent;
    }

    @Override
    public <S extends LearningContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningContent> findById(Long id) {
        return learningContentRepository.findById(id);
    }

    @Override
    public Optional<LearningContent> findByBinaryContent(BinaryContent binaryContent) {
        return learningContentRepository.findByBinaryContent(binaryContent);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningContent> findAll() {
        return learningContentRepository.findAll();
    }

    @Override
    public Iterable<LearningContent> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        learningContentRepository.deleteById(aLong);
    }

    @Override
    public void delete(LearningContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningContent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<LearningContent> findByDescText(String desc) {
        return null;
    }

    @Override
    public Collection<LearningContent> findByDescBrief(String brief) {
        return null;
    }
}
