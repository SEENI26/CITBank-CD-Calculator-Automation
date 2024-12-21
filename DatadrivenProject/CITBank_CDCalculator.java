package DatadrivenProject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CITBank_CDCalculator {

    public static void main(String[] args) throws InterruptedException, IOException
    {
//WebDriver Initialization
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        try {
        	// Navigate to the Web Application
            driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");
            driver.manage().window().maximize();
            
            // Identify Web Elements        
            WebElement inideposit = driver.findElement(By.xpath("//input[@id='mat-input-0']"));        
            WebElement length = driver.findElement(By.xpath("//input[@id='mat-input-1']"));    
            WebElement apr = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
            WebElement calbutton = driver.findElement(By.xpath("//button[@id='CIT-chart-submit']"));
            
            System.out.println("User has identified all elements to calculate CD");
        
            // Read Test Data from Excel
            String path = "C:\\Users\\seeni\\eclipse-workspace\\Maven-project\\Excel\\Bankcal.xlsx"; // Excel file path
            int rows = XLUtils.getRowCount(path, "Sheet1"); // Get row count from the sheet
            System.out.println("Row count is: " + rows);
            
            // Process Each Row of Test Data
            for (int i = 1; i <= rows; i++) 
            {
             
            	// Reading data from Excel
                String inidepo = XLUtils.getCellData(path, "Sheet1", i, 0);
                String interestrate = XLUtils.getCellData(path, "Sheet1", i, 1);
                String monthlength = XLUtils.getCellData(path, "Sheet1", i, 2);
                String compoundingmonths = XLUtils.getCellData(path, "Sheet1", i, 3);
                String exptotal = XLUtils.getCellData(path, "Sheet1", i, 4);
    
                // Passing the data into the application
                inideposit.clear();
                length.clear();
                apr.clear();
                Thread.sleep(3000);
                inideposit.sendKeys(inidepo);
                length.sendKeys(monthlength);
                apr.sendKeys(interestrate);
                
                // Handling the drop-down
                WebElement compoundrp = driver.findElement(By.xpath("//mat-select[@id='mat-select-0']"));
                compoundrp.click();
                
                List<WebElement> options = driver.findElements(By.xpath("//div[@id='mat-select-0-panel']//mat-option"));
                for (WebElement option : options) 
                {
                    if (option.getText().equals(compoundingmonths)) 
                    {
                        option.click();
                        break;
                    }
                }
                
             // Click button to calculate CD
                calbutton.click(); 
                
                String acttotal = driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();
                System.out.println("Actual total is: " + acttotal);
                System.out.println("Expected total is: " + exptotal);
                
                if (exptotal.equals(acttotal)) {
                    XLUtils.setCellData(path, "Sheet1", i, 5, "Passed");
                    
                    XLUtils.fillGreenColor(path, "Sheet1", i, 5);
                } else {
                    XLUtils.setCellData(path, "Sheet1", i, 5, "Failed");
                    XLUtils.fillRedColor(path, "Sheet1", i, 5);
                }
            }
            System.out.println("Calculation has been completed.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
