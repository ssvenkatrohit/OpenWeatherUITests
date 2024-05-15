package util;


	


	import java.text.SimpleDateFormat;
	import java.util.Date;


	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReportManager implements ITestListener {
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		String repName;

		@Override
		public void onTestStart(ITestResult result) {
			
			
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			test= extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.PASS, "Test Passed");
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestFailure(ITestResult result) {
			test= extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
			
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			test= extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.SKIP, "Test Failed");
			test.log(Status.SKIP, result.getThrowable().getMessage());
			
		}

		@Override
		public void onFinish(ITestContext context) {
			extent.flush();
			
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStart(ITestContext context) {
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report"+ timestamp +".html";
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
			sparkReporter.config().setDocumentTitle("WeatherUIProject");
			sparkReporter.config().setReportName("WeatherUI");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "WeatherUI");
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("Username", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Author","rohit");
			
			
			
		}

	}



