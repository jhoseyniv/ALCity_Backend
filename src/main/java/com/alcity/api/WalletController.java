package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.appmember.WalletItemChangeRateDTO;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItemChangeRate;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.service.appmember.WalletItemChangeRateService;
import com.alcity.service.base.WalletItemTypeService;
import com.alcity.service.appmember.WalletItemService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WalletItemChangeRateService walletItemChangeRateService;

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
        if (walletItemOptional.isPresent()) return DTOUtil.getWalletItemDTO(walletItemOptional.get());
        return null;
    }

    @RequestMapping(value = "/item/base-currency", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public WalletItemDTO getBaseWalletItemById() {
        Optional<WalletItem> walletItemOptional = walletItemService.findByBaseCurrency(true);
        if (walletItemOptional.isPresent()) return DTOUtil.getWalletItemDTO(walletItemOptional.get());
        return null;
    }

    @Operation(summary = "Save a Wallet Item Type  ", description = "Save a Wallet Item Type  entity and their data to data base")
    @PostMapping("/type/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveOrEditWalletItemType(@RequestBody WalletItemTypeDTO dto) {
        WalletItemType savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<WalletItemType> walletItemTypeOptional = walletItemTypeService.findById(dto.getId());
          try {
                if(walletItemTypeOptional.isEmpty())
                    savedRecord = walletItemTypeService.save(dto, "Save");
                else
                    savedRecord = walletItemTypeService.save(dto, "Edit");
                if(savedRecord !=null)
                    response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),WalletItemType.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
                else
                    response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), WalletItemType.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
            } catch (RuntimeException e) {
                throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , WalletItemType.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
            }

        return response;
    }

    @Operation(summary = "Save a Wallet Item ", description = "Save a Wallet Item entity and their data to data base")
    @PostMapping("/item/save")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveOrEditWalletItem(@RequestBody WalletItemDTO dto) {
        WalletItem savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<WalletItem> walletItemOptional = walletItemService.findById(dto.getId());
        Optional<WalletItem> baseWalletItemOptional = walletItemService.findByBaseCurrency(true);
        if (baseWalletItemOptional.isPresent())
            return  new ResponseMessage(ErrorType.UniquenessViolation, Status.ok.name(),WalletItem.class.getSimpleName() ,  -1L, SystemMessage.baseCurrencyIsExist);
        try {
            if (walletItemOptional.isEmpty())
                savedRecord = walletItemService.save(dto, "Save");
            else
                savedRecord = walletItemService.save(dto, "Edit");

            if(savedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),WalletItem.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), WalletItem.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        }
        catch (Exception e) {
             throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , WalletItem.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        return response;
    }

    @Operation(summary = "Save a Wallet Item Change Rate ", description = "Save a Wallet Item Change Rate entity and their data to data base")
    @PostMapping("/item/save/change-rate")
    @CrossOrigin(origins = "*")
    public ResponseMessage saveOrEditWalletItemChangeRate(@RequestBody WalletItemChangeRateDTO dto) {
        WalletItemChangeRate savedRecord = null;
        ResponseMessage response = new ResponseMessage();
        Optional<WalletItemChangeRate> walletItemChangeRateOptional = walletItemChangeRateService.findById(dto.getId());
        try {
            if (walletItemChangeRateOptional.isEmpty())
                savedRecord = walletItemChangeRateService.save(dto, "Save");
            else
                savedRecord = walletItemChangeRateService.save(dto, "Edit");
            if(savedRecord !=null)
                response = new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),WalletItemChangeRate.class.getSimpleName() ,  savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                response = new ResponseMessage(ErrorType.SaveFail,Status.error.name(), WalletItemChangeRate.class.getSimpleName() ,  -1L, SystemMessage.SaveOrEditMessage_Fail);
        }
        catch (Exception e) {
            throw new ResponseObject(ErrorType.UniquenessViolation,Status.error.name() , WalletItemChangeRate.class.getSimpleName() ,  -1L ,e.getCause().getMessage());
        }
        return response;
    }

    @Operation( summary = "Delete a  Wallet Item Type  ",  description = "delete a Wallet Item type ")
    @DeleteMapping("/type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteWalletItemTypeById(@PathVariable Long id) {
        Optional<WalletItemType> existingRecord = walletItemTypeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemTypeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), WalletItemType.class.toString(),existingRecord.get().getId(),e.getClass().getName());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess, Status.ok.name(),WalletItemType.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound,Status.error.name(), WalletItemType.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }

    @Operation( summary = "delete a  Wallet Item ",  description = "delete a Wallet Item .....")
    @DeleteMapping("/item/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteWalletItemById(@PathVariable Long id) {
        Optional<WalletItem> existingRecord = walletItemService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), WalletItem.class.toString(),existingRecord.get().getId(),e.getClass().getName());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess,Status.ok.name(), ObjectAction.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(),ObjectAction.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }

    @Operation( summary = "Delete a  Wallet Item Change Rate by id",  description = "Delete a  Wallet Item Change Rate by id")
    @DeleteMapping("/item/del/change-rate/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseMessage deleteWalletItemChangeRateById(@PathVariable Long id) {
        Optional<WalletItemChangeRate> existingRecord = walletItemChangeRateService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemChangeRateService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ResponseObject(ErrorType.ForeignKeyViolation, Status.error.name(), WalletItemChangeRate.class.toString(),existingRecord.get().getId(),e.getClass().getName());
            }
            return new ResponseMessage(ErrorType.DeleteSuccess,Status.ok.name(), WalletItemChangeRate.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseMessage(ErrorType.RecordNotFound, Status.error.name(),WalletItemChangeRate.class.getSimpleName(),  existingRecord.get().getId(),SystemMessage.RecordNotFound);
    }

}
