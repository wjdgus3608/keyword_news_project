package com.example.server.controller;

import com.example.server.domain.searchkeyword.SearchKeyword;
import com.example.server.service.SearchKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SearchKeywordController {
    private final SearchKeywordService searchKeywordService;

    public void addKeyword(){

    }

    public void removeKeyword(){

    }

    public List<SearchKeyword> findAllKeyword(){

    }

    public Optional<SearchKeyword> findKeywordByInfo(){

    }

    public void addContainKeyword(){

    }

    public void removeContainKeyword(){

    }

    public List<String> findAllContainKeyword(){

    }

    public void addExcludeKeyword(){

    }

    public void removeExcludeKeyword(){

    }

    public List<String> findAllExcludeKeyword(){

    }


}
