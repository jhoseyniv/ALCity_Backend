package com.alcity.api;


import com.alcity.customexception.ALCityResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.service.base.WalletItemTypeService;
import com.alcity.service.appmember.WalletItemService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Tag(name = "Wallet APIs", description = "Get Wallet and related entities as rest api")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/wallet")

public class WalletController {
    @Autowired
    private WalletItemTypeService walletItemTypeService;
    @Autowired
    private WalletItemService walletItemService;
    @GetMapping("/type/all")
    @CrossOrigin(origins = "*")
    public Collection<WalletItemTypeDTO> getWalletItemTypes(Model model) {
        Collection<WalletItemType> walletItemTypes = walletItemTypeService.findAll();
        return DTOUtil.getWalletItemTypeDTOS(walletItemTypes);
    }

    @GetMapping("/item/all")
    @CrossOrigin(origins = "*")
    public Collection<WalletItemDTO> getWalletItems(Model model) {
        Collection<WalletItem> walletItems = walletItemService.findAll();
        Collection<WalletItemDTO> dtos = DTOUtil.getWalletItemDTOS(walletItems);
        return dtos;
    }
    @RequestMapping(value = "/type/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Optional<WalletItemType> getWalletItemTypeById(@PathVariable Long id) {
        Optional<WalletItemType> walletItemType = walletItemTypeService.findById(id);
        return walletItemType;
    }

    @RequestMapping(value = "/item/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public WalletItemDTO getWalletItemById(@PathVariable Long id) {
        Optional<WalletItem> walletItemOptional = walletItemService.findById(id);
        if(walletItemOptional.isPresent()) return  DTOUtil.getWalletItemDTO(walletItemOptional.get());
        return null;
    }

    @Operation( summary = "Save a Wallet Item Type  ",  description = "Save a Wallet Item Type  entity and their data to data base")
    @PostMapping("/type/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveOrEditWalletItemType(@RequestBody WalletItemTypeDTO dto)  {
        WalletItemType savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = walletItemTypeService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "Value and Lable Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = walletItemTypeService.save(dto, "Edit");
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

    @Operation( summary = "Save a Wallet Item ",  description = "Save a Wallet Item entity and their data to data base")
    @PostMapping("/item/save")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject saveOrEditWalletItem(@RequestBody WalletItemDTO dto)  {
        WalletItem savedRecord = null;
        ALCityResponseObject responseObject = new ALCityResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = walletItemService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(dto.getValue(), dto.getId(), "Value and Lable Must be Unique");
            }
            responseObject = new ALCityResponseObject(HttpStatus.OK.value(), "ok", savedRecord.getId(), "Record Saved Successfully!");
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = walletItemService.save(dto, "Edit");
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

    @Operation( summary = "delete a  Wallet Item Type  ",  description = "delete a Wallet Item type ")
    @DeleteMapping("/type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteWalletItemTypeById(@PathVariable Long id) {
        Optional<WalletItemType> existingRecord = walletItemTypeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemTypeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getValue(), existingRecord.get().getId(), WalletItemType.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

    @Operation( summary = "delete a  Wallet Item ",  description = "delete a Wallet Item .....")
    @DeleteMapping("/item/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ALCityResponseObject deleteWalletItemById(@PathVariable Long id) {
        Optional<WalletItem> existingRecord = walletItemService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(existingRecord.get().getValue(), existingRecord.get().getId(), WalletItemType.class.toString());
            }
            return new ALCityResponseObject(HttpStatus.OK.value(), "ok", id,"Record deleted Successfully!");
        }
        return  new ALCityResponseObject(HttpStatus.NO_CONTENT.value(), "error", id,"Record not found!");
    }

}
