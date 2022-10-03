package Utils;

import java.io.*;

public class FileUtil {
    public String readFile(File file) throws IOException {

        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String content="";
        String currentLine=bufferedReader.readLine();
        while(currentLine!=null){
            content+=currentLine+'\n';

            currentLine= bufferedReader.readLine();
        }
        return content;
    }

    public void writeFile(String data,File file) throws IOException {
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.flush();
    }
}
