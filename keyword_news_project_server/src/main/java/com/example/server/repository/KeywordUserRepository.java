package com.example.server.repository;

import com.example.server.domain.keyworduser.KeywordUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordUserRepository extends CrudRepository<KeywordUser, String> {
    List<KeywordUser> findKeywordUserByFcmToken(String fcmToken);
}
