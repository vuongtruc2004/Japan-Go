package com.nass.common.validator;

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
                || "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equalsIgnoreCase(contentType)
                || "application/vnd.ms-excel".equalsIgnoreCase(contentType)
                || "application/octet-stream".equalsIgnoreCase(contentType);
    }

    public boolean isXMLFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".xml")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || "application/xml".equalsIgnoreCase(contentType)
                || "text/xml".equalsIgnoreCase(contentType)
                || "application/octet-stream".equalsIgnoreCase(contentType);
    }

    public boolean isJSONFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".json")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || "application/json".equalsIgnoreCase(contentType)
                || "text/json".equalsIgnoreCase(contentType)
                || "application/octet-stream".equalsIgnoreCase(contentType);
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
                || "text/markdown".equalsIgnoreCase(contentType)
                || "text/plain".equalsIgnoreCase(contentType)
                || "application/octet-stream".equalsIgnoreCase(contentType);
    }

}