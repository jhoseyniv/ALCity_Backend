package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.dto.puzzle.PuzzleLevelLDTO;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.repository.puzzle.PLObjectiveRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.repository.users.WalletItemRespository;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.WalletItemService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PLObjectiveService implements PLObjectiveRepository {

    @Autowired
    @Qualifier("PLObjectiveRepository")
    PLObjectiveRepository objectiveRepository;
    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Autowired
    WalletItemRespository walletItemRespository;

    @Autowired
    LearningSkillRepository learningSkillRepository;

    @Autowired
    ApplicationMemberService applicationMemberService;

    @Override
    public <S extends PLObjective> S save(S entity) {
        return objectiveRepository.save(entity);
    }

    @Override
    public <S extends PLObjective> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLObjective> findById(Long id) {
        return objectiveRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLObjective> findAll() {
        return objectiveRepository.findAll();
    }

    @Override
    public Collection<PLObjective> findPuzzleLevelObjectiveByPuzzleLevelId(Long plId) {
        return objectiveRepository.findPuzzleLevelObjectiveByPuzzleLevelId(plId);
    }



    @Override
    public Iterable<PLObjective> findAllById(Iterable<Long> longs) {
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
    public void delete(PLObjective entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLObjective> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLObjective> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PLObjective> findByCondition(StringBuffer condition) {
        return null;
    }
    @Autowired
    private ApplicationMemberRepository applicationMemberRepository;

    public PLObjective save(PLObjectiveDTO dto, String code) {
        ApplicationMember createdBy = applicationMemberRepository.findByUsername("admin");
        PLObjective plObjective=null;
        Optional<LearningSkill> learningSkillOptional =  learningSkillRepository.findById(dto.getSkillId());
        Optional<WalletItem> walletItemOptional =  walletItemRespository.findById(dto.getWalletItemId());
        PuzzleLevel puzzleLevel = null;
        Optional<PuzzleLevel>  puzzleLevelOptional = puzzleLevelRepository.findById(dto.getPuzzleLevelId());
        if(puzzleLevelOptional.isPresent())
              puzzleLevel = puzzleLevelOptional.get();

        if (code.equalsIgnoreCase("Save")) { //Save
            plObjective = new PLObjective(dto.getTitle(), dto.getDescription(), dto.getSkillAmount(),dto.getRewardAmount(),
                    dto.getCondition(),learningSkillOptional.get(),walletItemOptional.get(),
                    puzzleLevel,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            objectiveRepository.save(plObjective);
        }else{//edit
            Optional<PLObjective> plObjectiveOptional= objectiveRepository.findById(dto.getId());
            if(plObjectiveOptional.isPresent()) {
                plObjective = plObjectiveOptional.get();
                plObjective.setCondition(dto.getCondition());
                plObjective.setDescription(dto.getDescription());
                plObjective.setSkillAmount(dto.getSkillAmount());
                plObjective.setLearningSkill(learningSkillOptional.get());
                plObjective.setWalletItem(walletItemOptional.get());
                plObjective.setPuzzleLevel(puzzleLevel);
                plObjective.setCreated(DateUtils.getNow());
                plObjective.setUpdated(DateUtils.getNow());
                plObjective.setCreatedBy(createdBy);
                plObjective.setUpdatedBy(createdBy);
                plObjective.setVersion(puzzleLevel.getVersion()+1);
                objectiveRepository.save(plObjective);
            }
        }
        return plObjective;
    }



}
