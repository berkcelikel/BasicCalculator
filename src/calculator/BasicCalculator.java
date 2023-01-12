package calculator;

import Utils.BaseStaticDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicCalculator extends BaseStaticDriver {
    public static void main(String[] args) {

        driver.get("https://testsheepnz.github.io/BasicCalculator.html");
        driver.manage().window().maximize();
        Actions aksiyonlar = new Actions(driver);

        for (int i = 0; i < 5; i++) {
            Bekle(1);
            int randomSayi1 = (int) (Math.random() * 101);
            int randomSayi2 = (int) (Math.random() * 101);
            WebElement number1Field = driver.findElement(By.id("number1Field"));
            number1Field.sendKeys(Integer.toString(randomSayi1));
            WebElement number2Field = driver.findElement(By.id("number2Field"));
            number2Field.sendKeys(Integer.toString(randomSayi2));
            for (int j = 0; j < 5; ++j) {
                WebElement select = driver.findElement(By.id("selectOperationDropdown"));
                Select ddown = new Select(select);
                ddown.selectByValue(Integer.toString(j));
                WebElement calculateButton = driver.findElement(By.cssSelector("input[onclick='calculate()']"));
                calculateButton.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[text()='Calculating ...']")));
                WebElement answer = driver.findElement(By.cssSelector("input[id='numberAnswerField']"));

                String strRandom1 = Integer.toString(randomSayi1);
                String strRandom2 = Integer.toString(randomSayi2);

                Double randomBolme = (double) randomSayi1 / randomSayi2;
                String stringRandomBolme = Double.toString(randomBolme);
                Bekle(1);
                switch (ddown.getOptions().get(j).getText()) {
                    case "Add":
                        Assert.assertTrue((randomSayi1 + randomSayi2) == Integer.parseInt(answer.getAttribute("value")));
                        break;
                    case "Subtract":
                        Assert.assertTrue((randomSayi1 - randomSayi2) == Integer.parseInt(answer.getAttribute("value")));
                        break;
                    case "Multiply":
                        Assert.assertTrue((randomSayi1 * randomSayi2) == Integer.parseInt(answer.getAttribute("value")));
                        break;
                    case "Divide":
                        Assert.assertEquals(stringRandomBolme, String.valueOf(answer.getAttribute("value")));
                        break;
                    case "Concatenate":
                        Assert.assertEquals(strRandom1 + strRandom2, answer.getAttribute("value"));
                        break;
                }
            }
            number1Field.clear();
            number2Field.clear();

        }
        BekleKapat();

    }
}
