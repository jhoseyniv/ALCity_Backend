package com.alcity.service.challenge;


import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.challenge.ChallengeDTO;
import com.alcity.entity.alenum.Language;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.MemberType;
import com.alcity.entity.challenge.Challenge;
import com.alcity.entity.challenge.ChallengeInitiator;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.challenge.ChallengeRepository;
import com.alcity.utility.DateUtils;
import com.alcity.utility.GenerateSHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional

public class ChallengeService  implements ChallengeRepository {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private ChallengeInitiatorService challengeInitiatorService;

    @Override
    public <S extends Challenge> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Challenge> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Challenge> findById(Long aLong) {
        return challengeRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    @Override
    public Iterable<Challenge> findAllById(Iterable<Long> longs) {
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
    public void delete(Challenge entity) {
        challengeRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    public Challenge save(ChallengeDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Challenge challenge = new Challenge();
        ChallengeInitiator challengeInitiator = new ChallengeInitiator();
        Optional<ChallengeInitiator> initiatorOptional = challengeInitiatorService.findById(dto.getInitiatorId()) ;
        if(initiatorOptional.isPresent())
            challengeInitiator = initiatorOptional.get();
        else challengeInitiator = null;
        if (code.equalsIgnoreCase("Save")) { //Save
            challenge = new Challenge(dto.getTitle(),dto.getStartTime(), dto.getEndTime(), dto.getTimeIntervalByHour(),
                    dto.getSizeOfParticipantGroup(),dto.getDescription(),challengeInitiator);
            challengeRepository.save(challenge);
        }else{//edit
            Optional<Challenge> challengeOptional= challengeRepository.findById(dto.getId());
            if(challengeOptional.isPresent()) {
                challenge = challengeOptional.get();
                challenge.setTitle(dto.getTitle());
                challenge.setStartTime(dto.getStartTime());
                challenge.setEndTime(dto.getEndTime());
                challenge.setTimeIntervalByHour(dto.getTimeIntervalByHour());
                challenge.setSizeOfParticipantGroup(dto.getSizeOfParticipantGroup());
                challenge.setDescription(dto.getDescription());
                challengeRepository.save(challenge);
            }
        }
        return challenge;
    }


    @Override
    public void deleteAll(Iterable<? extends Challenge> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
