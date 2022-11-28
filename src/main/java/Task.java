import java.io.File;
import java.io.IOException;

public class Task extends Thread{
    private final String url;
    private final int number;
    public Task(String url, int number){
        this.url = url;
        this.number = number;
    }
    @Override
    public void run(){
        File file = null;
        if(new File("./video").mkdirs()){
            file = new File("./video");
        }
        ProcessBuilder processBuilder = new ProcessBuilder("/Users/liuzeyu/Documents/JavaProject/cs622/src/main/recources/shell.sh",url);
        try {
//            processBuilder.directory(file);
            System.out.println("Processing Task: "+number);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("Task "+number+" has finished");
        }
    }
}
