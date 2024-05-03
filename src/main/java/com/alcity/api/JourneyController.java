package com.alcity.api;


import com.alcity.customexception.NotNullConstraintException;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.service.Journey.JourneyService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;
    @GetMapping("/all")
    public Collection<Journey> getJourneis(Model model) {
        Collection<Journey> journeyCollection = journeyService.findAll();
        return journeyCollection;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JourneyDTO getJourneyById(@PathVariable Long id) {
        Optional<Journey> journey = journeyService.findById(id);
        JourneyDTO journeyDTO = DTOUtil.getJourneyDTO(journey.get());
        return journeyDTO;
    }
    @RequestMapping(value = "/cond/{criteria}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<JourneyDTO> getJourneyByCriteria(@PathVariable String criteria) {
        Collection<Journey> journeyCollection = journeyService.findByTitleContains(criteria);
        Collection<JourneyDTO>  journeyDTOCollection = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeyCollection.iterator();

        while(itr.hasNext()){
            Journey journey = itr.next();
            JourneyDTO journeyDTO= DTOUtil.getJourneyDTO(journey);
            journeyDTOCollection.add(journeyDTO);
        }
        return journeyDTOCollection;
    }

    @PostMapping("/save")
    public Journey saveJourney(@RequestBody JourneyDTO journeyDTO)  {
        Journey savedJourney = null;
        if(journeyDTO.getGraphic().getId()==null)
            throw new NotNullConstraintException("Graphic Field Must not be Null",journeyDTO.getTitle(), Journey.class.toString());
        try {
            savedJourney = journeyService.save(journeyDTO);
        }catch (RuntimeException e )
        {
            throw new UniqueConstraintException(journeyDTO.getTitle(), journeyDTO.getId(), Journey.class.toString());
        }
        Optional<Journey> output = journeyService.findById(savedJourney.getId());
        return output.get();
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteJourneyById(@PathVariable Long id) {
        Optional<Journey> existingRecord = journeyService.findById(id);
        if(existingRecord.isPresent()){
            try {
                journeyService.deleteById(existingRecord.get().getId());

            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getTitle(), existingRecord.get().getId(), Journey.class.toString());
            }
            return new ResponseEntity<>("Record deleted Successfully!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
