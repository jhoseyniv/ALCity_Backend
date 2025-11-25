package com.alcity.api.async;

import com.alcity.customexception.ResponseObject;
import com.alcity.dto.base.PLBinaryContentDTO;
import com.alcity.dto.plimpexport.*;
import com.alcity.dto.plimpexport.ruleexport.RuleData;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.puzzle.InstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    AsyncService asyncService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor; // Inject the executor

    @GetMapping("/async-read")
    public Collection<Attribute> getEntitiesMultithreaded() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Collection<Attribute>>> futures = new ArrayList<>();

        // Example: Divide the work into multiple tasks
        // In a real application, you might partition data based on IDs, ranges, etc.
        CompletableFuture<Collection<Attribute>> puzzleLevels =  asyncService.findEntitiesAsync();
        return puzzleLevels.get();
    }


    @RequestMapping(value = "/json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PLData createJsonFile(@PathVariable  Long id) throws IOException, ClassNotFoundException, InterruptedException, ExecutionException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = new PLData();
        Collection<PLBinaryContentDTO> plContents= new ArrayList<>();
        if(puzzleLevelOptional.isPresent()){
            plData = asyncService.getJsonFile(id);
            //plContents = puzzleLevelService.getContents(id);
            //PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            //byte[] plContentsBytes = ImageUtil.convertObjectToBytes(plContents);
            //puzzleLevel.setPlconetents(plContentsBytes);
            //puzzleLevelService.save(puzzleLevel);

        }
        return plData;
    }


}
