package com.example.server.service;

import com.example.server.domain.keyworduser.KeywordUser;
import com.example.server.domain.keyworduser.UpdateType;
import com.example.server.domain.keyworduser.dto.UpdateSettingDTO;
import com.example.server.repository.KeywordUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordUserService {
    private final KeywordUserRepository keywordUserRepository;

    public Optional<String> signUp(){
        KeywordUser user = KeywordUser.generateUser();
        if(keywordUserRepository.existsById(user.getUserToken()))
            return Optional.empty();
        keywordUserRepository.save(user);
        return Optional.of(user.getUserToken());
    }

    public Optional<KeywordUser> logIn(String userToken){
        return findUserById(userToken);
    }

    public boolean logOut(String userToken){
        return true;
    }

    public void updateSetting(UpdateSettingDTO dto){
        Optional<KeywordUser> user = findUserById(dto.getUserToken());
        if(user.isEmpty()) return;
        if(dto.getUpdateType() == UpdateType.FCM_TOKEN){
            user.get().setFcmToken(dto.getUpdateValue());
        }
        else if(dto.getUpdateType() == UpdateType.ALARM_ALLOWED){
            user.get().setAlarmAllowed(Boolean.getBoolean(dto.getUpdateValue()));
        }
        else if(dto.getUpdateType() == UpdateType.IS_VIP){
            user.get().setVip(Boolean.getBoolean(dto.getUpdateValue()));
        }
        else if(dto.getUpdateType() == UpdateType.FETCH_TIME){
            user.get().setFetchTime(dto.getUpdateValue());
        }
        else if(dto.getUpdateType() == UpdateType.FETCH_INTERVAL){
            user.get().setFetchInterval(dto.getUpdateValue());
        }

    }

    public Optional<KeywordUser> findUserById(String userToken){
        return keywordUserRepository.findById(userToken);
    }
}
