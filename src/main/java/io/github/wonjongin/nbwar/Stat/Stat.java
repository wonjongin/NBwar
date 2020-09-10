package io.github.wonjongin.nbwar.Stat;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


public class Stat {
    public void CreateNewStat(String player){
        File fileName = new File("plugins/NBwar/Stat"+ player+".txt");
        File folder_Location1= new File("plugins/NBwar");
        File folder_Location2 = new File("plugins/NBwar/Stat");

        try {
            if(! fileName.exists()){
                folder_Location1.mkdir();
                folder_Location2.mkdir();
                fileName.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.append("레벨:1"+"\r\n"+"공격력:1"+"\r\n"+"크리티컬 확률:1%"+"\r\n"+"생명 흡혈:1%"+"\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long[] getStat(String player){
        File fileName = new File("plugins/NBwar/Stat"+ player+".txt");
        File folder_Location1= new File("plugins/NBwar");
        File folder_Location2 = new File("plugins/NBwar/Stat");
        long[] stat = new long[4];



        try {
            if(! fileName.exists()){
                folder_Location1.mkdir();
                folder_Location2.mkdir();
                fileName.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            List list = new ArrayList();
            String string;

            while((string = bufferedReader.readLine()) != null){
                list.add(Long.valueOf((cutter(string))));
            }

            for(int i=0;i<4;i++){
                stat[i] = ((Long) list.get(i)).longValue();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public long cutter(String string){
        String[] cut = string.split(":");
        return Long.parseLong(cut[1]);
    }
}
