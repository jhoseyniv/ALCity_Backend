package com.alcity.service.puzzle;

import com.alcity.dto.pgimport.PGLearningSkillContentImportDTO;
import com.alcity.dto.pgimport.PGObjectImportDTO;
import com.alcity.dto.puzzle.CityObjectInPGDTO;
import com.alcity.dto.puzzle.PGDTO;
import com.alcity.dto.puzzle.PGLearningSkillContentDTO;
import com.alcity.dto.pgimport.PGImportDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.PuzzleCategoryRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.alobject.AttributeService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class PGService implements PGRepository {

    @Qualifier("PGRepository")
    @Autowired
    PGRepository pgRepository;

    @Autowired
    BinaryContentRepository binaryContentRepository;

    @Autowired
    PGSkillLearningContentService pgSkillLearningContentService;

    @Override
    public <S extends PuzzleGroup> S save(S entity) {
        return pgRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroup> findById(Long id) {
        return pgRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroup> findAll() {
        return pgRepository.findAll();
    }

    @Override
    public Iterable<PuzzleGroup> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    public void deleteById(PuzzleGroup puzzleGroup) {
        binaryContentRepository.delete(puzzleGroup.getIcon());
        binaryContentRepository.delete(puzzleGroup.getPic());
        pgRepository.delete(puzzleGroup);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private PuzzleCategoryRepository puzzleCategoryRepository;

    @Autowired
    private ObjectInPGService objectInPGService;

    @Autowired
    private AttributeService attributeService;

    public PuzzleGroup importPG(PGImportDTO dto) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PuzzleGroup puzzleGroup=null;
        Optional<BinaryContent> iconOptional = binaryContentRepository.findById(dto.getIconInfo());
        Optional<BinaryContent> picOptional = binaryContentRepository.findById(dto.getPicInfo());
        Optional<PuzzleCategory>  puzzleCategoryOptional = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        if(puzzleCategoryOptional.isEmpty()) return null;
        puzzleGroup = new PuzzleGroup(dto.getTitle(),puzzleCategoryOptional.get(),iconOptional.get(),picOptional.get(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        pgRepository.save(puzzleGroup);
        Collection<PGObjectImportDTO> cityObjectInPGS = dto.getObjects();

        Iterator<PGObjectImportDTO> objectIterator = cityObjectInPGS.iterator();
        while(objectIterator.hasNext()) {
            PGObjectImportDTO objectImportDTO = objectIterator.next();
            objectInPGService.importObjInPG(objectImportDTO,puzzleGroup);
        }

        Collection<PGLearningSkillContentDTO> learningSkills = dto.getSkills();
        Iterator<PGLearningSkillContentDTO> iterator = learningSkills.iterator();
        while(iterator.hasNext()){
            PGLearningSkillContentDTO learningSkillDTO=iterator.next();
            pgSkillLearningContentService.save(learningSkillDTO,"Save");
        }
        return puzzleGroup;
    }
    public PuzzleGroup save(PGDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PuzzleGroup puzzleGroup=null;
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> pic = binaryContentRepository.findById(dto.getPicId());
        Optional<PuzzleCategory>  puzzleCategory = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        if (code.equalsIgnoreCase("Save")) { // save
            puzzleGroup = new PuzzleGroup(dto.getTitle(),puzzleCategory.get(),icon.get(),pic.get(), 1L, "1714379790", "1714379790", createdBy.get(), createdBy.get());
            pgRepository.save(puzzleGroup);
        }else{//edit
            Optional<PuzzleGroup> puzzleGroupOptional= pgRepository.findById(dto.getId());
            if(puzzleGroupOptional.isPresent()) {
                puzzleGroup = puzzleGroupOptional.get();
                puzzleGroup.setTitle(dto.getTitle());
                puzzleGroup.setIcon(icon.get());
                puzzleGroup.setPic(pic.get());
                puzzleGroup.setPuzzleCategory(puzzleCategory.get());
                puzzleGroup.setVersion(puzzleGroup.getVersion()+1);
                pgRepository.save(puzzleGroup);
            }
        }
        return puzzleGroup;
    }

    @Override
    public void delete(PuzzleGroup entity) {
       // binaryContentRepository.delete(entity.getIcon());
      //  binaryContentRepository.delete(entity.getPic());
        pgRepository.delete(entity);

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleGroup> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public  Optional<PuzzleGroup> findByTitle(String title) {
        return pgRepository.findByTitle(title);
    }

    @Override
    public Optional<PuzzleGroup> findByIcon(BinaryContent icon) {
        return pgRepository.findByIcon(icon);
    }

    @Override
    public Optional<PuzzleGroup> findByPic(BinaryContent pic) {
        return pgRepository.findByPic(pic);
    }


}
