/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.controller;

import com.vnpt.media.dao.ApiDAO;
import com.vnpt.media.dao.ApiosfDAO;
import com.vnpt.media.entity.Api;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.ApiInfo;
import com.vnpt.media.model.SFInfo;
import com.vnpt.media.utils.Utils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


//---
import org.json.JSONObject;
import org.json.JSONTokener;
/**
 *
 * @author PHU MINH HUONG
 */

@Controller
@EnableWebMvc
@RequestMapping("/api")
public class ExcelHandleController {
    @Autowired
    private ApiDAO apiDao;

    @Autowired
    private ApiosfDAO apiosfDAO;
    
    @RequestMapping(
            value = "/report", 
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public void reportExcel(HttpServletResponse response) throws IOException{
                
        response.setContentType("application/octet-stream");
        String headerKey= "Content-Disposition";
        String headerValue = "attachment;filename=reportAPIInfo.xls";
        response.setHeader(headerKey,headerValue);
        
        List<Api> listAPI = apiDao.findAll();
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("List ApiInfo");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Ten api");
        row.createCell(1).setCellValue("Authentication");
        row.createCell(2).setCellValue("Request");
        row.createCell(3).setCellValue("Response");
        row.createCell(4).setCellValue("Creation Time");
        row.createCell(5).setCellValue("Creator");
        row.createCell(6).setCellValue("Status");
        int dataRowOfIndex = 1;
        for(Api indx: listAPI){
            HSSFRow dataOfRow = sheet.createRow(dataRowOfIndex);
            dataOfRow.createCell(0).setCellValue(indx.getApiname());
            dataOfRow.createCell(1).setCellValue(indx.getAuthentication());
            dataOfRow.createCell(2).setCellValue(indx.getRequest());
            dataOfRow.createCell(3).setCellValue(indx.getResponse());
            dataOfRow.createCell(4).setCellValue(indx.getCreateTime());
            dataOfRow.createCell(5).setCellValue(indx.getUsercreate());
            dataOfRow.createCell(6).setCellValue(indx.getStatus());
            dataRowOfIndex++;
        }
        ServletOutputStream ops = response.getOutputStream();
        workBook.write(ops);
        workBook.close();
        ops.close();
    }
    
    @RequestMapping(value = "/template", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public void templateExcel( HttpServletResponse response) throws IOException{
                
        response.setContentType("application/octet-stream");
        String headerKey= "Content-Disposition";
        String headerValue = "attachment;filename=templateAPIInfo.xls";
        response.setHeader(headerKey,headerValue);
//--------------------------------------------------------------------------
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("List ApiInfo");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Ten Api");
        row.createCell(1).setCellValue("Authentication");
        row.createCell(2).setCellValue("Request");
        row.createCell(3).setCellValue("Response");
        
        ServletOutputStream ops = response.getOutputStream();
        workBook.write(ops);
        workBook.close();
        ops.close();
    }
    
//    @RequestMapping(value = "/import", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public void importExcel( HttpServletResponse response) throws IOException{
//        private static final String FILE_NAME = "/tmp/MyFirstExcel.xlsx";
//        try {
//            FileInputStream excelFile = new FileInputStream(new File(filename));
//            Workbook workbook = new HSSFWorkbook(excelFile);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = datatypeSheet.iterator();
//
//            while (iterator.hasNext()) {
//
//                Row currentRow = iterator.next();
//                Iterator<Cell> cellIterator = currentRow.iterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell currentCell = cellIterator.next();
//                    // getCellTypeEnum() is deprecated, will be renamed to getCellType() in version 4.0
//                    if (currentCell.getCellType() == CellType.STRING) {
//                        System.out.print(currentCell.getStringCellValue() + "--");
//                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
//                        System.out.print(currentCell.getNumericCellValue() + "--");
//                    }
//                }
//                System.out.println();
//            }
//            excelFile.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
//----------------------------------------------------------------------------------------------------------------------
    
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
//        try {
//            if (reapExcelDataFile.isEmpty()) {
//                return ;
//            }
//        HSSFWorkbook workbook = new HSSFWorkbook(reapExcelDataFile.getInputStream());
//        HSSFSheet worksheet = workbook.getSheetAt(0);
//        for(int i=1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//            ApiInfo tempApi = new ApiInfo();
//            HSSFRow row = worksheet.getRow(i);
//            
//            tempApi.setApiname((String)row.getCell(0).getStringCellValue());
//            tempApi.setAuthentication(row.getCell(1).getStringCellValue());
//            tempApi.setRequest((String)row.getCell(2).getStringCellValue());
//            tempApi.setResponse((String)row.getCell(3).getStringCellValue());
//            apiDao.insertVoidAPI(tempApi);
//        }
//        workbook.close(); // Đóng workbook để giải phóng tài nguyên
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//---------------------------------------------------------------------------------------------------
    
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile,HttpServletRequest request) throws IOException {
        long sucess = 0,fail = 0;
        Integer status = 0;
        Users users = Utils.getUsersInSession(request);
        String fileName = reapExcelDataFile.getOriginalFilename();
        SFInfo doInfo = new SFInfo(fileName,new Date(),users.getUsername());
        apiosfDAO.insertVoidSF(doInfo);
        long sfid = apiosfDAO.findSFID(fileName);
        if (fileName.substring(fileName.length() - 4, fileName.length()).equals(".xls")) {
            try (InputStream excelIs = reapExcelDataFile.getInputStream()) {
                // create the Workbook using the InputStream returned by 
                // MultipartFile#getInputStream()
                Workbook wb = WorkbookFactory.create(excelIs); 
                // get the first sheet of the Workbook
                Sheet sheet = wb.getSheet("List ApiInfo"); 

                Iterator<Row> rowIt = sheet.rowIterator();
                // iterating rows..
                while (rowIt.hasNext()) { 
                    ApiInfo tempApi = new ApiInfo();
                    Row currentRow = rowIt.next();
                    if (currentRow.getRowNum() == 0) {
                        continue;
                    }
                    String name = (String)currentRow.getCell(0).getStringCellValue();
                    String req = (String)currentRow.getCell(2).getStringCellValue();
                    String res = (String)currentRow.getCell(3).getStringCellValue();
                    System.out.println("Valid JSON");
                    tempApi.setApiname(name);
                    tempApi.setAuthentication((String)currentRow.getCell(1).getStringCellValue());
                    tempApi.setRequest(req);
                    tempApi.setResponse(res);
                    tempApi.setUsercreate(users.getUsername());
                     if (isValidJSON(req) && isValidJSON(res) && !apiDao.isExistAPIName(name)) {
                            sucess++;
                            status = 1;
                            apiDao.insertVoidAPI(tempApi);
                            apiosfDAO.insertVoidAPIOSF(tempApi,1,sfid);
                        } else {
                            fail++;
                            apiosfDAO.insertVoidAPIOSF(tempApi,0,sfid);
                            System.out.println("Not valid JSON");
                        }
                }
//                doInfo.setSuccess(sucess);
//                doInfo.setFail(fail);
//                doInfo.setStatus(status);
                apiosfDAO.updateSF(sfid,sucess,fail,status);
                wb.close();
            } catch(IOException e) {
//                throw new CustomException("Failed to process");
                e.printStackTrace();
            }
        } else {
//            throw new CustomException("The file should be a .xlsx");
            throw new EOFException();
        }
    }
    
     public static boolean isValidJSON(String jsonString) {
        try {
            // Sử dụng JSONTokener để kiểm tra định dạng JSON
            new JSONObject(new JSONTokener(jsonString));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
