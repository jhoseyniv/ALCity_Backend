package com.alcity.repository.play;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PlayHistoryRepository extends CrudRepository<PlayHistory,Long> {
    Optional<PlayHistory> findById(Long id);
    Collection<PlayHistory> findAll();
    Collection<PlayHistory>  findByplayScore(Float score);
    Collection<PlayHistory>  findByPlayer(AppMember appMember);
}
