package com.example.server.controller;

import com.example.server.domain.keyworduser.KeywordUser;
import com.example.server.domain.keyworduser.dto.UpdateSettingDTO;
import com.example.server.service.KeywordUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KeywordUserController {
    private final KeywordUserService keywordUserService;

    @GetMapping("/user")
    public ResponseEntity<?> signUp(){
        Optional<String> optional = keywordUserService.signUp();
        if(optional.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> logIn(@RequestBody Map<String,String> data){
        log.info(data.get("userToken"));
        Optional<KeywordUser> optional = keywordUserService.logIn(data.get("userToken"));
        if(optional.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logOut")
    public ResponseEntity<?> logOut(@RequestBody Map<String,String> data){
        boolean isLogOut = keywordUserService.logOut(data.get("userToken"));
        if(!isLogOut)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(isLogOut);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSetting(@RequestBody UpdateSettingDTO dto){
        keywordUserService.updateSetting(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/find-user")
    public ResponseEntity<?> findUserById(@RequestBody Map<String,String> data){
        Optional<KeywordUser> optional = keywordUserService.findUserById(data.get("userToken"));
        if(optional.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(optional.get());
    }
}
