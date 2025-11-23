package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.alcity.entity.alenum.DeviceType;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    @Cacheable(value = "getBinaryContentById", key = "#id")
    public BinaryContentDTO getBinaryContentById(@PathVariable Long id) {
        Optional<BinaryContent> binaryContentOptional = binaryContentService.findById(id);
        if(binaryContentOptional.isPresent()) {
            BinaryContent bc = binaryContentOptional.get();
            BinaryContentDTO binaryContentDTO = new BinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(), bc.getContent(), null,
                    bc.getIos3Dcontent(),bc.getAndriod3Dcontent(), bc.getContent(), bc.getContentType().name(),bc.getIs3dContent(),bc.getTag1(),bc.getTag2(),bc.getTag3());
            return binaryContentDTO;
        }
        return null;
    }
    @RequestMapping(value="/id/{id}/device-type/{deviceType}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    @Transactional(readOnly = true)
//    @Cacheable(value = "getBinaryContentByIdAndDevice", key = "{ #id, #deviceType }")
    public BinaryContentDTO getBinaryContentByIdAndDevice(@PathVariable Long id,@PathVariable String deviceType) {
        Optional<BinaryContent> binaryContentOptional = binaryContentService.findById(id);
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO();
        if(binaryContentOptional.isPresent()) {
            BinaryContent bc = binaryContentOptional.get();
            if (deviceType.equalsIgnoreCase(DeviceType.IOS.name())) {
                return  new BinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(),null,null,
                        bc.getIos3Dcontent(),null,null,bc.getContentType().name(),bc.getIs3dContent(),bc.getTag1(),bc.getTag2(),bc.getTag3());
            }else if(deviceType.equalsIgnoreCase(DeviceType.Android.name())) {
                return  new BinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(),null,null,null,bc.getAndriod3Dcontent(),null,bc.getContentType().name(),bc.getIs3dContent(),bc.getTag1(),bc.getTag2(),bc.getTag3());
            }else if(deviceType.equalsIgnoreCase(DeviceType.Web.name())) {
                return  new BinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(),null,null,null,null,bc.getWeb3Dcontent(),bc.getContentType().name(),bc.getIs3dContent(),bc.getTag1(),bc.getTag2(),bc.getTag3());
            }else {
                return  new BinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(),bc.getContent(),null,null,null,null,bc.getContentType().name(),bc.getIs3dContent(),bc.getTag1(),bc.getTag2(),bc.getTag3());

            }
        }
        return null;
    }

    @GetMapping("/get-file/id/{id}/device-type/{deviceType}")
    @CrossOrigin(origins = "*")
    @Transactional(readOnly = true)
//    @Cacheable(value = "getFileByDeviceType", key = "{ #id, #deviceType }")
    public ResponseEntity<byte[]> getFileByDeviceType(@PathVariable Long id , @PathVariable String deviceType) {
       Optional<BinaryContent>  binaryContentOptional= binaryContentService.findById(id);

        if(binaryContentOptional.isPresent()) {
            BinaryContent bc = binaryContentOptional.get();
            if (deviceType.equalsIgnoreCase(DeviceType.IOS.name())) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bc.getFileName() + "\"")
                        .body(bc.getIos3Dcontent());
             }else if(deviceType.equalsIgnoreCase(DeviceType.Android.name())) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bc.getFileName() + "\"")
                        .body(bc.getAndriod3Dcontent());
            }else if(deviceType.equalsIgnoreCase(DeviceType.Web.name())) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bc.getFileName() + "\"")
                        .body(bc.getWeb3Dcontent());
            }else {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bc.getFileName() + "\"")
                        .body(bc.getContent());
            }
        }
           return  null;
    }

    @GetMapping("/get-file/{id}")
    @CrossOrigin(origins = "*")
    @Transactional(readOnly = true)
//    @Cacheable(value = "getFileContent", key = "#id")
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
 //   @Cacheable(value = "getBinaryContent", key = "#criteriaDTO")
    public Collection<BinaryContentDTO> getBinaryContentBySearchCriteria(@RequestBody ContentSearchCriteriaDTO criteriaDTO ) {
        Collection<BinaryContent> binaryContentCollection = binaryContentService.findByCriteria(criteriaDTO);
        return DTOUtil.getBinaryContentsWithoutContent(binaryContentCollection);
    }

    @Operation( summary = "Save a Binary Content to database by DTO ",  description = "Save a Binary Content entity and their data to data base")
    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    @Transactional
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
