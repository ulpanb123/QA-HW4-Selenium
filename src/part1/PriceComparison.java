package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class PriceComparison {

    private static double extractPrice(String text) {
        String price = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(price);
    }

    private static double getPrice(String url, String xpath) {
        ChromeOptions chromeoption = new ChromeOptions();
        chromeoption.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver(chromeoption);
        driver.get(url);
        WebElement priceElement = driver.findElement(By.xpath(xpath));
        double price = extractPrice(priceElement.getText());
        driver.quit();
        return price;
    }

    public static void main(String[] args) {
        List<String[]> websites = new ArrayList<>();
        websites.add(new String[]{"iSpace", "https://ispace.kz/iphone/iphone-15/iphone-15-128-gb-chernyy-mtp03hxa", "/html/body/apr-root/apr-product/div/div[1]/div[2]/div[2]/apr-product-card/div/div[1]/div[3]/div/div[1]/div/div/div[2]"});
        websites.add(new String[]{"Mechta", "https://www.mechta.kz/product/telefon-sotovyy-apple-iphone-15-128gb-black/", "//*[@id=\"q-app\"]/div[1]/div[1]/main/div/div/div/div/div[4]/div[2]/div/div[1]/div[3]/div/div/div[1]/div/div/div[2]"});
        websites.add(new String[]{"Alser", "https://alser.kz/p/smartfon-apple-iphone-15-256gb-black-mtp63hxa", "//*[@id=\"__nuxt\"]/div/main/div/div/div[2]/div[2]/div[1]/div/div[3]/div[1]/div/div[1]/div/div[2]"});

        List<Double> prices = new ArrayList<>();
        for (String[] site : websites) {
            double price = getPrice(site[1], site[2]);
            prices.add(price);
            System.out.printf("Price from %s: %.2f Tenge%n", site[0], price);
        }

        if (!prices.isEmpty()) {
            double cheapestPrice = prices.stream().min(Double::compare).orElse(0.0);
            double averagePrice = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double mostExpensivePrice = prices.stream().max(Double::compare).orElse(0.0);

            System.out.printf("%nCheapest price: %.2f Tenge%n", cheapestPrice);
            System.out.printf("Average price: %.2f Tenge%n", averagePrice);
            System.out.printf("Most expensive price: %.2f Tenge%n", mostExpensivePrice);
        } else {
            System.out.println("Failed to retrieve prices from all websites.");
        }
    }
}
