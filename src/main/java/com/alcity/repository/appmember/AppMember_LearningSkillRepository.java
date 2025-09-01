package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_LearningSkill;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.learning.LearningSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AppMember_LearningSkillRepository extends CrudRepository<AppMember_LearningSkill,Long> {
    Collection<AppMember_LearningSkill> findByApplicationMember(AppMember applicationMember);

    Optional<AppMember_LearningSkill> findByApplicationMemberAndLearningSkill(AppMember applicationMember, LearningSkill learningSkill);
}
