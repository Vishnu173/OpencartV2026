package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException
	{
		/*
		String data[][] = {};
		return data;
		*/
		
		//  /Users/vishnukannan/eclipse-workspace/OpencartV2026/testData
		String path = ".//testData//Datadriventest_tutorialsninja.xlsx";   //taking excel file from the project
		
		ExcelUtils xlutil = new ExcelUtils(path);  //creating object for the ExcelUtils present in utilities package
		
		int totalrows = xlutil.getRowCount("Credentials");  //row count --> returns (last row index), which is 6
		int totalcolumns = xlutil.getCellCount("Credentials", 1);  //column count --> our case 3 -> (calls last cell count no). only not index.
		
		String logindata[][] = new String[totalrows][totalcolumns];  //created for two dimension arrays which can store all the data respective to rows and columns
		for(int i = 1 ; i <= totalrows; i++)  //1  -- reads the data  from excel storing in two dimensional array.
		{
			for(int j = 0 ; j < totalcolumns; j++) //i is reflecting row, j is reflecting the columns
			{
				logindata[i-1][j] = xlutil.getCellData("Credentials", i, j);
			}
		}
		
		return logindata;
		
	}

}
