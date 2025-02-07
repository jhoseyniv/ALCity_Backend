package com.alcity.api;


import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.base.CameraSetupRepository;
import com.alcity.service.base.CameraSetupService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.service.puzzle.PLGroundService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Puzzle Level Ground API ", description = "Get Puzzle Levels Ground Format for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/pl-ground")
public class PLGroundController {

    @Autowired
    private PLGroundService plGroundService;

    @Operation( summary = "Fetch puzzle level Ground by a Id ",  description = "Fetch puzzle level Ground by a Id ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public PLGroundDTO getPLGroundById(@PathVariable Long id) {
        PLGroundDTO   plGroundDTO= new PLGroundDTO();
        Optional<PLGround> plGroundOptional = plGroundService.findById(id);
        plGroundDTO = DTOUtil.getPLGroundDTO(plGroundOptional.get());
        return plGroundDTO;
    }

    @Operation( summary = "Save a PL Ground information ",  description = "Save a puzzle level ground entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject savePLGround(@RequestBody PLGroundDTO dto)  {
        PLGround savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = plGroundService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException("PL", dto.getId(), "Code Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = plGroundService.save(dto, "Edit");
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

    @Operation( summary = "delete a  PL Ground ",  description = "delete a PL ground")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deletePLGroundById(@PathVariable Long id) {
        Optional<PLGround> existingRecord = plGroundService.findById(id);
        if(existingRecord.isPresent()){
            try {
                plGroundService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException("PLGround", existingRecord.get().getId(), PuzzleLevel.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
