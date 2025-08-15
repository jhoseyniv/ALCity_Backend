package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLCellDTO;
import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLCellRepository;
import com.alcity.repository.puzzle.PLTemplateRepository;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PLCellService  implements PLCellRepository {

    @Qualifier("PLCellRepository")
    @Autowired
    private PLCellRepository plCellRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PLGroundService plGroundService;

    public PLCell save(PLCellDTO dto, String code)  {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLCell plCell=null;

        Optional<PLCell> plCellOptional =  plCellRepository.findById(dto.getId());

        Optional<PLGround> plGroundOptional =  plGroundService.findById(dto.getPlGroundId());

        if(plCellOptional.isPresent())
            plCell = plCellOptional.get();
        if (code.equalsIgnoreCase("Save")) { //Save
            plCell = new PLCell(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get(),
                    dto.getRow(),dto.getCol(),dto.getZorder(),plGroundOptional.get());
            plCellRepository.save(plCell);
        }else{//edit
            if(plCellOptional.isPresent()) {
                plCell = plCellOptional.get();
                plCell.setCol(dto.getCol());
                plCell.setzOrder(dto.getZorder());
                plCell.setRow(dto.getRow());
                plCellRepository.save(plCell);
            }
        }
        return plCell;
    }

    @Override
    public <S extends PLCell> S save(S entity) {
        return plCellRepository.save(entity);
    }

    @Override
    public <S extends PLCell> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLCell> findById(Long aLong) {
        if(aLong==null) return Optional.empty();
        return plCellRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PLCell> findAll() {
        return null;
    }

    @Override
    public Iterable<PLCell> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plCellRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLCell entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLCell> entities) {
        plCellRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }

//    @Override
//    public Optional<PLCell> findByPlGroundAndRowAndColAndZOrder(PLGround plGround, Integer row, Integer col, Integer zorder) {
//        return findByPlGroundAndRowAndColAndZOrder(plGround,row,col,zorder);
//    }
}
