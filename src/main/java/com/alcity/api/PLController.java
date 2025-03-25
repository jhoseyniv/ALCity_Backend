package com.alcity.api;

import com.alcity.dto.Interpreter.PLData;
import com.alcity.entity.appmember.AppMember;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.puzzle.PLGameInstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;


@Tag(name = "Puzzle Level API's ", description = "Get Puzzle Levels data Format for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl")
public class PLController {
    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private AppMemberService appMemberService;
    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    private PLGameInstanceService plGameInstanceService;

    @Operation( summary = "Fetch all puzzle level data ",  description = "fetches all data for all puzzle level structure ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getPuzzleLevels(Model model) {
        Collection<PLDTO> pldtos = new ArrayList<PLDTO>();
        Collection<PuzzleLevel> puzzleLevels = puzzleLevelService.findAll();
        Iterator<PuzzleLevel> itr = puzzleLevels.iterator();
        while(itr.hasNext()){
            PLDTO pldto = DTOUtil.getPuzzleLevelDTO(itr.next());
            pldtos.add(pldto);
        }
        return pldtos;
    }

    @Operation( summary = "Fetch puzzle level data by a Id ",  description = "fetches all data for a puzzle level ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLDTO getPuzzleLevelById(@PathVariable Long id) {
        PLDTO   pldto= new PLDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        pldto = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return pldto;
    }

    @Operation( summary = "Fetch puzzle level Json by a Id ",  description = "fetches Json for a puzzle level ")
    @RequestMapping(value = "json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLData getPuzzleLevelJsonById(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PLData plData = PLDTOUtil.getInterpreterJSON(puzzleLevelOptional.get());
        return plData;
    }


    /*
    @Operation( summary = "Fetch  step and journey mapped by a puzzle level by Id ",  description = "fetches all data for a puzzle level ")
    @RequestMapping(value = "/id/{id}/step", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PuzzleLevelStepMappingDTO getPuzzleLevelStepById(@PathVariable Long id) {
        PuzzleLevelStepMappingDTO dto= new PuzzleLevelStepMappingDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        dto = puzzleLevelService.getJourneyStepMappedWithPuzzleLevel(puzzleLevelOptional.get());
        return dto;
    }

 */

    @Operation( summary = "Fetch all puzzle levels for a user by age ",  description = "fetches all data for a puzzle level ")
    @RequestMapping(value = "/age/{age}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getPuzzleLevelsForAUserByAge(@PathVariable Integer age) {
        Collection<PLDTO> dtos= new ArrayList<>();
        Collection<PuzzleLevel> matchesToAge = puzzleLevelService.findAllMatchesToAge(age);
        dtos = DTOUtil.getPuzzleLevelDTOS(matchesToAge);
        return dtos;
    }

    @Operation( summary = "Fetch all puzzle levels matches with journey steps  for a specific user",  description = "Fetch all puzzle levels matches with journey steps")
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PuzzleLevelStepMappingDTO> getJourneyStepsMatchWithPuzzleLvels(@PathVariable Long id) {
        Optional<AppMember > memberOptional = appMemberService.findById(id);
        Collection<PuzzleLevel> matchesToAge = puzzleLevelService.findAllMatchesToAge(memberOptional.get().getAge());

        Collection<PuzzleLevelStepMappingDTO> dtos = puzzleLevelService.getJourneyStepsMatchWithPuzzleLvels(matchesToAge);
        return dtos;
    }

