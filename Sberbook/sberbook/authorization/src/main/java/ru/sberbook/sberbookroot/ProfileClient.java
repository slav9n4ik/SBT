package ru.sberbook.sberbookroot;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profile", url = "${profile-service.url}")
public interface ProfileClient {
    @RequestMapping("/findUser")
    Profile findProfile(@RequestParam("credential") String credential);

    @RequestMapping("/updateUser")
    boolean updateUser(@RequestParam("profile") Profile profile);

    @RequestMapping("/findUserByResetToken")
    Profile findUserByResetToken(@RequestParam("token") String token);
}
