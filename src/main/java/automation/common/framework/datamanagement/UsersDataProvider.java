package automation.common.framework.datamanagement;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class UsersDataProvider {

    public static final String FILE_PATH = "users.xlsx";

    private static String[] users = new String[100];

    public static void setData() throws EncryptedDocumentException, IOException {
        int i = 0;
        Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                users[i] = dataFormatter.formatCellValue(cell);
                i++;
            }
        }
    }
}
