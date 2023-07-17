package com.example.server.repository;

import com.example.server.domain.keyworduser.KeywordUser;
import org.springframework.data.repository.CrudRepository;

public interface KeywordUserRepository extends CrudRepository<KeywordUser, String> {
}
