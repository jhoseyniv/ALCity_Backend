package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLTemplateDTO;
import com.alcity.dto.puzzle.PLTemplateDraftDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PLTemplateDraft;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLTemplateDraftRepository;
import com.alcity.repository.puzzle.PLTemplateRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLTemplateDraftService  implements PLTemplateDraftRepository {

    @Qualifier("PLTemplateDraftRepository")
    @Autowired
    PLTemplateDraftRepository plTemplateDraftRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;


    public PLTemplateDraft save(PLTemplateDraftDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLTemplateDraft templateDraft=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            templateDraft = new PLTemplateDraft(dto.getTitle(),dto.getJsonTemplate(),dto.getPuzzleLevelId() ,
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            plTemplateDraftRepository.save(templateDraft);
        }else{//edit
            Optional<PLTemplateDraft> plTemplateDraftOptional= plTemplateDraftRepository.findById(dto.getId());
            if(plTemplateDraftOptional.isPresent()) {
                templateDraft = plTemplateDraftOptional.get();
                templateDraft.setJsonTemplate(dto.getJsonTemplate());
                templateDraft.setTitle(dto.getTitle());
                templateDraft.setPuzzleLevelId(dto.getPuzzleLevelId());
                templateDraft.setUpdated(DateUtils.getNow());
                templateDraft.setVersion(templateDraft.getVersion()+1);
                plTemplateDraftRepository.save(templateDraft);
            }
        }
        return templateDraft;
    }


    @Override
    public <S extends PLTemplateDraft> S save(S entity) {
        return null;
    }

    @Override
    public <S extends PLTemplateDraft> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLTemplateDraft> findById(Long id) {
        return plTemplateDraftRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLTemplateDraft> findAll() {
        return null;
    }

    @Override
    public Iterable<PLTemplateDraft> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plTemplateDraftRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLTemplateDraft entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLTemplateDraft> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLTemplateDraft> findByTitle(String title) {
        return plTemplateDraftRepository.findByTitle(title);
    }
}
