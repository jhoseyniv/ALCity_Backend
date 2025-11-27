package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface AppMemberRepository extends CrudRepository<AppMember,Long> {
    Optional<AppMember> findById(Long id);
    Collection<AppMember> findAll();
    Optional<AppMember> findByUsername(String username);
    Collection<AppMember> findByMobile(String mobile);
    AppMember findByEmail(String email);
    Collection<AppMember> findByUsernameContainingIgnoreCaseOrNicknameContainingIgnoreCaseOrEmailIsContainingIgnoreCase(String userName, String nickName, String email);
    Collection<AppMember> findByRefillEnergyExpirationTimeBefore(LocalDateTime time);


}
