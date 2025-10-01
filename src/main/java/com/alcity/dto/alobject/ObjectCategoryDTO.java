package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.entity.puzzle.BaseObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor

public class ObjectCategoryDTO extends BaseItemSetDTO {
    private Collection<BaseObject> puzzleObjectCollection;

    public ObjectCategoryDTO(Long id, String label, String value, Long version, String created, String updated, Collection<BaseObject> puzzleObjectCollection) {
        super(id, label, value, version, created, updated);
        this.puzzleObjectCollection = puzzleObjectCollection;
    }
}
