package com.alcity.api;


import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.puzzle.PGService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "Binary Content APIs", description = "All APIs for Binary content ... ")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@RestController
@RequestMapping("/bc") //binary content

public class BinaryContentController {

    @Autowired
    private BinaryContentService binaryContentService;
    @Autowired
    private PGService pgService;

    @RequestMapping(value="/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public BinaryContent getBinaryContentById(@PathVariable Long id) {
        Optional<BinaryContent> binaryContentOptional = binaryContentService.findById(id);
        if(binaryContentOptional.isPresent())
            return binaryContentOptional.get();
        return null;
    }
//    @Operation( summary = "Save a Binary Content to database  By Multipart",  description = "save a  Binary Content entity and their data to data base")
//    @PostMapping( value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @CrossOrigin(origins = "*")
//    public ALCityResponseObject saveBinaryContent(@RequestParam("file") MultipartFile file )  {
//        ALCityResponseObject responseObject = null;
//        try {
//
//            BinaryContent binaryContent = binaryContentService.save(file.getOriginalFilename(),file);
//            responseObject = new ALCityResponseObject(200,"ok",binaryContent.getId(), file.getOriginalFilename() + "binary content Saved Successfully..");
//
//        }catch (RuntimeException | IOException e ) {
//            //  throw new UniqueConstraintException(clientType.getLabel(), clientType.getId(), ClientType.class.toString());
//            // Optional<ClientType> output = clientTypeService.findById(savedRecord.getId());
//        }
//        return responseObject;
//    }

    @Operation( summary = "Save a Binary Content to database by DTO ",  description = "Save a  Binary Content entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveBinaryContentByDTO(@RequestBody BinaryContentDTO dto ) throws IOException {
        BinaryContent savedBinaryContent = null;
        ALCityResponseObject responseObject = null;
        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedBinaryContent = binaryContentService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getFileName(), dto.getId(), BinaryContent.class.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedBinaryContent.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            Optional<BinaryContent>  binaryContentOptional = binaryContentService.findById(dto.getId());
            savedBinaryContent = binaryContentService.save(dto, "Edit");
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedBinaryContent.getId(), "Record Updated Successfully!");
        }
        else
            responseObject = new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", savedBinaryContent.getId(), "Record Not Found!");

        return responseObject;
    }
    @Operation( summary = "Delete a  Binary Content ",  description = "Delete a Binary Content")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteById(@PathVariable Long id) {
        Optional<BinaryContent> existingRecord = this.binaryContentService.findById(id);
        if(existingRecord.isPresent()){
            try {
                binaryContentService.removeForeignKeys(id);
                binaryContentService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getFileName(), existingRecord.get().getId(), BinaryContent.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
