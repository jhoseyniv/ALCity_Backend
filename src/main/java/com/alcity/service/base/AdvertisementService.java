package com.alcity.service.base;


import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.entity.alenum.ADSType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.Advertisement;
import com.alcity.repository.base.AdvertisementRepository;
import com.alcity.repository.base.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class AdvertisementService implements AdvertisementRepository {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public <S extends Advertisement> S save(S entity) {
        return advertisementRepository.save(entity);
    }

    @Override
    public <S extends Advertisement> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Advertisement> findById(Long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Advertisement> findAll() {
        return null;
    }

    @Override
    public Iterable<Advertisement> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Advertisement entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Advertisement> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Advertisement findByAdText(String label) {
        return null;
    }

    @Override
    public Collection<Advertisement> findByAdsType(ADSType adsType) {
        Collection<Advertisement> advertisements = advertisementRepository.findByAdsType(adsType);
        return advertisements;
    }
    public Advertisement findTermAndCond() {
        Collection<Advertisement> advertisements = findByAdsType(ADSType.TermCondition);
        Iterator<Advertisement> itr = advertisements.iterator();
        while (itr.hasNext()) {
            Advertisement dto = itr.next();
            if(dto.getAdsType().equals(ADSType.TermCondition))
                return  dto;
            }
        return null;
    }
}
