package com.example.onetimepassword.service;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        SecretGenerator generator = new DefaultSecretGenerator();
        return generator.generate();
    }

    public String getUriForImage(String secret) {
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

    public boolean verifyCode(String code, String secret) {
        TimeProvider timeProvider = null;
        try {
            timeProvider = new NtpTimeProvider("pool.ntp.org", 5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        DefaultCodeVerifier  verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        System.out.println(timeProvider.getTime());
        System.out.println(code + " " + secret);
        verifier.setTimePeriod(60);
        verifier.setAllowedTimePeriodDiscrepancy(2);
        return verifier.isValidCode(secret, code);
    }
}
