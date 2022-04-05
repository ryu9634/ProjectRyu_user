package com.example.project.login;
import com.example.project.model.entity.User;
import com.example.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean login(String userid,String userpw){
        log.info(userid+"--"+userpw);
        User user =  userRepository.findByUserUserid(userid);
        log.info(user+ " ");
        if( user != null && user.getUserUserpw().equals(userpw) && user.getUserUserid().equals(userid)) {
            log.info(user + " ");
            return true;
        }
        return false;
    }




}
