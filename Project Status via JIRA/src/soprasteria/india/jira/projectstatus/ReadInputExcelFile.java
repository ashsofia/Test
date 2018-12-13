package soprasteria.india.jira.projectstatus;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

public class ReadInputExcelFile {

	/**
	 * ! Constant declaration for logWriter
	 */
	private static CreateJS ganttChartWriter;

	private static CreateJS taskPerResourceChartWriter;

	public static void main(String[] args) throws IOException, ParseException {
		
		String startDate=args[0];
		String endDate=args[1];
		
		File fileChromeDriver = new File("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
		
		String downloadFilepath = "D:\\Project Status via JIRA\\inputFile";
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory", downloadFilepath);
	    
	    ChromeOptions options = new ChromeOptions();
	    HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
	    options.setExperimentalOption("prefs", chromePrefs);
	    
	    DesiredCapabilities cap = DesiredCapabilities.chrome();
	    cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
	    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver = new ChromeDriver(cap);		
		
		driver.get("https://jira.cimpa.com/login.jsp");
		
		driver.findElement(By.id("login-form-username")).sendKeys("ramkumar");
		driver.findElement(By.id("login-form-password")).sendKeys("Jan20171#");
		driver.findElement(By.id("login-form-submit")).click();
		
		driver.get("https://jira.cimpa.com/secure/ConfigureReport!default.jspa?selectedProjectId=16253&projectOrFilterId=project-16253&projectOrFilterName=RATP%20Migration&reportKey=jira-timesheet-plugin:report");
		
		driver.findElement(By.id("date_startDate")).sendKeys(startDate);
		
		driver.findElement(By.id("date_endDate")).sendKeys(endDate);
		
		driver.findElement(By.id("next_submit")).click();
		
		driver.findElement(By.linkText("Excel View")).click();		
		
		FluentWait<WebDriver> wait = new FluentWait(driver).withTimeout(1000, TimeUnit.MILLISECONDS).pollingEvery(200, TimeUnit.MILLISECONDS);
		
		File fileToCheck = new File(downloadFilepath + "\\Timesheet Report.xls" );

		wait.until((WebDriver wd) -> fileToCheck.exists());

		String excelColumns = ToolConfigurationProperties.excelColumns;

		String requiredColumns = ToolConfigurationProperties.requiredColumns;

		String excelColumnFromFile = null;
		
		//FileInputStream inputStream = new FileInputStream(new File(ToolConfigurationProperties.inputExcelFilePath));
		
		//FileUtils.copyFile(fileToCheck, new File("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\inputFile\\Timesheet Report.xlt"));
		
		//fileToCheck.renameTo(new File("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\inputFile\\Timesheet Report.xlt"));
		
		Runtime.getRuntime().exec("D:\\Project Status via JIRA\\autoIt\\xlsToxlsx.exe");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FileInputStream inputStream = new FileInputStream("D:\\Project Status via JIRA\\inputFile\\Timesheet Report.xlsx");		

		boolean isCriticalityRequired = ToolConfigurationProperties.isCriticalityRequired;

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> iterator = firstSheet.iterator();

		HashMap<String, Integer> columnOrderMap = ToolHelper.getColumnOrder(excelColumns);

		boolean requiredColumnsPresent = ToolHelper.checkRequiredColumns(columnOrderMap, requiredColumns);

		if (requiredColumnsPresent) {

			boolean excelHeader = true;

			l1: while (iterator.hasNext()) {

				if (excelHeader) {
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					excelColumnFromFile = ToolHelper.readExcelColumsFromFile(cellIterator);
					excelHeader = false;
					if (!excelColumnFromFile.equals(excelColumns)) {
						System.out.println("Columns in Excel sheet and property file are not same.!");
						break l1;
					}
				} else {
					mapColumnValues(iterator, excelColumns, isCriticalityRequired, columnOrderMap);
				}

				System.out.println();
			}

		} else {
			System.out.println("required Columns are not Present in the Excel Header.");
		}

		workbook.close();
		inputStream.close();

		/*File file = new File("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\Project Tracking via Jira - Version 1.html");
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(file.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private static void mapColumnValues(Iterator<Row> iterator, String excelColumns, boolean isCriticalityRequired,
			HashMap<String, Integer> columnOrderMap) throws IOException, ParseException {

		ganttChartWriter = new CreateJS("C:\\Program Files (x86)\\Jenkins\\workspace\\JiraToolRunner\\js\\amChartGanttChart.js");

		ganttChartWriter.write(
				"function amGanttChart(themeType){var chart = AmCharts.makeChart( \"chartdiv\", {  \"type\": \"gantt\",  \"theme\": themeType,  \"marginRight\": 70,  \"period\": \"DD\",  \"dataDateFormat\": \"YYYY-MM-DD\",  \"columnWidth\": 0.5,  \"valueAxis\": {    \"type\": \"date\"  },  \"brightnessStep\": 7,  \"graph\": {    \"fillAlphas\": 1,    \"lineAlpha\": 1,    \"lineColor\": \"#fff\",    \"fillAlphas\": 0.85,    \"balloonText\": \"<b>[[task]]</b>:<br />[[open]] -- [[value]]\"},  \"rotate\": true,  \"categoryField\": \"category\",  \"segmentsField\": \"segments\",  \"colorField\": \"color\",  \"startDateField\": \"start\",  \"endDateField\": \"end\",  \"dataProvider\": [   ");

		String workLogColor = ToolConfigurationProperties.defaultWorkLogColor;

		Map<String, String> criticalityRatingColorMap = new HashMap<String, String>();

		Map<String, Integer> taskPerresource = new HashMap<String, Integer>();

		if (isCriticalityRequired) {
			criticalityRatingColorMap = ToolHelper.mapCritingRatingAndColor(
					ToolConfigurationProperties.criticalityRating, ToolConfigurationProperties.criticalityColour);
		}
		// add dataprovider

		int i = 1;
		ArrayList<String> taskNamesinExcel = new ArrayList<String>();
		boolean firstCategory = true;

		l1: while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			String taskName = null;

			Date logStartDate;

			Date logEndDate = null;

			String loggedStartDate = null;

			String loggedEndDate = null;

			String resource;

			boolean sameTask = false;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				String cellValue = null;

				if (columnOrderMap.get("Key") == i) {
					taskName = cell.getStringCellValue();
					if ("" == taskName) {
						ganttChartWriter.write("]}");
						break l1;
					}
					if (taskNamesinExcel.contains(cell.getStringCellValue())) {
						sameTask = true;
					}
					System.out.print("taskName::" + taskName);
				}

				if (columnOrderMap.get("Date") == i) {
					
					if(cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
						logStartDate = cell.getDateCellValue();
					}
					else {
						String dateValue=cell.getStringCellValue();
							SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
					       SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yy hh:mm a");
					       logStartDate = parseFormat.parse(dateValue);
						
					}
					
					
					
					logEndDate = new Date(logStartDate.getTime() + TimeUnit.DAYS.toMillis(1));
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
					loggedStartDate = formatter.format(logStartDate);
					loggedEndDate = formatter.format(logEndDate);
					System.out.print("loggedDate::" + loggedStartDate);
				}

				if (columnOrderMap.get("Username") == i) {
					boolean existingResource = false;
					resource = cell.getStringCellValue();
					if (taskPerresource.isEmpty()) {
						taskPerresource.put(resource, 1);
					} else {
						for (Map.Entry<String, Integer> entry : taskPerresource.entrySet()) {
							String mapResource = entry.getKey();
							if (mapResource.equals(resource)) {
								existingResource = true;
								int totalTask = entry.getValue() + 1;
								taskPerresource.put(resource, totalTask);
							}
						}
						if (!existingResource) {
							taskPerresource.put(resource, 1);
						}
					}

					System.out.print("resource::" + resource);
				}

				if (isCriticalityRequired && 10 == i) {
					workLogColor = criticalityRatingColorMap.get(String.valueOf(cell.getNumericCellValue()));
				}

				i++;

			}

			if (!sameTask && !firstCategory) {
				ganttChartWriter.write("]},");
			}

			if (!sameTask) {
				ganttChartWriter.write("{\"category\": \"" + taskName + "\",    \"segments\": [ ");
			}

			if (sameTask) {
				ganttChartWriter.write(",");
			}

			ganttChartWriter.write("{      \"start\": \"" + loggedStartDate + "\",      \"end\": \"" + loggedEndDate
					+ "\",	  \"color\": \"" + workLogColor + "\",      \"task\": \"Logged Days Good\"    }");

			taskNamesinExcel.add(taskName);
			firstCategory = false;

			i = 1;

		}

		ganttChartWriter.write(
				" ],  \"valueScrollbar\": {    \"autoGridCount\": true  },  \"chartCursor\": {    \"cursorColor\": \"#55bb76\",    \"valueBalloonsEnabled\": false,    \"cursorAlpha\": 0,    \"valueLineAlpha\": 0.5,    \"valueLineBalloonEnabled\": true,    \"valueLineEnabled\": true,    \"zoomable\": false,    \"valueZoomable\": true  },  \"export\": {    \"enabled\": true  }} );}");

		try {
			if (!taskPerresource.isEmpty()) {
				ToolHelper.writeTaskPerResourceGraph(taskPerResourceChartWriter, taskPerresource);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
