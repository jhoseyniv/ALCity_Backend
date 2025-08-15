package com.alcity.service.base;

import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.base.PLPrivacyDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.ClientTypeRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ClientTypeService  implements ClientTypeRepository {
    @Autowired
    private ClientTypeRepository clientTypeRepository;

    @Override
    public <S extends ClientType> S save(S entity) {
        return clientTypeRepository.save(entity);
    }

    @Override
    public <S extends ClientType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ClientType> findById(Long id) {
        if(id ==null) return Optional.empty();
        return clientTypeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ClientType> findAll() {
        return clientTypeRepository.findAll();
    }

    @Override
    public Iterable<ClientType> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        clientTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(ClientType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ClientType> entities) {

    }

    @Override
    public void deleteAll() {

    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    public ClientType save(ClientTypeDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        ClientType clientType=null;

        if (code.equalsIgnoreCase("Save")) { //Save
            clientType = new ClientType(dto.getLabel(), dto.getValue(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            clientTypeRepository.save(clientType);
        }else{//edit
            Optional<ClientType> clientTypeOptional= clientTypeRepository.findById(dto.getId());
            if(clientTypeOptional.isPresent()) {
                clientType = clientTypeOptional.get();
                clientType.setLabel(dto.getLabel());
                clientType.setValue(dto.getValue());
                clientType.setVersion(clientType.getVersion()+1);
                clientType.setUpdated(DateUtils.getNow());
                clientTypeRepository.save(clientType);
            }
        }
    return clientType;
    }

    @Override
    public ClientType findByLabel(String label) {
        return clientTypeRepository.findByLabel(label);
    }

    @Override
    public ClientType findByValue(String value) {
        return clientTypeRepository.findByValue(value);
    }
}
