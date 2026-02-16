package com.japan_go_be.common.validator;

import com.japan_go_be.common.constant.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileValidator {
    public boolean isEmptyFile(MultipartFile file) {
        return file == null || file.isEmpty();
    }

    public boolean isExcelFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx")))
            return false;

        String contentType = file.getContentType();
        return contentType == null
                || ContentType.APPLICATION_XLSX.equalsIgnoreCase(contentType)
                || ContentType.APPLICATION_XLS.equalsIgnoreCase(contentType)
                || ContentType.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isXMLFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".xml")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || ContentType.APPLICATION_XML.equalsIgnoreCase(contentType)
                || ContentType.TEXT_XML.equalsIgnoreCase(contentType)
                || ContentType.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isJSONFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".json")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || ContentType.APPLICATION_JSON.equalsIgnoreCase(contentType)
                || ContentType.TEXT_JSON.equalsIgnoreCase(contentType)
                || ContentType.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isMarkdownFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null) return false;

        String lower = fileName.toLowerCase();
        if (!(lower.endsWith(".md") || lower.endsWith(".markdown"))) {
            return false;
        }

        String contentType = file.getContentType();

        return contentType == null
                || ContentType.TEXT_MARKDOWN.equalsIgnoreCase(contentType)
                || ContentType.TEXT_PLAIN.equalsIgnoreCase(contentType)
                || ContentType.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

}