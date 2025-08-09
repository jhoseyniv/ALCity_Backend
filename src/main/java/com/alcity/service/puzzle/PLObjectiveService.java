package com.alcity.service.puzzle;

import com.alcity.dto.pl.PLObjectiveData;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.repository.puzzle.PLObjectiveRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
    AppMemberService applicationMemberService;

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
        objectiveRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLObjective entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLObjective> objectives) {
        objectiveRepository.deleteAll(objectives);
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
    private AppMemberRepository appMemberRepository;

    public Collection<PLObjective> copyObjectives(Collection<PLObjective> objectives,PuzzleLevel puzzleLevel){
        Collection<PLObjective> copiedObjectives = new ArrayList<>();
        Iterator<PLObjective> iterator = objectives.iterator();;
        while(iterator.hasNext()){
            PLObjective objective = iterator.next();
            PLObjective copyObjective = copy(objective,puzzleLevel);
            copiedObjectives.add(copyObjective);
        }
        return copiedObjectives;
    }

    public Collection<PLObjective> importObjectives(Collection<PLObjectiveData> objectives, PuzzleLevel puzzleLevel){
        Collection<PLObjective> copiedObjectives = new ArrayList<>();
        Iterator<PLObjectiveData> iterator = objectives.iterator();;
        while(iterator.hasNext()){
            PLObjectiveData objective = iterator.next();
            PLObjective copyObjective = importObjective(objective,puzzleLevel);
            copiedObjectives.add(copyObjective);
        }
        return copiedObjectives;
    }

    public PLObjective copy(PLObjective objective,PuzzleLevel copyPuzzleLevel) {
        PLObjective plObjective = new PLObjective(objective.getTitle(), objective.getDescription(), objective.getSkillAmount(),objective.getRewardAmount(),
                objective.getCondition(),objective.getLearningSkill(),objective.getWalletItem(),
                copyPuzzleLevel,1L, DateUtils.getNow(), DateUtils.getNow(),objective.getCreatedBy(), objective.getUpdatedBy());
        objectiveRepository.save(plObjective);
        return plObjective;
    }

    public PLObjective importObjective(PLObjectiveData objective,PuzzleLevel importedPuzzleLevel) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<LearningSkill> learningSkillOptional =  learningSkillRepository.findById(objective.getSkillId());
        Optional<WalletItem> walletItemOptional =  walletItemRespository.findById(objective.getRewardId());

        PLObjective plObjective = new PLObjective(objective.getTitle(), objective.getDescription(), objective.getSkillAmount(),objective.getRewardAmount(),
                objective.getCondition(),learningSkillOptional.get(),walletItemOptional.get(),importedPuzzleLevel,
                1L, DateUtils.getNow(), DateUtils.getNow(),createdBy.get(), createdBy.get());
        objectiveRepository.save(plObjective);
        return plObjective;
    }

    public PLObjective save(PLObjectiveDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLObjective plObjective=null;
        Optional<LearningSkill> learningSkillOptional =  learningSkillRepository.findById(dto.getSkillId());
        Optional<WalletItem> walletItemOptional =  walletItemRespository.findById(dto.getWalletItemId());

        WalletItem walletItem = null;
        PuzzleLevel puzzleLevel = null;
        Optional<PuzzleLevel>  puzzleLevelOptional = puzzleLevelRepository.findById(dto.getPuzzleLevelId());
        if(puzzleLevelOptional.isPresent())
              puzzleLevel = puzzleLevelOptional.get();
        if(walletItemOptional.isPresent())
             walletItem = walletItemOptional.get();
        StringBuffer condition = new StringBuffer(dto.getCondition());

        if (code.equalsIgnoreCase("Save")) { //Save
            plObjective = new PLObjective(dto.getTitle(), dto.getDescription(), dto.getSkillAmount(),dto.getRewardAmount(),
                    condition,learningSkillOptional.get(),walletItem,
                    puzzleLevel,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            objectiveRepository.save(plObjective);
        }else{//edit
            Optional<PLObjective> plObjectiveOptional= objectiveRepository.findById(dto.getId());
            if(plObjectiveOptional.isPresent()) {
                plObjective = plObjectiveOptional.get();
                plObjective.setCondition(condition);
                plObjective.setDescription(dto.getDescription());
                plObjective.setSkillAmount(dto.getSkillAmount());
                plObjective.setLearningSkill(learningSkillOptional.get());
                plObjective.setWalletItem(walletItemOptional.get());
                plObjective.setPuzzleLevel(puzzleLevel);
                plObjective.setCreated(DateUtils.getNow());
                plObjective.setUpdated(DateUtils.getNow());
                plObjective.setCreatedBy(createdBy.get());
                plObjective.setUpdatedBy(createdBy.get());
                plObjective.setVersion(puzzleLevel.getVersion()+1);
                objectiveRepository.save(plObjective);
            }
        }
        return plObjective;
    }



}
