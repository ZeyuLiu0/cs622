import com.sapher.youtubedl.YoutubeDLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;




public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, YoutubeDLException {
        List<String> urls = getUrls("dead lift");

        for(int i =0; i< 1;i++){
            Task task = new Task(urls.get(i),i);
            task.start();
        }
//        String directory = System.getProperty("./");
//        YoutubeDLRequest request = new YoutubeDLRequest("https://www.youtube.com/watch?v=QY8dhl1EQfI", directory);
//        YoutubeDLResponse response = YoutubeDL.execute(request);
//        String stdOut = response.getOut();
    }

    public static List<String> getUrls(String keyWord) throws InterruptedException {
        List<String> output = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement search = driver.findElement(By.xpath("//input[@id='search']"));
        search.sendKeys("dead lift");
        driver.findElement(By.id("search-icon-legacy")).click();
        Thread.sleep(5000);
        List<WebElement> videos = driver.findElements(By.xpath("//ytd-video-renderer/div[1]/ytd-thumbnail[1]/a"));
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }

}
