/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved
 */
package com.baidu.fbu.asset.util;

import com.baidu.fbu.common.service.FormatService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 解密key
 */
public final class DesUtils {

    /**
     * DES算法.
     */
    private static final String DES = "DES";

    /**
     * 字符串默认键值.
     */
    private static final String DEFAULT_KEY = "bAiDu6!0IP$@^20*&#";

    /**
     * log.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(DesUtils.class);

    /**
     * Constructor.
     */
    private DesUtils() {
    }

    /**
     * DES加密.
     *
     * @param data 字符串
     * @return 加密后字符串
     */
    public static String encrypt(String data) {
        return encrypt(data, DEFAULT_KEY);
    }

    /**
     * 根据键值进行加密.
     *
     * @param data 字符串
     * @param key  加密键
     * @return 加密后字符串
     */
    public static String encrypt(String data, String key) {
        if (data == null) {
            return null;
        }
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.encodeBase64String(bt);
        return strs;
    }


    /**
     * 根据键值进行加密.
     *
     * @param data byte数组
     * @param key  加密键byte数组
     * @return 加密后byte数组
     */
    private static byte[] encrypt(byte[] data, byte[] key) {
        byte[] result = null;
        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            result = cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            LOG.error(FormatService.logFormat("InvalidKeyException"), e);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(FormatService.logFormat("NoSuchAlgorithmException"), e);
        } catch (InvalidKeySpecException e) {
            LOG.error(FormatService.logFormat("InvalidKeySpecException"), e);
        } catch (NoSuchPaddingException e) {
            LOG.error(FormatService.logFormat("NoSuchPaddingException"), e);
        } catch (IllegalBlockSizeException e) {
            LOG.error(FormatService.logFormat("IllegalBlockSizeException"), e);
        } catch (BadPaddingException e) {
            LOG.error(FormatService.logFormat("BadPaddingException"), e);
        }
        return result;
    }

    /**
     * 根据键值进行解密.
     *
     * @param data byte数组
     * @param key  加密键byte数组
     * @return 解加密后byte数组
     */
    private static byte[] decrypt(byte[] data, byte[] key) {
        byte[] result = null;
        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            result = cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            LOG.error(FormatService.logFormat("InvalidKeyException"), e);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(FormatService.logFormat("NoSuchAlgorithmException"), e);
        } catch (InvalidKeySpecException e) {
            LOG.error(FormatService.logFormat("InvalidKeySpecException"), e);
        } catch (NoSuchPaddingException e) {
            LOG.error(FormatService.logFormat("NoSuchPaddingException"), e);
        } catch (IllegalBlockSizeException e) {
            LOG.error(FormatService.logFormat("IllegalBlockSizeException"), e);
        } catch (BadPaddingException e) {
            LOG.error(FormatService.logFormat("BadPaddingException"), e);
        }

        return result;
    }

    /**
     * DES解密.
     *
     * @param data 字符串
     * @return 解密后字符串
     */
    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }

    /**
     * 根据键值进行解密.
     *
     * @param data 字符串
     * @param key  加密键
     * @return 解密后字符串
     */
    public static String decrypt(String data, String key) {
        if (data == null) {
            return null;
        }
        byte[] buf = Base64.decodeBase64(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

}