import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;



public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, YoutubeDLException {
//        List<String> urls = getUrls("dead lift");

        new ProcessBuilder("shell.sh","https://www.youtube.com/watch?v=ytGaGIn3SjE").start();



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
        return output;
    }

}
