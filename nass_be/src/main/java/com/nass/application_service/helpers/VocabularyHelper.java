package com.nass.application_service.helpers;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class VocabularyHelper {
    public String getStringCellValue(Row row, int colIndex) {
        return getStringCellValue(row, colIndex, "");
    }

    public String getStringCellValue(Row row, int colIndex, String defaultValue) {
        if (row == null) return defaultValue;
        var cell = row.getCell(colIndex);
        if (cell == null) return defaultValue;

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> defaultValue;
        };
    }
}
