package com.alcity.service.base;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.CameraSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class CameraSetupService implements CameraSetupRepository {

    @Autowired
    private CameraSetupRepository cameraSetupRepository ;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public <S extends CameraSetup> S save(S entity) {
        return cameraSetupRepository.save(entity);
    }
    public CameraSetup save(CameraSetupDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        CameraSetup cameraSetup=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            cameraSetup = new CameraSetup(dto.getTitle(),dto.getxPosition(),dto.getyPosition(), dto.getzPosition(),
                                dto.getyRotation(),dto.getyRotation(),dto.getzRotation() ,
                    1L, "1714379790", "1714379790", createdBy, createdBy);
            cameraSetupRepository.save(cameraSetup);
        }else{//edit
            Optional<CameraSetup> cameraSetupOptional =  cameraSetupRepository.findById(dto.getId());
            if(cameraSetupOptional.isPresent()) {
                cameraSetup = cameraSetupOptional.get();
                cameraSetup.setTitle(dto.getTitle());
                cameraSetup.setxPosition(dto.getxPosition());
                cameraSetup.setxRotation(dto.getxRotation());
                cameraSetup.setyPosition(dto.getyPosition());
                cameraSetup.setyRotation(dto.getyRotation());
                cameraSetup.setzPosition(dto.getzPosition());
                cameraSetup.setzRotation(dto.getzRotation());
                cameraSetupRepository.save(cameraSetup);
            }
        }
        return cameraSetup;
    }

    @Override
    public <S extends CameraSetup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CameraSetup> findById(Long aLong) {
        return cameraSetupRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<CameraSetup> findAll() {
        return cameraSetupRepository.findAll();
    }

    @Override
    public Iterable<CameraSetup> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        cameraSetupRepository.deleteById(aLong);
    }

    @Override
    public void delete(CameraSetup entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CameraSetup> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
