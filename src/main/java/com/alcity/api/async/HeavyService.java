package com.alcity.api.async;


import com.alcity.dto.plimpexport.PGObjectData;
import com.alcity.dto.plimpexport.PLCellData;
import com.alcity.dto.plimpexport.ruleexport.RuleData;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Service
public class HeavyService {
    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    AttributeService attributeService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    ActionService actionService;

    @Autowired
    InstanceService pgObjectInstanceService;

    @Async
    //@Transactional(readOnly = true)
    public CompletableFuture<Collection<PGObjectData>> getObjectsForPuzzleGroup(PuzzleLevel pl) {
        return CompletableFuture.supplyAsync(() -> {
            return DTOUtil.getObjectsForPuzzleGroup(pl, attributeService, actionService, pgObjectInstanceService);
        });
    }
    @Async
    //@Transactional(readOnly = true)
    public CompletableFuture<Collection<RuleData>> getRules(PuzzleLevel pl) {
        return CompletableFuture.supplyAsync(() -> {
            return DTOUtil.getPLRules(pl, attributeService, attributeValueService, plRulePostActionService);
        });
    }
    @Async
    public CompletableFuture<Collection<PLCellData>> getCells(Collection<PLCell> cells) {
        return CompletableFuture.supplyAsync(() -> {
            return DTOUtil.getPLCellDTOS(cells,attributeService);
        });
    }

}
