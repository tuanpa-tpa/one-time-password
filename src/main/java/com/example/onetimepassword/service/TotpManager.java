package com.example.onetimepassword.service;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Base64;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

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
        // return TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, secret);
        QrData data = new QrData.Builder()
                .label("OTP-PAT")
                .secret(secret)
                .issuer("hello test")
                .algorithm(HashingAlgorithm.SHA256)
                .digits(6)
                .period(30)
                .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = new byte[0];

        try {
            imageData = generator.generate(data);
        } catch (QrGenerationException e) {
            log.error("unable to generate QrCode");
        }

        String mimeType = generator.getImageMimeType();

        return getDataUriForImage(imageData, mimeType);
    }

    public boolean verifyCode(int code, String secret) {
        try {
            return TimeBasedOneTimePasswordUtil.validateCurrentNumber(secret,code,30000);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
