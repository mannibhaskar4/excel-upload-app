package com.example.exceluploadapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import com.example.exceluploadapp.service.ExcelService;
import com.example.exceluploadapp.service.ExcelSummary;

// import io.swagger.annotations.Api;

@RestController
// @Api(value = "Excel Dependency", protocols = "http")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExcelSummary> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            ExcelSummary excelSummary = excelService.processExcelFile(file);
            // ResponseEntity.ok("Operations completed successfully.");
            return ResponseEntity.ok(excelSummary);
        } catch (Exception e) {
            e.printStackTrace();
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the Excel file.");

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }
}