package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.pgimport.PGImportDTO;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PLTemplateService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = "Puzzle Group API list", description = "puzzle groups api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pg")

public class PGController {

    @Autowired
    private PGService pgService;
    @Autowired
    private PLTemplateService plTemplateService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PGObjectDTO> getObjectsForAPG(@PathVariable Long id) {
        Collection<PGObjectDTO> pgObjectDTOS = new ArrayList<PGObjectDTO>();
        Collection<PGObject> pgObjects = new ArrayList<PGObject>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            pgObjects = puzzleGroup.get().getAlCityObjectInPGS();
            pgObjectDTOS = DTOUtil.getPGObjectDTOS(pgObjects);
        }
        return  pgObjectDTOS;
    }
    @Operation( summary = "Fetch all Journey Step that define for a puzzle group ",  description = "Fetch all Journey Step that define for a puzzle group")
    @RequestMapping(value = "/id/{id}/journey-step/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<JourneyStepDTO> getJourneyStepsForAPG(@PathVariable Long id) {
        Collection<JourneyStepDTO> journeyStepDTOS = new ArrayList<JourneyStepDTO>();
        Collection<JourneyStep> journeySteps = new ArrayList<JourneyStep>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            journeySteps = puzzleGroup.get().getJourneyStepCollection();
            journeyStepDTOS = DTOUtil.getJourneyStepsDTOS(journeySteps);
        }
        return  journeyStepDTOS;
    }

    @Operation( summary = "Fetch all Puzzle Levels that define in a puzzle group ",  description = "Fetch all puzzle level for a puzzle group")
    @RequestMapping(value = "/id/{id}/pl/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PLDTO> getPuzzleLevelForAPG(@PathVariable Long id) {
        Collection<PLDTO> puzzleLevelLDTOS = new ArrayList<PLDTO>();
        Collection<PuzzleLevel> puzzleLevels = new ArrayList<PuzzleLevel>();
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        if(puzzleGroup.isPresent()) {
            puzzleLevels = puzzleGroup.get().getPuzzleLevels();
            puzzleLevelLDTOS = DTOUtil.getPuzzleLevelDTOS(puzzleLevels);
        }
        return  puzzleLevelLDTOS;
    }


    @Operation( summary = "get all Puzzle Groups ",  description = "get all Puzzle Groups")
    @GetMapping("/all")
    @CrossOrigin
    public Collection<PGDTO> getPuzzleGroups(Model model) {
        Collection<PuzzleGroup> puzzleGroupCollection = pgService.findAll();
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();
        puzzleGroupDTOCollection = DTOUtil.getPuzzleGroupDTOS(puzzleGroupCollection);
        return puzzleGroupDTOCollection;
    }

    @Operation( summary = "get a Puzzle Group by id",  description = "get all  a Puzzle Group")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PGDTO getPuzzleGroupById(@PathVariable Long id) {
          Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        PGDTO puzzleGroupDTO = new PGDTO();
        if(puzzleGroup.isPresent())  puzzleGroupDTO = DTOUtil.getPuzzleGroupDTO(puzzleGroup.get());
        return  puzzleGroupDTO;
    }

    @Operation( summary = "get all learning skill content for  a Puzzle Group ",  description = "get all learning skill for  a Puzzle Group")
    @RequestMapping(value = "/id/{id}/skills/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PGLearningSkillDTO> getLearningSKillsForPuzzleGroupById(@PathVariable Long id) {
        Optional<PuzzleGroup> puzzleGroup = pgService.findById(id);
        Collection<PGLearningSkillDTO> dtos = new ArrayList<PGLearningSkillDTO>();
        if(puzzleGroup.isPresent())  dtos = DTOUtil.getPGLearningSkillContentDTOS(puzzleGroup.get().getLearningSkillContents());
        return  dtos;
    }

    @Operation( summary = "Import a Puzzle Group ",  description = "Import a Puzzle Group entity to database")
    @PostMapping("/import")
    @CrossOrigin(origins = "*")
    //@Transactional
    public ResponseMessage importPuzzleGroup(@RequestBody PGImportDTO dto) throws UniqueConstraintException {
        PuzzleGroup savedRecord = null;
        ResponseMessage responseObject = new ResponseMessage();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = pgService.importPG(dto);
                Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(dto.getPuzzleTemplateId());
                PLTemplate plTemplate = plTemplateOptional.get();
                plTemplate.setPuzzleGroupId(savedRecord.getId());
                plTemplateService.save(plTemplate);
            } catch (RuntimeException e) {
                throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , PuzzleGroup.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            responseObject = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PuzzleGroup.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        }
        return responseObject;
    }

    @Operation( summary = "Save a Puzzle Group ",  description = "save a Puzzle Group entity to database")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePuzzleGroup(@RequestBody PGDTO dto) {
        PuzzleGroup savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(dto.getId());
        try{
            if (puzzleGroupOptional.isEmpty())
                savedRecord = pgService.save(dto,"Save");
            else
                savedRecord = pgService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , PuzzleGroup.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess,Status.ok.name(), PuzzleGroup.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.ok.name(), PuzzleGroup.class.getSimpleName() , -1L, SystemMessage.SaveOrEditMessage_Fail);

        return response;
    }

    @Operation( summary = "Delete a  Puzzle Group ",  description = "Delete a Puzzle group entity and their data to data base")
    @DeleteMapping("/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePuzzleGroupById(@PathVariable Long id) throws Exception {
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(id);
        ResponseMessage response=null;
        if(puzzleGroupOptional.isPresent()) {
            Collection<PuzzleLevel> puzzleLevels = puzzleGroupOptional.get().getPuzzleLevels();
            if (puzzleLevels.isEmpty()) {  // if no puzzle level present
                pgService.delete(puzzleGroupOptional.get());
                response = new ResponseMessage(ErrorType.DeleteSuccess,Status.error.name(), PuzzleGroup.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
            } else {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.ok.name(), PuzzleGroup.class.getSimpleName() ,  id, SystemMessage.ForeignKeyViolation);
            }
        }
        else {
            response = new ResponseMessage(ErrorType.DeleteSuccess, Status.error.name(), PuzzleGroup.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  response;
    }


}
