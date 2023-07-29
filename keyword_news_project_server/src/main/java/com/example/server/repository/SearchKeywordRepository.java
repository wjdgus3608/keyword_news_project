package com.example.server.repository;

import com.example.server.domain.searchkeyword.SearchKeyword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SearchKeywordRepository extends CrudRepository<SearchKeyword, Long> {
    List<SearchKeyword> findByOwnerId(String ownerId);
    Optional<SearchKeyword> findByKeywordAndOwnerId(String keyword, String ownerId);
}
