package com.adamchilds.secuur.encryption;

import com.adamchilds.secuur.encryption.method.EncryptionType;
import com.adamchilds.secuur.encryption.method.cipher.AutokeyCipherUtils;
import com.adamchilds.secuur.encryption.method.cipher.CaesarCipherUtils;
import com.adamchilds.secuur.encryption.method.cipher.SubstitutionCipherUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 */
public class EncryptionOptions {

    private EncryptionType encryptionType;
    private String publicKey;
    private String privateKey;
    private String salt;
    private String autoKey;
    private char[] substitutionChars;
    private int shift;
    private char[][] tabulaRecta;

    /**
     * Creates a default set of {@link EncryptionOptions}.
     *
     * @return a new {@link EncryptionOptions} with default values
     */
    public static EncryptionOptions getDefault() {
        return getDefault(null);
    }

    /**
     * Creates a default set of {@link EncryptionOptions} for the specified {@link EncryptionType}.
     *
     * @param encryptionType the type of encryption algorithm to be used
     * @return a new {@link EncryptionOptions} with default values
     */
    public static EncryptionOptions getDefault(EncryptionType encryptionType) {
        EncryptionOptions encryptionOptions = new EncryptionOptions();

        encryptionOptions.setEncryptionType(encryptionType);
        encryptionOptions.setPrivateKey("7h!$_i5_4-Pr1v4TE_k3y");
        encryptionOptions.setPublicKey("public_key");
        encryptionOptions.setSalt("7hi$_1s-4_5al7");
        encryptionOptions.setAutoKey(AutokeyCipherUtils.AUTOKEY);
        encryptionOptions.setSubstitutionChars(SubstitutionCipherUtils.generateRandomSubstitutions());
        encryptionOptions.setShift(CaesarCipherUtils.generateRandomShift());
        encryptionOptions.setTabulaRecta(AutokeyCipherUtils.generateRandomTabulaRecta());

        return encryptionOptions;
    }

    public EncryptionType getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAutoKey() {
        return autoKey;
    }

    public void setAutoKey(String autoKey) {
        this.autoKey = autoKey;
    }

    public char[] getSubstitutionChars() {
        return substitutionChars;
    }

    public void setSubstitutionChars(char[] substitutionChars) {
        this.substitutionChars = substitutionChars;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public char[][] getTabulaRecta() {
        return tabulaRecta;
    }

    public void setTabulaRecta(char[][] tabulaRecta) {
        this.tabulaRecta = tabulaRecta;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}