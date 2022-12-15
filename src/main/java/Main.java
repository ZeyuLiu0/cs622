import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args)  {
       if(args[0].equals("youtube")){
           StringBuilder sb = new StringBuilder();
           for(int i =1; i<args.length;i++){
               sb.append(args[i]).append(" ");
           }
           dealWithYB(sb.toString());
       }else if(args[0].equals("ig")){
           dealwithIG(args);
       }else {
           StringBuilder sb = new StringBuilder();
           for(int i =1 ;i<args.length;i++){
               sb.append(args[i]).append(" ");
           }
           dealwithTT(sb.toString());
       }
    }


    public static void split(String fileName){
        File file;
        if(fileName.equals("/Users/liuzeyu/Downloads")){
            file = new File("/Users/liuzeyu/Downloads");
        }else{
            file = new File("./video/"+fileName);
        }
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
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<WebElement> videos = driver.findElements(By.xpath("//ytd-video-renderer/div[1]/ytd-thumbnail[1]/a"));
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }

    public static void dealWithYB(String keyword){
        List<String> urls = getUrlsFromYB(keyword);

        for(int i =0; i< 1;i++){
            Task task = new Task(urls.get(i),i, "youtube");
            task.start();
            try{
                task.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        split("youtube");
    }

    public static List<String> getUrlsFromIG(String keyWord, String user, String pass) throws InterruptedException {
        List<String> output = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.instagram.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement username = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label/input"));
        WebElement password=  driver.findElement(By.xpath("//input[@ name='password']"));
        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]"));
        username.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[2]/section/main/div/div/div/div/button")).click();
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[2]/div[2]/div/a"));
        searchButton.click();


        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/div/div/div[2]/div[1]/div/input"));
        search.sendKeys(keyWord);

        WebElement item = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/div/div/div[2]/div[2]/div/div[1]/div/a/div"));
        item.click();
        Thread.sleep(10000);
        List<WebElement> videos = driver.findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[2]/section/main/div/div[3]/article/div[1]/div/div/div/a"));
        System.out.println(videos.size());
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }

    public static void dealwithIG(String[] args){
        StringBuilder sb = new StringBuilder();
        for(int i = 3; i<args.length;i++){
            sb.append(args[i]).append(" ");
        }
        String keyword = sb.toString();
        String user = args[1];
        String password = args[2];
        List<String> urls = new ArrayList<>();
        try{
            urls = getUrlsFromIG(keyword,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Starting task from ig");
        List<Thread> list= new ArrayList<>();

        for(int i =0; i< 3;i++){
            list.add(new Task(urls.get(i),i, "IG"));
        }
        for(Thread thread:list){
            thread.start();
        }

        for(Thread thread:list){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        split("IG");
    }




    private static List<String> getUrlFromTT(String keyWord) throws InterruptedException {
        List<String> output = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.tiktok.com/");
        Thread.sleep(40000);
        WebElement search = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/div/form/input"));
        search.sendKeys(keyWord);
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/div/form/button"));
        searchButton.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[1]/div[3]")).click();
        Thread.sleep(5000);
        List<WebElement> videos = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[1]/div/div/div/div/div[1]/a"));
        System.out.println(videos.size());
        for(WebElement e : videos){
            output.add(e.getAttribute("href"));
        }
        driver.quit();
        return output;
    }

    public static void dealwithTT(String keyword){
        List<String> urls= new ArrayList<>();
        try{
            urls = getUrlFromTT(keyword);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i =0; i<5; i++){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://tiktokdownload.online/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.xpath("/html/body/main/section[1]/div/div/form/div[2]/input[1]")).sendKeys(urls.get(i));
            driver.findElement(By.xpath("/html/body/main/section[1]/div/div/form/div[3]/button")).click();
            driver.findElement(By.xpath("/html/body/main/section[1]/div/div/div[3]/div/div/div/a[1]")).click();
            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            driver.quit();
        }

        split("/Users/liuzeyu/Downloads");
    }
}
