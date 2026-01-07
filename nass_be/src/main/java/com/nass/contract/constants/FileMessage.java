package com.nass.contract.constants;

public class FileMessage {
    public static final String EMPTY_FILE = "File rỗng!";

    public static final String IS_NOT_EXCEL_FILE_DEV = "File must be ended with .xls or .xlsx!";
    public static final String IS_NOT_EXCEL_FILE_USER = "File phải có định dạng .xls hoặc .xlsx!";

    public static final String IS_NOT_XML_FILE_DEV = "Không phải file xml!";
    public static final String IS_NOT_XML_FILE_USER = "Không phải file xml!";

    public static String FILE_ERROR_AT_LINE_USER(int line) {
        return "Lỗi ở dòng " + line + "!";
    }
}
