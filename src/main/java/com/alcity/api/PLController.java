package com.alcity.api;

import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.base.PLBinaryContentDTO;
import com.alcity.dto.learning.LearningSkillTreeDTO;
import com.alcity.dto.plimpexport.PLContentsDTO;
import com.alcity.dto.plimpexport.PLData;
import com.alcity.dto.plimpexport.PLImportDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.puzzle.*;
import com.alcity.service.appmember.DynamicSchedulerService;
import com.alcity.service.appmember.EnergyConfigService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.puzzle.PLGameInstanceService;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.service.puzzle.PLTemplateService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import com.alcity.utility.PLDTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
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
    private PuzzleLevelService plService;

    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private EnergyConfigService energyConfigService;

    @Autowired
    private PLGroundService plGroundService;

    @Autowired
    private PLTemplateService plTemplateService;

    @Autowired
    private PLGameInstanceService plGameInstanceService;

    @Autowired
    private LearningSkillService learningSkillService;
    @Autowired
    private BinaryContentService binaryContentService;

    @Operation( summary = "Fetch all puzzle level data ",  description = "fetches all data for all puzzle level structure ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    @Cacheable("all-PLDTO")
    public Collection<PLDTO> getPuzzleLevels() {
        Collection<PLDTO> pldtos = new ArrayList<PLDTO>();
        Collection<PuzzleLevel> puzzleLevels = plService.findAll();
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
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        pldto = DTOUtil.getPuzzleLevelDTO(puzzleLevelOptional);
        return pldto;
    }

    @Operation( summary = "Fetch puzzle level Json by a Id ",  description = "fetches Json for a puzzle level ")
    @RequestMapping(value = "json/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLData getPuzzleLevelJsonById(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        PLData plData = PLDTOUtil.getInterpreterJSON(puzzleLevelOptional.get());
        return plData;
    }


    @Operation( summary = "Fetch all Binary Contents for a puzzle level by id ",  description = "Fetch all binary contents a puzzle leve")
    @RequestMapping(value = "/id/{id}/contents", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    @Cacheable(value = "getPuzzleLevelContents", key = "#id")
    public Collection<PLBinaryContentDTO> getPuzzleLevelContents(@PathVariable Long id) throws IOException, ClassNotFoundException {
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        Collection<PLBinaryContentDTO> plContents = new ArrayList<>();
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            if(puzzleLevel.getPlconetents()!=null)
                plContents = PLDTOUtil.getPLContentsJSON(puzzleLevel);
            else {
                plContents = plService.getContents(id);
                byte[] plContentsBytes = ImageUtil.convertObjectToBytes(plContents);
                puzzleLevel.setPlconetents(plContentsBytes);
                plService.save(puzzleLevel);
            }
        }
        return plContents;
    }


    @Operation( summary = "Fetch all puzzle levels for a user by age ",  description = "fetches all data for a puzzle level ")
    @RequestMapping(value = "/age/{age}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLDTO> getPuzzleLevelsForAUserByAge(@PathVariable Integer age) {
        Collection<PLDTO> dtos= new ArrayList<>();
        Collection<PuzzleLevel> matchesToAge = plService.findAllMatchesToAge(age);
        dtos = DTOUtil.getPuzzleLevelDTOS(matchesToAge);
        return dtos;
    }

    @Operation( summary = "Fetch all puzzle levels matches with journey steps  for a specific user",  description = "Fetch all puzzle levels matches with journey steps")
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PuzzleLevelStepMappingDTO> getJourneyStepsMatchWithPuzzleLvels(@PathVariable Long id) {
        Optional<AppMember > memberOptional = appMemberService.findById(id);
        Collection<PuzzleLevel> matchesToAge = plService.findAllMatchesToAge(memberOptional.get().getAge());

        Collection<PuzzleLevelStepMappingDTO> dtos = plService.getJourneyStepsMatchWithPuzzleLvels(matchesToAge);
        return dtos;
    }

    @Operation( summary = "Fetch all objective by a puzzle level Id ",  description = "fetches all objectives for a puzzle level ")
    @RequestMapping(value = "/id/{id}/objectives/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLObjectiveDTO> getAllObjectivesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection= new ArrayList<PLObjectiveDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevelOptional.get());
        return plObjectiveDTOCollection;
    }
    @Operation( summary = "Fetch Learning Skills tree for a puzzle level Id ",  description = "fetches Learning Skills trees for a puzzle level by id ")
    @RequestMapping(value = "/id/{id}/learning-skills/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<LearningSkillTreeDTO> getAllLearningSkillById(@PathVariable Long id) {
        Collection<LearningSkillTreeDTO> learningSkillDTOS= new ArrayList<LearningSkillTreeDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isPresent()) {
            learningSkillDTOS = DTOUtil.getLearningSkillDTOSFromPL(puzzleLevelOptional.get(),learningSkillService);
        }
        return learningSkillDTOS;
    }

    @Operation( summary = "Fetch Ground Information for a puzzle level by Id ",  description = "Fetch Ground Information for a puzzle level by Id ")
    @RequestMapping(value = "/id/{id}/ground", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLGroundDTO getGroundForPuzzleLevelById(@PathVariable Long id) throws IOException, ClassNotFoundException, JSONException {
        PLGroundDTO groundDTO= new PLGroundDTO();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isPresent()) {
            Optional<PLGround> plGroundOptional = plGroundService.findByPuzzleLevelId(puzzleLevelOptional.get().getId());
            if(plGroundOptional.isPresent())      groundDTO = DTOUtil.getPLGroundDTO(plGroundOptional.get());
        }
        return groundDTO;
    }


    @Operation( summary = "Fetch all learning topics for a puzzle level by  Id ",  description = "Fetch all variables for a puzzle level by  Id")
    @RequestMapping(value = "/id/{id}/learning-topic/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PlLearningTopicDTO> getAllLearningTopicsForPuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isEmpty()) return  null;
        PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
        Collection<PlLearningTopicDTO>  learningTopicDTOS = DTOUtil.getPlLearningTopicDTOS(puzzleLevel);
        return  learningTopicDTOS;
    }

    @Operation( summary = "Fetch all Instances by a puzzle level Id ",  description = "fetches all Instances for a puzzle level ")
    @RequestMapping(value = "/id/{id}/instances/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<InstanceDTO> getAllInstancesForPuzzleLevelById(@PathVariable Long id) {
        Collection<InstanceDTO> plInstancesDTOS= new ArrayList<InstanceDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plInstancesDTOS = DTOUtil.getInstancesDTOS(puzzleLevelOptional.get());
        return plInstancesDTOS;
    }

    @Operation( summary = "Start Puzzle (Game) after play by user ",  description = "Update Puzzle (Game) Status after play by user")
    @PostMapping("/start-play")
    @CrossOrigin(origins = "*")
    public ResponseMessage StartGameEvent(@RequestBody PLEventDTO plEventDTO) {
        ResponseMessage response = new ResponseMessage();
        //check is user enough energy or not
        Integer energy =5;
        Optional<AppMember> memberOptional = appMemberService.findById(plEventDTO.getAppMemberId());
        Optional<EnergyConfig> energyConfigOptional = energyConfigService.findByExpireIsFalse();
        if(energyConfigOptional.isPresent())
            energy = energyConfigOptional.get().getEnergy();
        if(memberOptional.isPresent()) {
            if(memberOptional.get().getEnergy()==0) {
                return
                        new ResponseMessage(ErrorType.Energy_Is_Not_Sufficient_To_PLay_Game, Status.ok.name(), AppMember.class.getSimpleName(),
                                memberOptional.get().getId(), SystemMessage.Energy_Is_Not_Sufficient_To_PLay_Game);
            }
            if(memberOptional.get().getEnergy()<energy) {
                appMemberService.startUserTimer(plEventDTO.getAppMemberId());
                return
                        new ResponseMessage(ErrorType.Energy_Is_Low_And_Timer_Start_To_Refill,  Status.ok.name(),AppMember.class.getSimpleName() ,
                                                memberOptional.get().getId(), SystemMessage.Energy_Is_Low_And_Timer_Start_To_Refill);
            }
            PLGameInstanceDTO gameInstance = plGameInstanceService.startGameInstance(plEventDTO);
            response = new ResponseMessage(ErrorType.SaveSuccess,  Status.ok.name(),PLGameInstanceDTO.class.getSimpleName() , gameInstance.getId(), SystemMessage.Game_Instance_Created_Successfully);
        }

        return response;
    }

    @Operation( summary = "End Puzzle (Game) Status after change status by user ",  description = "Update Puzzle (Game) Status after change status play by user")
    @PostMapping("/end-play")
    @CrossOrigin(origins = "*")
    public PLGameInstanceDTO updateGameEvent(@RequestBody PLEventDTO eventDTO) {
        return plGameInstanceService.updateGameInstanceStatus(eventDTO);
    }

    @Operation( summary = "Get all Game Play for a puzzle Level Member",  description = "get all Game Play  for a Puzzle Level ...")
    @RequestMapping(value = "/id/{id}/game-play-all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLGameInstanceDTO> getAllPlayGameForUser(@PathVariable Long id) {
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isEmpty()) return  null;
        Collection<PLGameInstance>  histories= plGameInstanceService.findByPuzzleLevel(puzzleLevelOptional.get());
        Collection<PLGameInstanceDTO> dtos =  DTOUtil.getPLGameInstanceDTOS(histories);
        return dtos;
    }

    @Operation( summary = "Fetch all Rules by a puzzle level Id ",  description = "fetches all Rules for a puzzle level ")
    @RequestMapping(value = "/id/{id}/rules/all", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<PLRuleDTO> getAllRulesForPuzzleLevelById(@PathVariable Long id) {
        Collection<PLRuleDTO> plRuleDTOS= new ArrayList<PLRuleDTO>();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        if(puzzleLevelOptional.isPresent())
            plRuleDTOS = DTOUtil.getRulesForPuzzleLevel(puzzleLevelOptional.get());
        return plRuleDTOS;
    }

    @Operation( summary = "Copy a puzzle level by id  ",  description = "copy a puzzle level  entity and their data")
    @PostMapping("/copy")
    @CrossOrigin(origins = "*")
    public ResponseObject copyPuzzleLevel(@RequestBody PLCopyDTO dto) {
        /*copy memory game with id
        {
          "title": "copy of memory game",
          "code": "4800",
          "fromAge": 16,
          "toAge": 19,
          "puzzleLevelId": 461 ,
          "rules": true,
          "variables": true,
          "objectives": true,
          "instances": true,
          "learningTopics": true,
          "plground": true
        }
        *
        */
        ResponseObject responseObject = new ResponseObject();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(dto.getPuzzleLevelId());
        if(puzzleLevelOptional.isEmpty())
            return  new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), puzzleLevelOptional.get().getId(), SystemMessage.RecordNotFound);
        PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
        PuzzleLevel copyPuzzleLevel =plService.copy(puzzleLevel,dto);
        return  new ResponseObject(ErrorType.CopySuccess, ObjectAction.class.getSimpleName() , Status.ok.name(), copyPuzzleLevel.getId(), SystemMessage.SaveOrEditMessage_Success);

    }
    @Operation( summary = "Import a puzzle level",  description = "Import a puzzle level  entity and their data")
    @PostMapping("/import")
    @CrossOrigin(origins = "*")
    @Transactional
    public ResponseMessage importPuzzleLevel(@RequestBody PLImportDTO dto) throws IOException, ClassNotFoundException {
        PuzzleLevel importedPuzzleLevel=null;
        ResponseMessage response = new ResponseMessage();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(dto.getId());
        if(puzzleLevelOptional.isEmpty()){
            importedPuzzleLevel =  plService.importPuzzleLevel(dto);
         }else{
            //first delete exist puzzle level and then add new pl
            plService.deletePuzzleLevel(puzzleLevelOptional.get());
            importedPuzzleLevel =  plService.importPuzzleLevel(dto);
            }
        Optional<PLTemplate> plTemplateOptional = plTemplateService.findById(dto.getPuzzleTemplateId());
        PLTemplate plTemplate = plTemplateOptional.get();
        plTemplate.setPuzzleLevelId(importedPuzzleLevel.getId());
        plTemplateService.save(plTemplate);
        response = new ResponseMessage(ErrorType.ImportSuccess,  Status.ok.name(),PuzzleLevel.class.getSimpleName() , importedPuzzleLevel.getId(), SystemMessage.SaveOrEditMessage_Success);
        return response;
    }

    @Operation( summary = "Save a puzzle level  ",  description = "Save a puzzle level  entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage savePuzzleLevel(@RequestBody PLDTO dto)  {
        PuzzleLevel savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(dto.getId());

        try{
            if (puzzleLevelOptional.isEmpty())
                savedRecord = plService.save(dto,"Save");
            else
                savedRecord = plService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , AppMember.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),AppMember.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), AppMember.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }


    @Operation( summary = "Delete a  Puzzle Level ",  description = "delete a Puzzle Level ")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deletePuzzleLevelById(@PathVariable Long id) {
        Optional<PuzzleLevel> requestedRecord = plService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                plService.deletePuzzleLevel(requestedRecord.get());
            }
            catch (Exception e) {
                throw  new ResponseObject(ErrorType.ForeignKeyViolation,Status.error.name(), AppMember.class.getSimpleName(),  id,e.getCause().getMessage());
            }
            return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(), AppMember.class.getSimpleName(), id,SystemMessage.DeleteMessage);
        }
        return  new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(),AppMember.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }


    @Autowired
    private AttributeService attributeService;

    @Operation( summary = "Fetch all AL City Object for that define in a puzzle group ",  description = "Fetch all Al city object for an puzzle group")
    @RequestMapping(value = "/id/{id}/objects/all", method = RequestMethod.GET)
    @ResponseBody
    public Collection<PGObjectDTO> getObjectsForAPG(@PathVariable Long id) {
        Collection<PGObjectDTO> dtos = new ArrayList<PGObjectDTO>();
        Collection<PGObject> alCityObjectInPGS = new ArrayList<PGObject>();
        Optional<PuzzleLevel> puzzleLevelOptional = plService.findById(id);
        PuzzleGroup puzzleGroup =puzzleLevelOptional.get().getPuzzleGroup();
        if(puzzleGroup != null) {
            alCityObjectInPGS = puzzleGroup.getAlCityObjectInPGS();
            Iterator<PGObject> itr = alCityObjectInPGS.iterator();
            while(itr.hasNext()) {
                PGObjectDTO dto = new PGObjectDTO();
                PGObject entity = itr.next();
                dto = DTOUtil.getPGObjectDTO(entity);
                //Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerType(entity.getId(), AttributeOwnerType.Object_In_Puzzle_Group);
                dtos.add(dto);
            }
        }
        return  dtos;
    }




}
