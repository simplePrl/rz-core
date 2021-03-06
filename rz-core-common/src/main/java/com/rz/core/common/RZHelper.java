package com.rz.core.common;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.StringUtils;

public class RZHelper {
    private final static byte[] PASSWORD_KEY = new byte[] { 'h', 'j', 'n', 'o', 't', 'i', 'f', 'y' };
    private final static byte[] PASSWORD_IV = new byte[] { 'h', 'j', 't', 'p', 'm', 's', 'g', 's' };
    private static List<String> ipV4s = null;

    public static boolean isBaseClazz(Class<?> clazz) {
        if (null == clazz) {
            new IllegalAccessException("clazz");
        }

        if (true == clazz.isPrimitive() || String.class == clazz || Integer.class == clazz || Boolean.class == clazz || Character.class == clazz || Byte.class == clazz
                || Short.class == clazz || Long.class == clazz || Float.class == clazz || Double.class == clazz || Void.class == clazz) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean interfaceOf(Class<?> clazz, Class<?> interfaceClazz) {
        if (null == clazz) {
            new IllegalAccessException("clazz");
        }

        Class<?>[] interfaceClazzes = clazz.getInterfaces();
        for (int i = 0; i < interfaceClazzes.length; i++) {
            if (interfaceClazz == interfaceClazzes[i]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        if (null == collection || true == collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmptyCollection(Map<?, ?> map) {
        if (null == map || true == map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void safeClose(Closeable instance) {
        Assert.isNotNull(instance, "instance");

        try {
            instance.close();
        } catch (Exception exception) {
            // ServiceLocator.Logger.Error(exception, "Failed to dispose the
            // instance.");
        }
    }

    public static String encrypt(String value) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (true == StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(value);
        }

        KeySpec keySpec = new DESKeySpec(PASSWORD_KEY);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(PASSWORD_IV);
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] bytes = cipher.doFinal(value.getBytes(StandardCharsets.US_ASCII));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (2 > hex.length()) {
                // b -> 0b
                stringBuilder.append(0);
            }
            stringBuilder.append(hex);
        }

        return stringBuilder.toString();
    }

    public static String decrypt(String value) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        if (true == StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(value);
        }

        KeySpec keySpec = new DESKeySpec(PASSWORD_KEY);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(PASSWORD_IV);
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] bytes = cipher.doFinal(RZHelper.hexStringToByte(value));

        return new String(bytes, StandardCharsets.US_ASCII);
    }

    public static byte[] hexStringToByte(String hexString) {
        if (true == StringUtils.isBlank(hexString) || 0 != hexString.length() % 2) {
            throw new IllegalArgumentException(hexString);
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] chars = hexString.toCharArray();
        byte[] bytes = new byte[length];

        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ("0123456789ABCDEF".indexOf(chars[i * 2]) << 4 | "0123456789ABCDEF".indexOf(chars[i * 2 + 1]));
        }

        return bytes;
    }

    public static int getBKDRHashCode(String value) {
        if (null == value) {
            return 0;
        }

        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hashCode = 0;

        for (int i = 0; i < value.length(); i++) {
            hashCode = hashCode * seed + (int) value.charAt(i);
        }

        return (int) (hashCode & 0x7FFFFFFF);
    }

    public static String getIpV4() throws SocketException {
        if (null != RZHelper.ipV4s) {
            return RZHelper.ipV4s.get(0);
        }

        List<String> hostAddresses = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress inetAddress;
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                inetAddress = (InetAddress) inetAddresses.nextElement();
                if (null != inetAddress && inetAddress instanceof Inet4Address) {
                    String hostAddress = inetAddress.getHostAddress();
                    if (hostAddress.equals("127.0.0.1")) {
                        continue;
                    }
                    hostAddresses.add(hostAddress);
                }
            }
        }

        RZHelper.ipV4s = hostAddresses;
        return hostAddresses.get(0);
    }
}
