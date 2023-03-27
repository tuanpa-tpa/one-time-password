package com.example.onetimepassword.util;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by pat on 27-Mar-23 - 9:50 AM
 *
 * @author pat
 * @project one-time-password
 */
public class OneTimePasswordHelpService {
    private final static Integer LENGTH = 6;

    public static Supplier<Integer> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        };
    }
}
