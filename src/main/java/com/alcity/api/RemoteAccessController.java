package com.alcity.api;


import com.alcity.dto.RemoteAccess.RemoteAccessDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.dto.search.ObjectSearchCriteriaDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.customexception.ALCityAcessRight;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.PLDTOUtil;
import io.github.classgraph.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
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


    @Operation( summary = "login  to system by remote application ",  description = "login to system by remote application")
    @PostMapping("/get/user")
    @CrossOrigin(origins = "*")
    public ALCityAcessRight getPuzzleLevel(@RequestBody RemoteAccessDTO accessDTO) {
        Optional<AppMember> memberOptional = memberService.findByUsername(accessDTO.getRemoteUserName());
        AppMember member=null;
        if(memberOptional.isEmpty())
            member = memberService.saveRemoteUser(accessDTO);
        else
            member = memberOptional.get();

        ALCityAcessRight accessRight = new ALCityAcessRight(member.getId(), member.getUsername(),0,"Login Successfull","JWT Token", member.getAge(), member.getNickname(), member.getMobile(),
                member.getEmail(), member.getIcon().getId(), member.getMemberType().getValue(), member.getGender().name());

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
