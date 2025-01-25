package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AuthorityRepository  extends CrudRepository<Authority,Long> {

    Optional<Authority> findById(Long id);
    Collection<Authority> findAll();
    AppMember findByName(String name);
}
