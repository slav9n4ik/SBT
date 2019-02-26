package ru.sberbook.sberbookroot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    /**
     * TODO 1.Запоминать loginAttempt : > n ? дополнительная верификация
     */

    @GetMapping("/login")
    public boolean login(String credential, String pass){
       return findUser(credential, getUserId(pass));
    }

    @GetMapping("/recover")
    public boolean recover(String credential, String type){
        if (type.equals("e-mail") || (type.equals("phone"))){
            System.out.println("Password reset code sent by email");
            return true;
        }
        if (type.equals("phone")){
            System.out.println("Password reset code sent via SMS");
            return true;
        }
        return false;
    }

    @GetMapping("/changePass")
    public boolean changePass(String credential, int code){
        if (code == 1) return true; //saving in db new pass
        return false;
    }

    private long getUserId(String credential){
        //request to host:9000/getUserId?credential=credential
        return 1;
    }

    private boolean findUser(String credential, long id){
        //request to db
        return true;
    }

}
