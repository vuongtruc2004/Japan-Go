package com.japan_go_be.business.validators;

import com.japan_go_be.contract.constants.ContentTypes;
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
                || ContentTypes.APPLICATION_XLSX.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_XLS.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isXMLFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".xml")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || ContentTypes.APPLICATION_XML.equalsIgnoreCase(contentType)
                || ContentTypes.TEXT_XML.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isJSONFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".json")) return false;

        String contentType = file.getContentType();
        return contentType == null
                || ContentTypes.APPLICATION_JSON.equalsIgnoreCase(contentType)
                || ContentTypes.TEXT_JSON.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
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
                || ContentTypes.TEXT_MARKDOWN.equalsIgnoreCase(contentType)
                || ContentTypes.TEXT_PLAIN.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }

    public boolean isPowerPointFile(MultipartFile file) {
        if (isEmptyFile(file)) return false;

        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                (!fileName.toLowerCase().endsWith(".ppt") && !fileName.toLowerCase().endsWith(".pptx"))) {
            return false;
        }

        String contentType = file.getContentType();
        return contentType == null
                || ContentTypes.APPLICATION_PPTX.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_PPT.equalsIgnoreCase(contentType)
                || ContentTypes.APPLICATION_OCTET_STREAM.equalsIgnoreCase(contentType);
    }
}