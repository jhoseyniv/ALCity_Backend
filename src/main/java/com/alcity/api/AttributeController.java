package com.alcity.api;

import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.puzzle.PuzzleLevelLDTO;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Attribute Entity  API's ", description = "Get Attributes API for ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/att")
public class AttributeController {


    @Autowired
    private AttributeService attributeService;



    @Operation( summary = "Fetch an Attribute data by a Id ",  description = "fetches all data for a Attribute")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public AttributeDTO getAttributeById(@PathVariable Long id) {
        AttributeDTO attributeDTO= new AttributeDTO();
        Optional<Attribute> attributeOptional = attributeService.findById(id);
        if(attributeOptional.isEmpty()) return null;
        attributeDTO = DTOUtil.getAttributeDTO(attributeOptional.get());
        return attributeDTO;
    }

}
