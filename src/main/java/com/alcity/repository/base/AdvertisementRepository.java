package com.alcity.repository.base;

import com.alcity.entity.alenum.ADSType;
import com.alcity.entity.base.Advertisement;
import com.alcity.entity.base.ClientType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AdvertisementRepository extends CrudRepository<Advertisement,Long> {
    Optional<Advertisement> findById(Long id);
    Collection<Advertisement> findAll();
    Advertisement findByAdText(String label);
    Collection<Advertisement> findByAdsType(ADSType adsType);

}
