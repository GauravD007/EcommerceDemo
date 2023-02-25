package DataProvider;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class ReadWriteExcel {
    FileInputStream fileInputStream;
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;
    public ReadWriteExcel() throws IOException {
        fileInputStream =new FileInputStream(new File(ConfigFileReader.getTestDataFilePath()));
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
    }
    public XSSFSheet getXssfSheet(String sheetName) {
        xssfSheet=xssfWorkbook.getSheet(sheetName);
        return xssfSheet;
    }
}