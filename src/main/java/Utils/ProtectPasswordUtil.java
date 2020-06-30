package Utils;

import java.security.MessageDigest;

public class ProtectPasswordUtil {
    public static String change(String md5){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            String s;
            byte[] array = md.digest(md5.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i<array.length; i++){
                stringBuilder.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
