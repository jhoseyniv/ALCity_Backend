package com.alcity.service.appmember;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.puzzle.ALCityObjectDTO;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.MemberType;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.CustomizedUserRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.MemberTypeRepository;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppMemberService implements AppMemberRepository, CustomizedUserRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private BinaryContentRepository binaryContentRepository;
    @Autowired
    private MemberTypeRepository memberTypeRepository;

    public AppMember save(AppMemberDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeRepository.findByValue(dto.getMemberType()).get();
        UserGender gender = UserGender.getByTitle(dto.getGender());
        BinaryContent icon=null;
        if(dto.getIconId() == null || dto.getIconId() ==0)
                icon = binaryContentRepository.findByfileName("no_photo_avatar");
        else
            icon = binaryContentRepository.findById(dto.getIconId()).get();

        AppMember appMember=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            appMember = new AppMember(dto.getAge(),dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getMobile(),dto.getEmail(),icon,gender ,memberType
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            appMemberRepository.save(appMember);
        }else{//edit
            Optional<AppMember> appMemberOptional= appMemberRepository.findById(dto.getId());
            if(appMemberOptional.isPresent()) {
                appMember = appMemberOptional.get();
                appMember.setNickname(dto.getNickname());
                appMember.setUsername(dto.getUsername());
                appMember.setPassword(dto.getPassword());
                appMember.setMobile(dto.getMobile());
                appMember.setIcon(icon);
                appMember.setMemberType(memberType);
                appMember.setGender(gender);
                appMember.setAge(appMember.getAge());
                appMember.setVersion(appMember.getVersion()+1);
                appMember.setCreated(DateUtils.getNow());
                appMember.setUpdated(DateUtils.getNow());
                appMember.setCreatedBy(createdBy);
                appMember.setUpdatedBy(createdBy);
                appMemberRepository.save(appMember);
            }
        }
        return appMember;
    }

    @Override
    public <S extends AppMember> S save(S entity) {
        return appMemberRepository.save(entity);
    }

    @Override
    public <S extends AppMember> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember> findById(Long id) {
        return appMemberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AppMember> findAll() {
        return appMemberRepository.findAll();
    }

    @Override
    public Iterable<AppMember> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        appMemberRepository.deleteById(aLong);
    }

    @Override
    public void delete(AppMember entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public AppMember findByUsername(String username) {
        return appMemberRepository.findByUsername(username);
    }

    @Override
    public Collection<AppMember> findByMobile(String mobile) {
        return null;
    }

    @Override
    public AppMember findByEmail(String email) {
        return null;
    }

    public ALCityResponseObject login(String username, String password) {
        return null;
    }
}
