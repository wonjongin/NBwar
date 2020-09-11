package io.github.wonjongin.nbwar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class FileIO {
    public static void createFile(String path, String desc) {
        String[] pathList = path.split("/");
        String dirPath = pathList[0]+"/";
        for(int i=1;i<pathList.length-1;i++){
            dirPath = dirPath+"/"+pathList[i];
        }
        File dir = new File(dirPath);
        File file = new File(path);
//        String[] pathList ;
//        pathList = path.split("");

        if(!dir.exists()){
            boolean result = dir.mkdirs();
            if(result){
                getLogger().info("Success to make directory");
            } else {
                getLogger().warning("Fail to make directory");
            }
        }
        try{
            if(!file.exists()){
                boolean result = file.createNewFile();
                if(result){
                    getLogger().info("Success to create file");
                } else {
                    getLogger().warning("Fail to create file");
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.append(desc);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readFile(String path){
        File file = new File(path);
        ArrayList<String> line = new ArrayList<String>();
        String str;
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((str = bufferedReader.readLine()) != null){
                getLogger().info("readline: "+str);
                line.add(str);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return line;
    }
    public static String readFileOnce(String path){
        File file = new File(path);
        try{
        Scanner scan = new Scanner(file);
        String res = scan.toString();
        return res;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return "ERROR";
    }
    public static void readJSON(String path){
        // json-simple 사용 폰에 탭 참고
    }
}
