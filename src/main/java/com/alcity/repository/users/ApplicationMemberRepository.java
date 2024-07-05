package com.alcity.repository.users;

import com.alcity.entity.users.AppMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationMemberRepository extends CrudRepository<AppMember,Long> {
    Optional<AppMember> findById(Long id);
    Collection<AppMember> findAll();
    AppMember findByUsername(String username);
    Collection<AppMember> findByMobile(String mobile);
    AppMember findByEmail(String email);

}
