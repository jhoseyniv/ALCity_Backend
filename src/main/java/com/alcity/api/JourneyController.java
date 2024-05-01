package com.alcity.api;


import com.alcity.customexception.UniqueConstraintException;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.service.Journey.JourneyService;
import com.alcity.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ExceptionHandler(UniqueConstraintException.class)
    @PostMapping("/save")
    public JourneyDTO saveJourney(@RequestBody JourneyDTO journeyDTO)  {
        System.out.println("j"+journeyDTO.getGraphic().getFileName());
        JourneyDTO savedJourneyDTO = null;
        try {
            savedJourneyDTO = journeyService.save(journeyDTO);
        }catch (RuntimeException e )
        {
          //  throw new UniqueConstraintException(journey.getTitle(), journey.getId(), Journey.class.toString());
        }
        //Optional<Journey> output = journeyService.findById(savedJourney.getId());
        return savedJourneyDTO;
    }


}
