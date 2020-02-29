package com.common.framework.configuration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class EnvironmentProvider {

    private static final String FILE_PATH = "environments.xlsx";

    private static String[] urls = new String[100];

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
                urls[i] = dataFormatter.formatCellValue(cell);
                i++;
            }
        }
    }

    public static String getUrl(String description)
            throws IOException {
        setData();
        for (int i = 0; i < 100; i++) {
            if (urls[i].contains(description)) {
                return urls[i + 1];
            }
        }
        return "about:blank";
    }

}
