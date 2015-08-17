package com.adamchilds.secuur.encryption.method;

/**
 *
 */
public enum EncryptionType {

    /*
     * Asymmetric encryption methods
     */
    ASYMMETRIC_RSA,

    /*
     * Binary to text encoding
     */
    BINARY_BASE32,
    BINARY_BASE64,

    /*
     * Ciphertexts
     */
    CIPHER_AUTOKEY,
    CIPHER_BACONIAN,
    CIPHER_CAESAR,
    CIPHER_SUBSTITUTION,

    /*
     * Hashing functions
     */
    HASH_MD2,
    HASH_MD5,
    HASH_SHA,

    /*
     * Symmetric encryption methods
     */
    SYMMETRIC_AES,
    SYMMETRIC_DES,
    SYMMETRIC_IDEA;

    EncryptionType() {

    }

}