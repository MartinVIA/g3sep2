package server;

import java.io.FileNotFoundException;
import java.time.LocalTime;

public class ServerLog {

//    if a filepath is not specified it will automatically go to utils/server_log.txt
    // newLog clears the file, starting from 0 at StartServer beginning

    private static ServerLog instance;

    private ServerLog(){}

    public static ServerLog getInstance(){
      if (instance == null){
        instance = new ServerLog();
      }
      return instance;
    }


    public void log(String filepath, String text)throws FileNotFoundException{
        client.utils.MyFileHandler.appendToTextFile(filepath,text+" "+now());
    }
    public void log(String text)throws FileNotFoundException{
        client.utils.MyFileHandler.appendToTextFile("src/server/server_log.txt",text+" "+now());
    }

    public void newLog(String filepath, String text)throws FileNotFoundException{
        client.utils.MyFileHandler.writeToTextFile(filepath,text+" "+now());
    }
    public void newLog(String text)throws FileNotFoundException{
        client.utils.MyFileHandler.writeToTextFile("src/server/server_log.txt",text+" "+now());
    }

    private String now(){
        LocalTime time= LocalTime.now();
        return time.toString();
    }
}
