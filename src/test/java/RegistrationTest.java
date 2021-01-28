import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class RegistrationTest{
    @Test
    public void positiveRegistrationFlow() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupin@gmail.com");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).getText();
        Assert.assertEquals(result, "Account is created!");
    }
    @Test
    public void registrationFlowWithoutZipcode() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("");
        $(By.cssSelector("[value=Continue]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");

    }
    @Test
    public void registrationFlowWith4digitsZipcode() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("1234");
        $(By.cssSelector("[value=Continue]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");

    }
    @Test
    public void registrationFlowWith6digitsZipcode() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("123456");
        $(By.cssSelector("[value=Continue]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");

    }
    @Test

    public void reregistrationFlowWithoutFirstname() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupin@gmail.com");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
    @Test
    public void reregistrationFlowWithoutLastname() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("");
        $(By.name("email")).sendKeys("G.pupin@gmail.com");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
    @Test
    public void reregistrationFlowWithoutEmail() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
    @Test
    public void reregistrationFlowWithIncorretEmailForm1() {                 //Without ".com"
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupin@gmail");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
    @Test
    public void reregistrationFlowWithIncorretEmailForm2() {                 //Without "@"
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupingmail.com");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
    @Test
    public void reregistrationFlowWithoutConformationOfPasswordl() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupin@gmail.com");
        $(By.name("password1")).sendKeys("test123");
        $(By.name("password2")).sendKeys("");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
    @Test
    public void reregistrationFlowWithOnlyConformationOfPasswordl() {
        open("https://www.sharelane.com/");
        $(By.xpath("//*[text()='ENTER']")).click();
        open("https://www.sharelane.com/cgi-bin/register.py");
        $(By.name("zip_code")).sendKeys("12345");
        $(By.cssSelector("[value=Continue]")).click();
        $(By.name("first_name")).sendKeys("George");
        $(By.name("last_name")).sendKeys("Pupin");
        $(By.name("email")).sendKeys("G.pupin@gmail.com");
        $(By.name("password1")).sendKeys("");
        $(By.name("password2")).sendKeys("test123");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
}

