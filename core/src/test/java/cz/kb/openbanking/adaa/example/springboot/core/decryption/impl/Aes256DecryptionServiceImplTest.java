package cz.kb.openbanking.adaa.example.springboot.core.decryption.impl;

import cz.kb.openbanking.adaa.example.springboot.core.decryption.Aes256DecryptionService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Aes256DecryptionServiceImpl}.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
class Aes256DecryptionServiceImplTest {
    private static final String CIPHER_TEXT = "QUp4Sa8Vd0gwDrUNwhhokj_FBuy2Q88OCA==";
    public static final String SALT = "2KwGx76r3hikHRw7";
    public static final String SECRET_KEY = "Ri1KYU5kUmdVa1hwMnM1djh5L0I/RShIK0tiUGVTaFY=";
    public static final String PLAIN_TEXT = "plaintext";

    /**
     * Test method for {@link Aes256DecryptionServiceImpl#decrypt(String, String, String)}.
     */
    @Test
    void testDecrypt() {
        Aes256DecryptionService decryptionService = new Aes256DecryptionServiceImpl();
        String plainText = decryptionService.decrypt(CIPHER_TEXT, SALT, SECRET_KEY);
        assertThat(plainText).isEqualTo(PLAIN_TEXT);
    }
}