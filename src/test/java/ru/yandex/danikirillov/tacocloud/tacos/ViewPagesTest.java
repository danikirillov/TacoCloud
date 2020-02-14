package ru.yandex.danikirillov.tacocloud.tacos;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
/* материал по созданию тестов был взят из этой статьи https://habr.com/ru/post/446184/
 * и из этой https://habr.com/ru/post/431306/  ,
 * а примеры реализации подсмотрены в репозитории https://github.com/habuma/spring-in-action-5-samples/
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//чтобы тесты работали - помещай их в директорию, где лежит Application
public class ViewPagesTest {

    @LocalServerPort
    private int port;
    private static HtmlUnitDriver browser;//браузер без gui

    @BeforeClass//если тесты содержат одни и те же данные то можно разместить их тут, чтобы использовать во всех тестах
    public static void setup() {
        browser = new HtmlUnitDriver();

        browser.manage()//возвращает Options
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void teardown() {
        browser.quit();
    }

    @Test
    public void testHomePage() {
        String homePage = "http://localhost:" + port;
        browser.get(homePage);

        String titleText = browser.getTitle();
        Assert.assertEquals("Taco Cloud", titleText);

        String imgSrc = browser.findElementByTagName("img")
                .getAttribute("src");
        Assert.assertEquals(homePage + "/images/TacoCloud.png", imgSrc);

        String toDesignLinkText = browser.findElementByTagName("a").getText();
        Assert.assertEquals("order a taco", toDesignLinkText);

        String toDesignLink = browser.findElementByTagName("a").getAttribute("href");
        Assert.assertEquals(homePage + "/design", toDesignLink);
    }

    @Test
    public void testErrorPage() {
        String errorPage = "http://localhost:" + port + "/error";
        browser.get(errorPage);

        String titleText = browser.getTitle();
        Assert.assertEquals("Taco Cloud", titleText);

        String errorText = browser.findElementByTagName("h1").getText();
        Assert.assertEquals("Dear, it`s an error happened. I`m really sorry...", errorText);

        String toHomeLinkText = browser.findElementByTagName("a").getText();
        Assert.assertEquals("back", toHomeLinkText);

        String toHomeLink = browser.findElementByTagName("a").getAttribute("href");
        Assert.assertEquals("http://localhost:" + port + "/", toHomeLink);
    }

    @Test
    public void testOrderCompletePage() {
        String orderCompletePage = "http://localhost:" + port + "/orderComplete";
        browser.get(orderCompletePage);

        String titleText = browser.getTitle();
        Assert.assertEquals("Taco Cloud", titleText);

        String orderCompleteText = browser.findElementByTagName("h1").getText();
        Assert.assertEquals("thanks for your order<3", orderCompleteText);

        String toHomeLinkText = browser.findElementByTagName("a").getText();
        Assert.assertEquals("back", toHomeLinkText);

        String toHomeLink = browser.findElementByTagName("a").getAttribute("href");
        Assert.assertEquals("http://localhost:" + port + "/", toHomeLink);
    }
}
