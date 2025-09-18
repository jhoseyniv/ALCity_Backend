package com.alcity.service.appmember;


import com.alcity.dto.appmember.AppMemberSkillScoreDTO;
import com.alcity.entity.alenum.SkillType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_LearningSkill;
import com.alcity.entity.appmember.PLObjectiveTransaction;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_LearningSkillRepository;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class AppMember_LearningSkillService implements AppMember_LearningSkillRepository {

    @Autowired
    AppMember_LearningSkillRepository appMember_LearningSkillRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private LearningSkillService learningSkillService;


    @Override
    public Collection<AppMember_LearningSkill> findByApplicationMember(AppMember applicationMember) {
        return appMember_LearningSkillRepository.findByApplicationMember(applicationMember);
    }

    @Override
    public Optional<AppMember_LearningSkill> findByApplicationMemberAndLearningSkill(AppMember applicationMember, LearningSkill learningSkill) {
        return appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(applicationMember,learningSkill);
    }

    public void updateAppMemberMicroSkills(PLObjectiveTransaction transaction) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        AppMember_LearningSkill appMemberLearningSkill = null;
        AppMember appMember = transaction.getGameInstance().getPlayer();
        PLObjective objective = transaction.getPlObjective();
        LearningSkill learningSkill = objective.getLearningSkill();
        Optional<AppMember_LearningSkill> appMemberLearningSkillOptional = appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(appMember,learningSkill);
        if(appMemberLearningSkillOptional.isEmpty()) {
            Float sumAmount = transaction.getAmount() + appMemberLearningSkill.getAmount();
            Long  levelUpSize = learningSkill.getLevelUpSize();
            Long level = (long) (sumAmount / levelUpSize);
            Float reminder = (Float) (sumAmount % levelUpSize);
            appMemberLearningSkill.setAmount(reminder);
            appMemberLearningSkill.setLevel(level);
            appMemberLearningSkill = new AppMember_LearningSkill(appMember,learningSkill, reminder,level,1L, DateUtils.getNow(),DateUtils.getNow(),
                    createdBy.get(),createdBy.get());
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }else{
            appMemberLearningSkill = appMemberLearningSkillOptional.get();
            Float sumAmount = transaction.getAmount() + appMemberLearningSkill.getAmount();
            Long  levelUpSize = learningSkill.getLevelUpSize();
            Long level = (long) (sumAmount / levelUpSize);
            Float reminder = (Float) (sumAmount % levelUpSize);
            appMemberLearningSkill.setAmount(reminder);
            appMemberLearningSkill.setLevel(level);
            appMemberLearningSkill.setVersion(appMemberLearningSkill.getVersion()+1);
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }
    }
    public Float getMin(Float f1,Float f2 ) {
        if(f1 > f2) {
            return f2;
        }
        return f1;
    }
        public Float sigmaOfSkillScores(Collection<AppMemberSkillScoreDTO> scores) {

        Iterator<AppMemberSkillScoreDTO> iterator = scores.iterator();
        Float sum = 0.0f;
        while(iterator.hasNext()){
            AppMemberSkillScoreDTO appMemberSkillScoreDTO = iterator.next();
            sum += getMin(appMemberSkillScoreDTO.getAmount(),10f);
        }
        return sum;
    }

    public AppMember_LearningSkill calculateSubSetSkillScore(AppMember_LearningSkill appMemberLearningSkill , LearningSkill subsetSkill) {
        Collection<LearningSkill> microSkills = learningSkillService.findByParentSkill(subsetSkill);
        Collection<AppMemberSkillScoreDTO> dtos = new ArrayList<>();
        Iterator<LearningSkill> iterator = microSkills.iterator();
        while(iterator.hasNext()){
            LearningSkill microSkill = iterator.next();
            Optional<AppMember_LearningSkill> appMemberMicroSkillOptional =  appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(appMemberLearningSkill.getApplicationMember(),microSkill);
            if(appMemberMicroSkillOptional.isPresent()){
                AppMember_LearningSkill appMemberLearningSkill1 = appMemberMicroSkillOptional.get();
                AppMemberSkillScoreDTO dto= new AppMemberSkillScoreDTO(microSkill.getId(), microSkill.getTitle(),microSkill.getDescription(),appMemberLearningSkill1.getLevel(), SkillType.MicroSkill.name(), appMemberLearningSkill1.getAmount(),null,null);
                dtos.add(dto);
            }else{
                AppMemberSkillScoreDTO dto= new AppMemberSkillScoreDTO(microSkill.getId(), microSkill.getTitle(),microSkill.getDescription(),0L, SkillType.MicroSkill.name(), 0f,null,null);
                dtos.add(dto);
            }
        }
        AppMemberSkillScoreDTO minLevel =  Collections.min(dtos, Comparator.comparing(s -> s.getSkillLevel()));
        appMemberLearningSkill.setLevel(minLevel.getSkillLevel());
        Float score = sigmaOfSkillScores(dtos);
        appMemberLearningSkill.setAmount(score);

       return appMemberLearningSkill;
    }

    public void updateAppMemberSubSetSkills(PLObjectiveTransaction transaction) {
       LearningSkill microSkill = transaction.getPlObjective().getLearningSkill();
       LearningSkill subSetSkill = microSkill.getParentSkill();
       Optional<AppMember_LearningSkill> appMemberLearningSkillOptional =  appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(transaction.getAppMember(),subSetSkill);
       if(appMemberLearningSkillOptional.isPresent()) {
           AppMember_LearningSkill appMemberLearningSkill= appMemberLearningSkillOptional.get();
           appMemberLearningSkill = calculateSubSetSkillScore(appMemberLearningSkill,subSetSkill);
           appMember_LearningSkillRepository.save(appMemberLearningSkill);
       }else{
           AppMember_LearningSkill appMemberLearningSkill = new AppMember_LearningSkill(transaction.getAppMember(),subSetSkill,0f,0L,1L,
                   DateUtils.getNow(),DateUtils.getNow(),transaction.getAppMember(),transaction.getAppMember());
           appMemberLearningSkill = calculateSubSetSkillScore(appMemberLearningSkill,subSetSkill);
           appMember_LearningSkillRepository.save(appMemberLearningSkill);
       }

    }

    public AppMember_LearningSkill calculateSkillScore(AppMember_LearningSkill appMemberLearningSkill , LearningSkill skill) {
        Collection<LearningSkill> subSetSkills = learningSkillService.findByParentSkill(skill);
        Collection<AppMemberSkillScoreDTO> dtos = new ArrayList<>();
        Iterator<LearningSkill> iterator = subSetSkills.iterator();
        while(iterator.hasNext()){
            LearningSkill microSkill = iterator.next();
            Optional<AppMember_LearningSkill> appMemberMicroSkillOptional =  appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(appMemberLearningSkill.getApplicationMember(),microSkill);
            if(appMemberMicroSkillOptional.isPresent()){
                AppMember_LearningSkill appMemberLearningSkill1 = appMemberMicroSkillOptional.get();
                AppMemberSkillScoreDTO dto= new AppMemberSkillScoreDTO(microSkill.getId(), microSkill.getTitle(),microSkill.getDescription(),appMemberLearningSkill1.getLevel(), SkillType.MicroSkill.name(), appMemberLearningSkill1.getAmount(),null,null);
                dtos.add(dto);
            }else{
                AppMemberSkillScoreDTO dto= new AppMemberSkillScoreDTO(microSkill.getId(), microSkill.getTitle(),microSkill.getDescription(),0L, SkillType.MicroSkill.name(), 0f,null,null);
                dtos.add(dto);
            }
            AppMemberSkillScoreDTO minLevel =  Collections.max(dtos, Comparator.comparing(s -> s.getSkillLevel()));
            appMemberLearningSkill.setLevel(minLevel.getSkillLevel());
            Float score = sigmaOfSkillScores(dtos);
            appMemberLearningSkill.setAmount(score);
        }
        return appMemberLearningSkill;
    }

    public void updateAppMemberSkills(PLObjectiveTransaction transaction) {
        LearningSkill microSkill = transaction.getPlObjective().getLearningSkill();
        LearningSkill subSetSkill = microSkill.getParentSkill();
        LearningSkill skill = subSetSkill.getParentSkill();

        Optional<AppMember_LearningSkill> appMemberLearningSkillOptional =  appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(transaction.getAppMember(),skill);
        if(appMemberLearningSkillOptional.isPresent()) {
            AppMember_LearningSkill appMemberLearningSkill= appMemberLearningSkillOptional.get();
            appMemberLearningSkill = calculateSkillScore(appMemberLearningSkill,subSetSkill);
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }else{
            AppMember_LearningSkill appMemberLearningSkill = new AppMember_LearningSkill(transaction.getAppMember(),subSetSkill,0f,0L,1L,
                    DateUtils.getNow(),DateUtils.getNow(),transaction.getAppMember(),transaction.getAppMember());
            appMemberLearningSkill = calculateSkillScore(appMemberLearningSkill,subSetSkill);
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }

    }

    @Override
    public <S extends AppMember_LearningSkill> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AppMember_LearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember_LearningSkill> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<AppMember_LearningSkill> findAll() {
        return null;
    }

    @Override
    public Iterable<AppMember_LearningSkill> findAllById(Iterable<Long> longs) {
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
    public void delete(AppMember_LearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember_LearningSkill> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
