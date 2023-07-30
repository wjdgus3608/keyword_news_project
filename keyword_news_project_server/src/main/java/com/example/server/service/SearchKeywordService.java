package com.example.server.service;

import com.example.server.domain.searchkeyword.SearchKeyword;
import com.example.server.domain.searchkeyword.dto.AddKeywordDTO;
import com.example.server.domain.searchkeyword.dto.FindKeywordDTO;
import com.example.server.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchKeywordService {
    private final SearchKeywordRepository searchKeywordRepository;

    public void addKeyword(AddKeywordDTO dto){
        searchKeywordRepository.save(dto.toEntity());
    }

    public void removeKeyword(FindKeywordDTO dto){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        optional.ifPresent(searchKeywordRepository::delete);
    }

    public List<SearchKeyword> findAllKeyword(String ownerId){
        return searchKeywordRepository.findByOwnerId(ownerId);
    }

    public Optional<SearchKeyword> findKeywordByInfo(FindKeywordDTO dto){
        return searchKeywordRepository.findByKeywordAndOwnerId(dto.getKeyword(), dto.getOwnerId());
    }

    public void addContainKeyword(FindKeywordDTO dto,String keyword){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        optional.ifPresent(searchKeyword -> searchKeyword.addContainKeyword(keyword));
    }

    public void removeContainKeyword(FindKeywordDTO dto,String keyword){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        optional.ifPresent(searchKeyword -> searchKeyword.removeContainKeyword(keyword));
    }

    public List<String> findAllContainKeyword(FindKeywordDTO dto){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        if(optional.isPresent())
            return optional.get().getContainKeywords();
        return Collections.emptyList();
    }

    public void addExcludeKeyword(FindKeywordDTO dto,String keyword){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        optional.ifPresent(searchKeyword -> searchKeyword.addExcludeKeyword(keyword));
    }

    public void removeExcludeKeyword(FindKeywordDTO dto,String keyword){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        optional.ifPresent(searchKeyword -> searchKeyword.removeExcludeKeyword(keyword));
    }

    public List<String> findAllExcludeKeyword(FindKeywordDTO dto){
        Optional<SearchKeyword> optional = findKeywordByInfo(dto);
        if(optional.isPresent())
            return optional.get().getExcludeKeywords();
        return Collections.emptyList();
    }
}
