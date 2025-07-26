package com.basma.Demo1.commun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
@Slf4j
public class CommunHelper {
    public static boolean CheckIfExists(Object object) {
        if (object == null) {
            return false;
        }
        return true;
    }

//    public static boolean isExisting(Object object){
//        if(object!=null)
//            return true;
//        return false;
//    }

    public static boolean CheckIfExistsList(List<?> objList) {
        if (objList.size() > 0) return true;
        return false;
    }
//    public static String HashPassword(String password){
//        BCr
//    }
    public static boolean Requals(String string1, String string2){
        if (string1.equals(string2)) return true;
        return false;
    }
    public static double CalculatePercentage(Integer part, Integer whole){
        if (whole == 0){
            throw new UnsupportedOperationException("Unsupported operation!");
        }
        return part/whole;
    }
    public static String EncryptData(String data){
        BCryptPasswordEncoder encryptor=new BCryptPasswordEncoder();
        String encryptedData=encryptor.encode(data);
        return encryptedData;
    }
    public static boolean checkPassword(String plaintext, String hashedText) {
        return BCrypt.checkpw(plaintext, hashedText);
    }

    public static boolean areEqual(String string1,String string2){
        if(string1.equals(string2)){
            log.info("are equal "+string1+"   "+string2);
            return true;
        }
        return false;
    }
}
