package com.example.onetimepassword.controller;

import com.example.onetimepassword.service.TotpManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pat on 28-Mar-23 - 9:35 AM
 *
 * @author pat
 * @project one-time-password
 */
@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class TotpController {
    private final TotpManager totpManager;

//    test secret, the secret will later be stored in the database with the user
    private String secret = null;

    @PostMapping("/generate-secret")
    public String generateSecret() {
        String s = totpManager.generateSecret();
        secret = s;
        return s;
    }

    @PostMapping("/verify")
    public String verify(@RequestParam int code) {
//        System.out.println("secret: " + secret);
        if (totpManager.verifyCode(code, secret)) {
            return "CORRECT CODE";
        }
        return "INCORRECT CODE";
    }

    @GetMapping("/qrcode")
    public String getQR(@RequestParam String secret) {
        return totpManager.getUriForImage(secret);
    }

}
