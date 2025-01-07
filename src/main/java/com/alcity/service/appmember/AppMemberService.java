package com.alcity.service.appmember;

import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.appmember.AppMemberWalletDTO;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.MemberType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import com.alcity.repository.appmember.CustomizedUserRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.MemberTypeRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppMemberService implements AppMemberRepository, CustomizedUserRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private BinaryContentRepository binaryContentRepository;
    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Autowired
    private AppMember_WalletItemRepository appMember_WalletItemRepository;

    @Autowired
    private WalletItemRespository walletItemRespository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    public AppMember_WalletItem chargeOrDeChargeAppMemberWallet(AppMemberWalletDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        Optional<WalletItem> walletItemOptional = walletItemRespository.findById(dto.getWalletItemId());
        Optional<AppMember> appMemberOptional = appMemberRepository.findById(dto.getAppMemberId());
        WalletItem walletItem =null;
        AppMember appMember =null;

        if(walletItemOptional.isPresent())
            walletItem = walletItemOptional.get();

        if(appMemberOptional.isPresent())
            appMember = appMemberOptional.get();


        AppMember_WalletItem appMember_walletItem=null;
        Optional<AppMember_WalletItem> isWalletItemPresent = appMember_WalletItemRepository.findByApplicationMemberAndWalletItem(appMember,walletItem);
        if (code.equalsIgnoreCase("Save") && !isWalletItemPresent.isPresent()) { //Save
            appMember_walletItem = new AppMember_WalletItem(appMember,walletItem, dto.getAmount()
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            appMember_WalletItemRepository.save(appMember_walletItem);
        } else if(code.equalsIgnoreCase("Save") && isWalletItemPresent.isPresent()){
             appMember_walletItem = isWalletItemPresent.get();
            appMember_walletItem.setAmount(appMember_walletItem.getAmount() + dto.getAmount());
            appMember_WalletItemRepository.save(appMember_walletItem);
        }
        else{//edit
            Optional<AppMember_WalletItem> appMember_walletItemOptional= appMember_WalletItemRepository.findById(dto.getId());
            if(appMember_walletItemOptional.isPresent()) {
                appMember_walletItem = appMember_walletItemOptional.get();
                appMember_walletItem.setApplicationMember(appMember);
                appMember_walletItem.setWalletItem(walletItem);
                appMember_walletItem.setAmount(dto.getAmount());
                appMember_walletItem.setVersion(appMember.getVersion()+1);
                appMember_walletItem.setCreated(DateUtils.getNow());
                appMember_walletItem.setUpdated(DateUtils.getNow());
                appMember_walletItem.setCreatedBy(createdBy);
                appMember_walletItem.setUpdatedBy(createdBy);
                appMember_WalletItemRepository.save(appMember_walletItem);
            }
        }
        return appMember_walletItem;

    }
    public AppMember save(AppMemberDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeRepository.findByValue(dto.getMemberType()).get();
        UserGender gender = UserGender.getByTitle(dto.getGender());
        BinaryContent icon=null;
        if(dto.getIconId() == null || dto.getIconId() ==0)
                icon = binaryContentRepository.findByfileName("no_photo_avatar");
        else
            icon = binaryContentRepository.findById(dto.getIconId()).get();

        AppMember appMember=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            appMember = new AppMember(dto.getAge(),dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getMobile(),dto.getEmail(),icon,gender ,memberType
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
            appMemberRepository.save(appMember);
        }else{//edit
            Optional<AppMember> appMemberOptional= appMemberRepository.findById(dto.getId());
            if(appMemberOptional.isPresent()) {
                appMember = appMemberOptional.get();
                appMember.setNickname(dto.getNickname());
                appMember.setUsername(dto.getUsername());
                appMember.setPassword(dto.getPassword());
                appMember.setMobile(dto.getMobile());
                appMember.setIcon(icon);
                appMember.setMemberType(memberType);
                appMember.setGender(gender);
                appMember.setAge(appMember.getAge());
                appMember.setVersion(appMember.getVersion()+1);
                appMember.setCreated(DateUtils.getNow());
                appMember.setUpdated(DateUtils.getNow());
                appMember.setCreatedBy(createdBy);
                appMember.setUpdatedBy(createdBy);
                appMemberRepository.save(appMember);
            }
        }
        return appMember;
    }
    public AppMember saveGuestUser() {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeRepository.findByValue("Guest").get();
        BinaryContent icon=null;
        AppMember guest=null;
            icon = binaryContentRepository.findByfileName("no_photo_avatar");
        guest = new AppMember(0,"Guest", "Guest", "Guest", "","",icon,UserGender.Unknow ,memberType
                ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy, createdBy);
        appMemberRepository.save(guest);
        String UniqueUserName= guest.getUsername() + guest.getId();
        guest.setUsername(UniqueUserName);
        appMemberRepository.save(guest);
        return guest;
    }
    @Override
    public <S extends AppMember> S save(S entity) {
        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return appMemberRepository.save(entity);
    }

    @Override
    public <S extends AppMember> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember> findById(Long id) {
        return appMemberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AppMember> findAll() {
        return appMemberRepository.findAll();
    }

    @Override
    public Iterable<AppMember> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        appMemberRepository.deleteById(aLong);
    }

    @Override
    public void delete(AppMember entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public AppMember findByUsername(String username) {
        return appMemberRepository.findByUsername(username);
    }

    @Override
    public Collection<AppMember> findByMobile(String mobile) {
        return null;
    }

    @Override
    public AppMember findByEmail(String email) {
        return null;
    }

    public ALCityResponseObject login(String username, String password) {
        return null;
    }
}
