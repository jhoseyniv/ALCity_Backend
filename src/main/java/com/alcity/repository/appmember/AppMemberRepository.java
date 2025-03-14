package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AppMemberRepository extends CrudRepository<AppMember,Long> {
    Optional<AppMember> findById(Long id);
    Collection<AppMember> findAll();
    Optional<AppMember> findByUsername(String username);
    Collection<AppMember> findByMobile(String mobile);
    AppMember findByEmail(String email);

}
