package cz.kb.openbanking.adaa.example.springboot.core.decryption;

/**
 * Decryption service for the AES-256 algorithm with GCM mode.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public interface Aes256DecryptionService {

    /**
     * Decrypts a ciphered text with AES-256 algorithm and GCM mode.
     *
     * @param cipherText ciphered text
     * @param salt       encryption's salt
     * @param secret     base64 encoded 256-bit encryption key
     * @return decrypted plain text
     */
    String decrypt(String cipherText, String salt, String secret);
}
