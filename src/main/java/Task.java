import java.io.File;
import java.io.IOException;

public class Task extends Thread{
    private final String url;
    private final int number;

    private final String fileName;
    public Task(String url, int number, String fileName){
        this.url = url;
        this.number = number;
        this.fileName = fileName;
    }
    @Override
    public void run(){
        File file = null;
        if(new File("./"+fileName).mkdirs()){
            file = new File("./"+fileName);
        }
        ProcessBuilder processBuilder = new ProcessBuilder("/Users/liuzeyu/Documents/JavaProject/cs622/src/main/recources/shell.sh",url);
        try {
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
