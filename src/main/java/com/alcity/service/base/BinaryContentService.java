package com.alcity.service.base;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.base.BinaryContentCustom;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.repository.users.CustomizedUserRepository;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class BinaryContentService implements BinaryContentRepository , BinaryContentCustom {

    @Autowired
    BinaryContentRepository binaryContentRepository;
    @Override
    public <S extends BinaryContent> S save(S entity) {
        return binaryContentRepository.save(entity);
    }

    @Override
    public <S extends BinaryContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BinaryContent> findById(Long id) {
        return binaryContentRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<BinaryContent> findAll() {
        return null;
    }

    @Override
    public Iterable<BinaryContent> findAllById(Iterable<Long> longs) {
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
    public void delete(BinaryContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BinaryContent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public BinaryContent findByfileName(String fileName) {
        return binaryContentRepository.findByfileName(fileName);
    }

    @Override
    public Collection<BinaryContent> findBySize(Integer size) {
        return null;
    }

    @Autowired
    private ApplicationMemberRepository applicationMemberRepository;

    @Override
    public BinaryContent save(String fileName, MultipartFile file) throws IOException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);
        ApplicationMember createdBy = applicationMemberRepository.findByUsername("admin");
        BinaryContentType binaryContentType = ImageUtil.getBinaryContentType(file.getContentType());
        BinaryContent binaryContent = new BinaryContent(fileName,file.getBytes(), binaryContentType,1L,now,now,createdBy,createdBy);
        binaryContentRepository.save(binaryContent);

        return binaryContent;
    }
}
