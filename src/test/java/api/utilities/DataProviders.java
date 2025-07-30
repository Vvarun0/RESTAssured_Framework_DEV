package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name ="AllData")
    public String[][] AllDataProvider(){
    String fileName = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
    int totalRowCount = ReadExcelFile.getRowCount(fileName,"Sheet1");
    int totalColumnCount = ReadExcelFile.getColCount(fileName,"Sheet1");

    String[][] userData = new String [totalRowCount-1][totalColumnCount];
    for(int rowNo =1 ;rowNo<totalRowCount;rowNo++)
    {
        for(int colNo =0;colNo<totalColumnCount;colNo++)
        {
            userData [rowNo-1][colNo]= ReadExcelFile.getCellValue(fileName,"Sheet1",rowNo,colNo);
        }
    }
    return userData;
    }

    @DataProvider(name ="UserNameData")
    public String[] UserNameDataProvider(){
        String fileName = System.getProperty("user.dir") + "//TestData//TestData.xlsx";
        int totalRowCount = ReadExcelFile.getRowCount(fileName,"Sheet1");
//        int totalColumnCount = ReadExcelFile.getColCount(fileName,"Sheet1");

        String[] userNamesData = new String [totalRowCount-1];
        for(int rowNo =1 ;rowNo<totalRowCount;rowNo++)
        {
            userNamesData [rowNo-1]= ReadExcelFile.getCellValue(fileName,"Sheet1",rowNo,1);

        }
        return userNamesData;
    }
}
