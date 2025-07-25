package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLGroundDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.PLGroundRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ImageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLGroundService implements PLGroundRepository {

    @Autowired
    @Qualifier("PLGroundRepository")
    PLGroundRepository groundRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private PLCellService plCellService;

    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Autowired
    BinaryContentRepository binaryContentRepository;

    @Override
    public <S extends PLGround> S save(S entity) {
        return groundRepository.save(entity);
    }

    public Collection<PLCell> savePLCells(Integer row,Integer col, Integer zorder,PLGround plGround ){
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        Collection<PLCell> cells = new ArrayList<>();
        for(Integer i = 1; i<=row; i++)
            for (Integer j = 1; j <= row; j++)
                for (Integer k = 1; k <= zorder; k++) {
                    PLCell cell = new PLCell(1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get(),
                            i, j, k, plGround);
                    plCellService.save(cell);
                    cells.add(cell);
                }

        return  cells;
    }

    public PLGround save(PLGroundDTO  dto, String code)  {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLGround plGround=null;
        PuzzleLevel puzzleLevel=null;
        byte[] boardGraphic = ImageUtil.convertObjectToBytes(dto.getBoardGraphicDTO());

       // JSONObject objJsonObject = new JSONObject(dto.getBoardGraphic());
       // byte[] boardGraphic = objJsonObject.toString().getBytes();
        //pl.setInterpreterFile(bytes);

        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelRepository.findById(dto.getPuzzleLevelId());
        if(puzzleLevelOptional.isPresent())
            puzzleLevel = puzzleLevelOptional.get();
        if (code.equalsIgnoreCase("Save")) { //Save
            plGround = new PLGround(dto.getNumRows(),dto.getNumColumns(),dto.getXposition(),dto.getYposition(),dto.getZposition(),
                      dto.getXrotation(),dto.getYrotation(),dto.getZrotation(),dto.getZoom(),dto.getPan(),dto.getRotation(),
                    puzzleLevel,boardGraphic
                                 , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            groundRepository.save(plGround);




        }else{//edit
            Optional<PLGround> plGroundOptional =  groundRepository.findById(dto.getId());
            if(plGroundOptional.isPresent()) {
                plGround = plGroundOptional.get();
                plGround.setBoardGraphic(boardGraphic);
                plGround.setNumColumns(dto.getNumColumns());
                plGround.setNumRows(dto.getNumRows());
                plGround.setxPosition(dto.getXposition());
                plGround.setyPosition(dto.getYposition());
                plGround.setzPosition(dto.getZposition());
                plGround.setxRotation(dto.getXrotation());
                plGround.setyRotation(dto.getYrotation());
                plGround.setzRotation(dto.getZrotation());
                plGround.setPan(dto.getPan());
                plGround.setZoom(dto.getZoom());
                plGround.setRotation(dto.getRotation());
                plGround.setPuzzleLevel(puzzleLevel);
                plGround.setVersion(plGround.getVersion()+1);
                plGround.setUpdated(DateUtils.getNow());
                plGround.setBoardGraphic(boardGraphic);
                groundRepository.save(plGround);
            }
        }
        return plGround;
    }
    @Override
    public <S extends PLGround> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLGround> findById(Long id) {
        return groundRepository.findById(id);
    }

    @Override
    public Optional<PLGround> findByPuzzleLevelId(Long id) {
        return groundRepository.findByPuzzleLevelId(id);
    }

     @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLGround> findAll() {
        return null;
    }

    @Override
    public Iterable<PLGround> findAllById(Iterable<Long> longs) {
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
    public void delete(PLGround entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLGround> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLGround> findByNumColumns(Integer cols) {
        return null;
    }

    @Override
    public Collection<PLGround> findByNumRows(Integer rows) {
        return null;
    }
}
