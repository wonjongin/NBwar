package io.github.wonjongin.nbwar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
