import com.sapher.youtubedl.YoutubeDLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;




public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, YoutubeDLException {
        String keyword = args[0];
        dealWithYB(keyword);

    }

    public static void split(String fileName){
        File file = new File("./"+fileName);
        String[] content = file.list();
        try{
            assert content!=null;
            for(String s : content){
                ProcessBuilder processBuilder = new ProcessBuilder("/Users/liuzeyu/Documents/JavaProject/cs622/src/main/recources/split.sh",s);
                processBuilder.directory(file);
                Process process = processBuilder.start();
                process.waitFor();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<String> getUrlsFromYB(String keyWord)   {
        List<String> output = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement search = driver.findElement(By.xpath("//input[@id='search']"));
        search.sendKeys(keyWord);
        driver.findElement(By.id("search-icon-legacy")).click();
        List<WebElement> videos = driver.findElements(By.xpath("//ytd-video-renderer/div[1]/ytd-thumbnail[1]/a"));
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }

    public static void dealWithYB(String keyword){
        List<String> urls = getUrlsFromYB(keyword);

        for(int i =0; i< 10;i++){
            Task task = new Task(urls.get(i),i, "youtube");
            task.start();
        }
        split(keyword);
    }

}
