package com.aadev.aecomics.helpers;

import com.aadev.aecomics.models.Comic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRareComics {

    public static void generateRareComics(List<Comic> listComics){

        int percentRares = (listComics.size() * 12) / 100;

        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        int fleg = 0;
        for (int i = 0; i < listComics.size(); i++) {
            if(fleg == percentRares){
                break;
            }
            Integer temp = random.nextInt(listComics.size());
            if(!list.contains(temp)) {
                list.add(temp);
                fleg++;
                continue;
            }
            i--;
        }

        System.out.println(list);
        for (Integer integer : list) {
            listComics.get(integer).rare = true;
        }
    }
}
