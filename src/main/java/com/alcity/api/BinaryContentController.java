package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.puzzle.PGService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
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

    @GetMapping("/get-file/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
       Optional<BinaryContent>  binaryContentOptional= binaryContentService.findById(id);
       if(binaryContentOptional.isEmpty()) return  null;
       BinaryContent binaryContent = binaryContentOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + binaryContent.getFileName() + "\"")
                .body(binaryContent.getContent());
    }
    @PostMapping("/search")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Collection<BinaryContentDTO> getBinaryContentBySearchCriteria(@RequestBody ContentSearchCriteriaDTO criteriaDTO ) {
        Collection<BinaryContent> binaryContentCollection = binaryContentService.findByCriteria(criteriaDTO);
        return DTOUtil.getBinaryContentsWithoutContent(binaryContentCollection);
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

    @Operation( summary = "Save a Binary Content to database by DTO ",  description = "Save a Binary Content entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveBinaryContentByDTO(@RequestBody BinaryContentDTO dto ) throws IOException, ResponseObject {
        BinaryContent savedRecord = null;
        ResponseMessage response= null;
        if(dto.getId()==null ) dto.setId(-1L);
        Optional<BinaryContent> binaryContentOptional = binaryContentService.findById(dto.getId());

        try{
            if (binaryContentOptional.isEmpty())
                savedRecord = binaryContentService.save(dto,"Save");
            else
                savedRecord = binaryContentService.save(dto, "Edit");
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation, Status.error.name() , BinaryContent.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        if(savedRecord !=null)
            response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name() , BinaryContent.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        else
            response = new ResponseMessage(ErrorType.RecordNotFound, Status.error.name() , BinaryContent.class.getSimpleName() , dto.getId(), SystemMessage.SaveOrEditMessage_Fail);
        return response;
    }

    @Operation( summary = "Delete a  Binary Content ",  description = "Delete a Binary Content")
    @DeleteMapping("/del/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteById(@PathVariable Long id) {
        Optional<BinaryContent> requestedRecord = binaryContentService.findById(id);
        if(requestedRecord.isPresent()){
            try {
                binaryContentService.delete(requestedRecord.get());
            }
            catch (Exception e) {
                return new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), BinaryContent.class.getSimpleName(), id,e.getCause().getMessage());
            }
            return new ResponseObject(ErrorType.SaveSuccess, Status.ok.name(), BinaryContent.class.getSimpleName(),  id,SystemMessage.DeleteMessage);
        }
        return  new ResponseObject(ErrorType.RecordNotFound, Status.error.name(),BinaryContent.class.getSimpleName(),  id,SystemMessage.RecordNotFound);
    }

}
