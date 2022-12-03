import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.*;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        List<String> urls = getUrlsFromIG("as");

        for(int i =0; i< 4;i++){
            Task task = new Task(urls.get(i),i, "IG");
            task.start();
        }
    }



    public static List<String> getUrlsFromIG(String keyWord) throws InterruptedException {
        List<String> output = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.instagram.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement username = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label/input"));
        WebElement password=  driver.findElement(By.xpath("//input[@ name='password']"));
        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]"));
        username.sendKeys("8482267908");
        password.sendKeys("asdf1994119");
        login.click();
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[2]/div[2]/div/a"));
        searchButton.click();


        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/div/div/div[2]/div[1]/div/input"));
        search.sendKeys("dead lift");

        WebElement item = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/a/div"));
        item.click();
        Thread.sleep(3000);
        List<WebElement> videos = driver.findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[2]/section/main/div[2]/div[1]/div/div/div/div/a"));
        System.out.println(videos.size());
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }
}