package com.alcity.service.puzzle;

import com.alcity.customexception.RecordNotFoundException;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.puzzle.PLObjectiveRepository;
import com.alcity.service.users.ApplicationMemberService;
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
    PuzzleLevelService puzzleLevelService;
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
        return null;
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



    public PLObjective saveDTO(PLObjectiveDTO ploDTO,Long plId) {
        Optional<PuzzleLevel> puzzleLeveL = puzzleLevelService.findById(plId);

        Optional<ApplicationMember> createdBy = applicationMemberService.findById(ploDTO.getCreatedById());
        Optional<ApplicationMember> updatedBy = applicationMemberService.findById(ploDTO.getUpdatedById());

        if(!puzzleLeveL.isPresent() || !createdBy.isPresent() || !updatedBy.isPresent())
            throw new RecordNotFoundException(plId,"record not found","exception");
        PLObjective plObjective = new PLObjective(ploDTO.getTitle(),ploDTO.getDescription(), ploDTO.getSkillAmount(),ploDTO.getRewardAmount(),
                ploDTO.getCondition(),null,null,puzzleLeveL.get(),ploDTO.getVersion(),ploDTO.getCreated(),ploDTO.getUpdated(),createdBy.get(),updatedBy.get());

        return null;
    }

}
