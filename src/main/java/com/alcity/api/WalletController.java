package com.alcity.api;


import com.alcity.customexception.ResponseMessage;
import com.alcity.customexception.ResponseObject;
import com.alcity.customexception.UniqueConstraintException;
import com.alcity.customexception.ViolateForeignKeyException;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.dto.appmember.WalletItemDTO;
import com.alcity.entity.alenum.Status;
import com.alcity.entity.alenum.ErrorType;
import com.alcity.entity.alenum.SystemMessage;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.WalletItemType;
import com.alcity.entity.appmember.WalletItem;
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
    public ResponseObject saveOrEditWalletItemType(@RequestBody WalletItemTypeDTO dto)  {
        WalletItemType savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = walletItemTypeService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + WalletItemType.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = walletItemTypeService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }

    @Operation( summary = "Save a Wallet Item ",  description = "Save a Wallet Item entity and their data to data base")
    @PostMapping("/item/save")
    @CrossOrigin(origins = "*")
    public ResponseObject saveOrEditWalletItem(@RequestBody WalletItemDTO dto)  {
        WalletItem savedRecord = null;
        ResponseObject responseObject = new ResponseObject();

        if (dto.getId() == null || dto.getId() <= 0L) { //save
            try {
                savedRecord = walletItemService.save(dto,"Save");
            } catch (RuntimeException e) {
                throw new UniqueConstraintException(-1,"Unique Constraint in" + WalletItem.class , "Error",savedRecord.getId() );
            }
            responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
        } else if (dto.getId() > 0L ) {//edit
            savedRecord = walletItemService.save(dto, "Edit");
            if(savedRecord !=null)
                responseObject = new ResponseObject(ErrorType.SaveSuccess, WalletItemType.class.getSimpleName() , Status.ok.name(), savedRecord.getId(), SystemMessage.SaveOrEditMessage_Success);
            else
                responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        }
        else if (savedRecord==null)
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);
        else
            responseObject = new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), dto.getId(),SystemMessage.RecordNotFound);

        return responseObject;
    }

    @Operation( summary = "delete a  Wallet Item Type  ",  description = "delete a Wallet Item type ")
    @DeleteMapping("/type/del/id/{id}")
    @CrossOrigin(origins = "*")
    public ResponseObject deleteWalletItemTypeById(@PathVariable Long id) {
        Optional<WalletItemType> existingRecord = walletItemTypeService.findById(id);
        if(existingRecord.isPresent()){
            try {
                walletItemTypeService.deleteById(existingRecord.get().getId());
            }catch (Exception e )
            {
                throw new ViolateForeignKeyException(-1, "error", WalletItemType.class.toString(),existingRecord.get().getId());
            }
            return new ResponseObject(ErrorType.DeleteSuccess, ObjectAction.class.getSimpleName(), Status.ok.name(), existingRecord.get().getId(),SystemMessage.DeleteMessage);
        }
        return new ResponseObject(ErrorType.RecordNotFound, ObjectAction.class.getSimpleName(), Status.error.name(), existingRecord.get().getId(),SystemMessage.RecordNotFound);
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

}
