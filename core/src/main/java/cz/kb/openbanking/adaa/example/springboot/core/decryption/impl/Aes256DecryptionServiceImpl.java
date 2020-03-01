package cz.kb.openbanking.adaa.example.springboot.core.decryption.impl;

import cz.kb.openbanking.adaa.example.springboot.core.decryption.Aes256DecryptionService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Implementation of the {@link Aes256DecryptionService}.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @see Aes256DecryptionService
 * @since 1.0
 */
@Service
public class Aes256DecryptionServiceImpl implements Aes256DecryptionService {
    private static final String AES_ALGORITHM_NAME = "AES";
    private static final int KEY_BYTE_SIZE = 32;
    private static final int KEY_OFFSET = 0;
    private static final int AUTHENTICATION_TAG_BIT_SIZE = 128;

    @Override
    public String decrypt(String cipherText, String salt, String secret) {
        Assert.hasText(cipherText, "base64UrlText must not be empty");
        Assert.hasText(salt, "base64UrlText must not be empty");
        Assert.hasText(secret, "secret must not be empty");

        byte[] decrypted;
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(AUTHENTICATION_TAG_BIT_SIZE, base64Decode(salt));
            cipher.init(Cipher.DECRYPT_MODE, decodeSecretKey(secret), parameterSpec);

            decrypted = cipher.doFinal(base64Decode(cipherText));
        } catch (Exception e) {
            throw new IllegalStateException("Ciphered text '" + cipherText + "' could not be decrypted.", e);
        }

        return new String(decrypted, StandardCharsets.UTF_8);
    }

    /**
     * Decodes Base64Url encoded text.
     *
     * @param  base64UrlText encoded text
     * @return decoded text
     */
    private byte[] base64Decode(String base64UrlText) {
        Assert.hasText(base64UrlText, "base64UrlText must not be empty");

        return Base64.getUrlDecoder().decode(base64UrlText.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes Base64 secret key.
     *
     * @param key BASE64 encoded encryption key
     * @return plain text decoded encryption key
     */
    private SecretKey decodeSecretKey(String key) {
        Assert.hasText(key, "key must not be empty");

        try {
            return new SecretKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)),
                    KEY_OFFSET, KEY_BYTE_SIZE, AES_ALGORITHM_NAME);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException("Cannot get/decode encryption key.", e);
        }
    }
}
