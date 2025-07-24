package com.basma.Demo1.commun;

import com.basma.Demo1.user.User;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        // Test isExisting START
        String emptyString = "Basma";

        if (CommunHelper.CheckIfExists(emptyString)) {
            System.out.println("Exists!");
        } else {
            System.out.println("Does not exists!");
        }

        // Test isExistiNg END
        List<User> list = new ArrayList<>();
        if (CommunHelper.CheckIfExistsList(list)){
            System.out.println("Exists!");
        }else{
            System.out.println("Does not exists!");}
    }
}
