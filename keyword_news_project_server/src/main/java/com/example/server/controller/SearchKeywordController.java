package com.example.server.controller;

import com.example.server.domain.searchkeyword.SearchKeyword;
import com.example.server.domain.searchkeyword.dto.*;
import com.example.server.service.SearchKeywordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
public class SearchKeywordController {
    private final SearchKeywordService searchKeywordService;

    @PostMapping("/addKeyword")
    public ResponseEntity<?> addKeyword(@RequestBody @Valid AddKeywordDTO dto){
        searchKeywordService.addKeyword(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/removeKeyword")
    public ResponseEntity<?> removeKeyword(@RequestBody @Valid FindKeywordDTO dto){
        searchKeywordService.removeKeyword(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/findAllKeyword/{ownerId}")
    public List<SearchKeyword> findAllKeyword(@PathVariable Map<String,String> data){
        return searchKeywordService.findAllKeyword(data.get("ownerId"));
    }
    @PostMapping("/findKeywordByInfo")
    public Optional<SearchKeyword> findKeywordByInfo(@RequestBody @Valid FindKeywordDTO dto){
        return searchKeywordService.findKeywordByInfo(dto);
    }
    @PostMapping("/addContainKeyword")
    public ResponseEntity<?> addContainKeyword(@RequestBody @Valid AddContainKeywordDTO dto){
        searchKeywordService.addContainKeyword(dto.toFindDTO(), dto.getAddContainKeyword());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/removeContainKeyword")
    public ResponseEntity<?> removeContainKeyword(@RequestBody @Valid RemoveContainKeywordDTO dto){
        searchKeywordService.removeContainKeyword(dto.toFindDTO(), dto.getRemoveContainKeyword());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/findAllContainKeyword")
    public List<String> findAllContainKeyword(@RequestBody @Valid FindKeywordDTO dto){
        return searchKeywordService.findAllContainKeyword(dto);
    }
    @PostMapping("/addExcludeKeyword")
    public ResponseEntity<?> addExcludeKeyword(@RequestBody @Valid AddExcludeKeywordDTO dto){
        searchKeywordService.addExcludeKeyword(dto.toFindDTO(), dto.getAddExcludeKeyword());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/removeExcludeKeyword")
    public ResponseEntity<?> removeExcludeKeyword(@RequestBody @Valid RemoveExcludeKeywordDTO dto){
        searchKeywordService.removeExcludeKeyword(dto.toFindDTO(), dto.getRemoveExcludeKeyword());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/findAllExcludeKeyword")
    public List<String> findAllExcludeKeyword(@RequestBody @Valid FindKeywordDTO dto){
        return searchKeywordService.findAllExcludeKeyword(dto);
    }


}
