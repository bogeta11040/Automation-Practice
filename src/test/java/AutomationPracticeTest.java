import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutomationPracticeTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver99.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS );
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void register(){
        driver.get("http://automationpractice.com/index.php");

        String email = generateEmail(7);
        String name = generateString(5);
        String lastName = generateString(5);
        String pass = generateString(5);
        String days = "15";
        String months = "5";
        String years = "2000";
        String company = generateString(7);

        driver.findElement(By.cssSelector("[title='Log in to your customer account']")).click();
        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(name);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);

        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);

        driver.findElement(By.id("passwd")).sendKeys(pass);

        Select daysSelect = new Select(driver.findElement(By.id("days")));
        daysSelect.selectByValue(days);

        Select monthsSelect = new Select(driver.findElement(By.id("months")));
        monthsSelect.selectByValue(months);

        Select yearsSelect = new Select(driver.findElement(By.id("years")));
        yearsSelect.selectByValue(years);

        checkCheckbox(driver.findElement(By.id("newsletter")),"Yes");
        checkCheckbox(driver.findElement(By.id("optin")),"Yes");

//        Assert.assertEquals(driver.findElement(By.id("firstname")).getText(), name);
//        Assert.assertEquals(driver.findElement(By.id("lastname")).getText(),lastName);

        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(generateString(10));
        driver.findElement(By.id("address2")).sendKeys(generateString(8));

        Select stateSelect = new Select(driver.findElement(By.id("id_state")));
        stateSelect.selectByVisibleText("Iowa");

        driver.findElement(By.id("city")).sendKeys(generateString(8));

        driver.findElement(By.id("postcode")).sendKeys(generateNum(5));

        Select selectCountry = new Select(driver.findElement(By.id("id_country")));

        Assert.assertEquals(selectCountry.getFirstSelectedOption().getText(), "United States");

        driver.findElement(By.id("other")).sendKeys(generateString(30));

        driver.findElement(By.id("phone")).sendKeys(generateNum(10));
        driver.findElement(By.id("phone_mobile")).sendKeys(generateNum(10));

        driver.findElement(By.id("submitAccount")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".header_user_info span")).getText(), name+" "+lastName);

    }

    public void checkCheckbox(WebElement element, String yesOrNo){
        if(yesOrNo.equals("Yes")){
            if(!element.isSelected()){
                element.click();
            }
        } else {
            if(element.isSelected()){
                element.click();
            }
        }
    }
    public String generateEmail(int num){
        String[] chars = {"q","w","e","r","t","y","u","i","o","p"};
        String res = "";

        for (int i = 0; i<num; i++){
            Random random = new Random();
            int r = random.nextInt(chars.length);
            res += chars[r];
        }

        return res+"@mail.com";
    }
    public String generateString(int num){
        String[] chars = {"q","w","e","r","t","y","u","i","o","p"};
        String res = "";

        for (int i = 0; i<num; i++){
            Random random = new Random();
            int r = random.nextInt(chars.length);
            res += chars[r];
        }

        return res;
    }
    public String generateNum(int num){
        String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
        String res = "";

        for (int i = 0; i<num; i++){
            Random random = new Random();
            int r = random.nextInt(nums.length);
            res += nums[r];
        }

        return res;
    }

}