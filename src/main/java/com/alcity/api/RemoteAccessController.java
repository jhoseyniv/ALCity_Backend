package com.alcity.api;


import com.alcity.dto.RemoteAccess.RemoteRequestDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.o3rdparty.O3rdPartyResponse;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.o3rdparty.ALCityAcessRight;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Tag(name = "Remote Access To Some  API's ", description = "Remote Access to API's...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/3rd-party")
public class RemoteAccessController extends BaseTable implements Serializable {

    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private AppMemberService memberService;
    @Autowired
    private BinaryContentService binaryContentService;

    public String getReferenceId(RemoteRequestDTO request,AppMember member ){
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime expireTime = current.plusMinutes(30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String expire = expireTime.format(format);
        String refrenceId = request.getRemoteHost() + "," + member.getUsername() + "," + expire ;
        return refrenceId;
    }
    public Long getPuzzleLevelId(String puzzleCode){
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findByCode(puzzleCode);
        if(puzzleLevelOptional.isPresent()) return puzzleLevelOptional.get().getId();
        else  return 0L;
    }

    @Operation( summary = "request a puzzle level from algoopia by a 3rd party application  ",  description = "request a puzzle level from algoopia by a 3rd party application ")
    @PostMapping("/request/puzzle")
    @CrossOrigin(origins = "*")
    public O3rdPartyResponse requestPuzzleLevel(@RequestBody RemoteRequestDTO request) {
        Optional<AppMember> memberOptional = memberService.findByUsername(request.getRemoteUserName());
        AppMember member=null;
        if(memberOptional.isEmpty())
            member = memberService.saveRemoteUser(request);
        else
            member = memberOptional.get();
        Long puzzleLevelId = getPuzzleLevelId(request.getPuzzleCode());
        String refId = getReferenceId(request,member);
        Long status =0L;
        if(puzzleLevelId > 0L)   status = 1L;
        O3rdPartyResponse response = new O3rdPartyResponse(refId, puzzleLevelId,status);
        return response;
    }
    public Boolean isReferenceIdOK(String refId){
        return true;
    }
    public String getRemoteUserName(String refId){
        String[] tokens = refId.split(",");
        return tokens[1];
    }


    @Operation( summary = "login  to system by remote application ", description = "login to system by remote application")
    @RequestMapping(value = "/get/refId/{refId}/plId/{plId}/status/{status}" , method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ALCityAcessRight loginAndGetPuzzleLevel(@PathVariable String refId,@PathVariable Long plId,@PathVariable Long status) {
        ALCityAcessRight accessRight =null;
        if(isReferenceIdOK(refId)) {
            String userName = getRemoteUserName(refId);
            Optional<AppMember> memberOptional = memberService.findByUsername(userName);
            AppMember member = memberOptional.get();
            accessRight = new ALCityAcessRight(member.getId(), member.getUsername(),0,"Login Successfull","JWT Token", member.getAge(),
                    member.getNickname(), member.getMobile(),
                    member.getEmail(), member.getIcon().getId(), member.getMemberType().getValue(), member.getGender().name());
        }else {
            accessRight = new ALCityAcessRight(-1L, null,-1,"Login Fail",null, -1,
                    null, null,null, null, null, null);

        }
          return accessRight;
    }


    @Operation( summary = "get download page application file  ",  description = "get download application file")
    @GetMapping("/get/download-page")
    @CrossOrigin(origins = "*")
    public String getDownloadPAge() throws IOException {
        BinaryContent application =  binaryContentService.findByfileName("application");
        String url="http://localhost:8080/3rd-party/get/app";
        String urlServer="https://backend.algoopia.com/3rd-party/get/app";
        String link = "<a href="+url +  ">algoopia application!" +"</a>";

        return "You must download algoopia:" + link;

    }
    @Operation( summary = "get download page application file  ",  description = "get download application file")
    @GetMapping("/get/app")
    @CrossOrigin(origins = "*")
    public ResponseEntity getApplicationFile() throws IOException {
        File file = new File("src/main/resources/images/goose.png");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

}
