package com.alcity.service.challenge;

import com.alcity.dto.challenge.ChallengeDTO;
import com.alcity.dto.challenge.ChallengeInitiatorDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.challenge.Challenge;
import com.alcity.entity.challenge.ChallengeInitiator;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.challenge.ChallengeInitiatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChallengeInitiatorService  implements ChallengeInitiatorRepository {

    @Autowired
    private ChallengeInitiatorRepository challengeInitiatorRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public <S extends ChallengeInitiator> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ChallengeInitiator> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChallengeInitiator> findById(Long aLong) {
        return challengeInitiatorRepository.findById(aLong) ;
    }

    public ChallengeInitiator save(ChallengeInitiatorDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        ChallengeInitiator challengeInitiator = new ChallengeInitiator();
        if (code.equalsIgnoreCase("Save")) { //Save
            challengeInitiator = new ChallengeInitiator(dto.getTitle(),dto.getInitiatorId());
            challengeInitiatorRepository.save(challengeInitiator);
        }else{//edit
            Optional<ChallengeInitiator> challengeInitiatorOptional= challengeInitiatorRepository.findById(dto.getId());
            if(challengeInitiatorOptional.isPresent()) {
                challengeInitiator = challengeInitiatorOptional.get();
                challengeInitiator.setTitle(dto.getTitle());
                challengeInitiator.setInitiatorId(dto.getInitiatorId());
                challengeInitiatorRepository.save(challengeInitiator);
            }
        }
        return challengeInitiator;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ChallengeInitiator> findAllById(Iterable<Long> longs) {
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
    public void delete(ChallengeInitiator entity) {
        challengeInitiatorRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChallengeInitiator> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ChallengeInitiator> findAll() {
        return challengeInitiatorRepository.findAll();
    }
}
