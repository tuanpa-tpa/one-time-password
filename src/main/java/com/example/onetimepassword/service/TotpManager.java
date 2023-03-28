package com.example.onetimepassword.service;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
/**
 * Created by pat on 28-Mar-23 - 9:32 AM
 *
 * @author pat
 * @project one-time-password
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TotpManager {
    public String generateSecret() {
        return TimeBasedOneTimePasswordUtil.generateBase32Secret();
    }

    public String getUriForImage(String secret) {
        String keyId = "username";
        return TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, secret);
    }

    public boolean verifyCode(int code, String secret) {
        try {
            return TimeBasedOneTimePasswordUtil.validateCurrentNumber(secret,code,100000);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