    @Operation( summary = "Fetch all objective by a puzzle level Id ",  description = "fetches all objectives for a puzzle level ")
    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return plObjectiveDTOCollection;
    }
    @Operation( summary = "Fetch Ground Information for a puzzle level by Id ",  description = "Fetch Ground Information for a puzzle level by Id ")
    @RequestMapping(value = "/id/{id}/ground", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLGroundDTO getGroundForPuzzleLevelById(@PathVariable Long id) throws IOException, ClassNotFoundException, JSONException {
        PLGroundDTO groundDTO= new PLGroundDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent()) {
            Optional<PLGround> plGroundOptional = plGroundService.findByPuzzleLevelId(puzzleLevelOptional.get().getId());
            if(plGroundOptional.isPresent())      groundDTO = DTOUtil.getPLGroundDTO(plGroundOptional.get());
        }
        return groundDTO;
    }

    /*

     */

 /*   @Operation( summary = "Fetch all variables for a puzzle level by  Id ",  description = "Fetch all variables for a puzzle level by  Id")
    @RequestMapping(value = "/id/{id}/variables/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<RecordData> getAllVariablesForPuzzleLevelById(@PathVariable Long id) {
        Collection<RecordData>  variables = DTOUtil.getAttributeForOwnerById(attributeService,id,AttributeOwnerType.Puzzle_Level_Variable);
        return  variables;
    }

  */
    @Operation( summary = "Fetch all learning topics for a puzzle level by  Id ",  description = "Fetch all variables for a puzzle level by  Id")
    @RequestMapping(value = "/id/{id}/learning-topic/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<Pl_LearningTopicDTO> getAllLearningTopicsForPuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isEmpty()) return  null;
        PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
        Collection<Pl_LearningTopicDTO>  pl_learningTopicDTOS = DTOUtil.getPl_LearningTopicDTOS(puzzleLevel);
        return  pl_learningTopicDTOS;
    }

    @Operation( summary = "Fetch all Instances by a puzzle level Id ",  description = "fetches all Instances for a puzzle level ")
    @RequestMapping(value = "/id/{id}/instances/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<ALCityObjectInstanceInPLDTO> getAllInstancesForPuzzleLevelById(@PathVariable Long id) {
        Collection<ALCityObjectInstanceInPLDTO> plInstancesDTOS= new ArrayList<ALCityObjectInstanceInPLDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plInstancesDTOS = DTOUtil.getPuzzleLevelInstance(puzzleLevelOptional.get());
        return plInstancesDTOS;
    }
    @Operation( summary = "Start Puzzle (Game) after play by user ",  description = "Update Puzzle (Game) Status after play by user")
    @PostMapping("/start-play")
    @CrossOrigin(origins = "*")
    public PLGameInstanceDTO StartGameEvent(@RequestBody PLEventDTO plEventDTO) {
        PLGameInstanceDTO instanceDTO =  plGameInstanceService.startGameInstance(plEventDTO);
        return instanceDTO;
    }

    @Operation( summary = "Update Puzzle (Game) Status after change status by user ",  description = "Update Puzzle (Game) Status after change status play by user")
    @PostMapping("/update-play")
    @CrossOrigin(origins = "*")
    public PLGameInstanceDTO updateGameEvent(@RequestBody PLEventDTO eventDTO) {
        PLGameInstanceDTO instanceDTO =  plGameInstanceService.updateGameInstanceStatus(eventDTO);
        return instanceDTO;
    }

    @Operation( summary = "Fetch all Rules by a puzzle level Id ",  description = "fetches all Rules for a puzzle level ")
    @RequestMapping(value = "/id/{id}/rules/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLRuleDTO> getAllRulesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLRuleDTO> plRuleDTOS= new ArrayList<PLRuleDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plRuleDTOS = DTOUtil.getRulesForPuzzleLevel(puzzleLevelOptional.get());
        return plRuleDTOS;
    }

    @Operation( summary = "Save a puzzle level  ",  description = "Save a puzzle level  entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePuzzleLevel(@RequestBody PLDTO dto)  {
        PuzzleLevel savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = puzzleLevelService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getCode(), dto.getId(), "Code Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            //Optional<PuzzleGroup>  puzzleGroupOptional = pgService.findById(dto.getId());
            savedRecord = puzzleLevelService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Updated Successfully!");
            else
                responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", dto.getId(), "Record Not Found!");
        }
        else if (savedRecord==null)
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", -1L, "Record Not Found!");

        return responseObject;
    }
    @Operation( summary = "delete a  Puzzle Level ",  description = "delete a Puzzle Level ")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> existingRecord = puzzleLevelService.findById(id);
        if(existingRecord.isPresent()){
            try {
                puzzleLevelService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), PuzzleLevel.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }
    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<CityObjectInPGDTO> getObjectsForAPG(@PathVariable Long id) {
        Collection<CityObjectInPGDTO> dtos = new ArrayList<CityObjectInPGDTO>();
        Collection<ALCityObjectInPG> alCityObjectInPGS = new ArrayList<ALCityObjectInPG>();
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(id);
        PuzzleGroup puzzleGroup =puzzleLevelOptional.get().getPuzzleGroup();
        if(puzzleGroup != null) {
            alCityObjectInPGS = puzzleGroup.getAlCityObjectInPGS();
            Iterator<ALCityObjectInPG> itr = alCityObjectInPGS.iterator();
            while(itr.hasNext()) {
                CityObjectInPGDTO dto = new CityObjectInPGDTO();
                ALCityObjectInPG entity = itr.next();
                dto = DTOUtil.getALCityObjectInPGDTO(entity);
                //Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(entity.getId(), AttributeOwnerType.Object_In_Puzzle_Group);
                dtos.add(dto);
            }
        }
        return  dtos;
    }




}
