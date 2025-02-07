package com.alcity.api;


import com.alcity.dto.puzzle.CameraSetupDTO;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.base.CameraSetupService;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.service.customexception.UniqueConstraintException;
import com.alcity.service.customexception.ViolateForeignKeyException;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Tag(name = " Camera Setup API ", description = "Get Puzzle Levels Ground Format for other systems...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/camera-setup")

public class CameraSetupController {
    @Autowired
    private CameraSetupService cameraSetupService;

    @Operation( summary = "Fetch all camera setup data ",  description = "fetches all data for camera setup ")
    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public Collection<CameraSetupDTO> getCameraSetups(Model model) {
        Collection<CameraSetupDTO> dtos = new ArrayList<CameraSetupDTO>();
        Collection<CameraSetup> cameraSetups = cameraSetupService.findAll();
        dtos = DTOUtil.getCameraSetupDTOS(cameraSetups);
        return dtos;
    }

    @Operation( summary = "Fetch Camera setup by a Id ",  description = "Fetch Camera setup by a Id ")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public CameraSetupDTO getCameraSetupId(@PathVariable Long id) {
        CameraSetupDTO   cameraSetupDTO= new CameraSetupDTO();
        Optional<CameraSetup> cameraSetupOptional = cameraSetupService.findById(id);
        cameraSetupDTO = DTOUtil.getCameraSetupDTO(cameraSetupOptional.get());
        return cameraSetupDTO;
    }


    @Operation( summary = "Save a Camera Setup information ",  description = "Save a puzzle level ground entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveCameraSetup(@RequestBody CameraSetupDTO dto)  {
        CameraSetup savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = cameraSetupService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException("PL", dto.getId(), "Code Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = cameraSetupService.save(dto, "Edit");
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
    @Operation( summary = "delete a  Camera Setup ",  description = "delete a Camera Setup")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteCameraSetupById(@PathVariable Long id) {
        Optional<CameraSetup> existingRecord = cameraSetupService.findById(id);
        if(existingRecord.isPresent()){
            try {
                cameraSetupService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), PuzzleLevel.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
