package com.alcity.repository.users;

import com.alcity.entity.users.ApplicationMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationMemberRepository extends CrudRepository<ApplicationMember,Long> {
    Optional<ApplicationMember> findById(Long id);
    Collection<ApplicationMember> findAll();
    ApplicationMember findByUsername(String username);
    ApplicationMember findByMobile(String mobile);
    ApplicationMember findByEmail(String email);

}