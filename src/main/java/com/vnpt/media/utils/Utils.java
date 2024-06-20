/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vnpt.media.entity.Users;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.JSONObject;

/**
 *
 * @author vnpt2
 */
public class Utils {

    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6LfcgDoUAAAAAG1fmR-8D3v4509yURfI8OFZ2Wv-";

    public static List<Long> usersListLocalIds = new ArrayList<>();
    public static List<String> usersListShortNames = new ArrayList<>();
    public static String usersBlockId = "";
    public static String usersDepartmentId = "";
    public static String usersDepartmentName = "";

    public static void getTemp() {
        Constants.mapTemp.put("1093978", "[VSHOP]: PARAMS_1 la ma xac thuc (OTP) cua Quy khach de xac nhan mua SIM so PARAMS_2. Ma co hieu luc trong PARAMS_3 phut.");
        Constants.mapTemp.put("1093979", "[VSHOP] Don hang dat SIM so voi ma don hang PARAMS_1 cua Quy khach da bi huy vi qua han! Quy khach vui long truy cap PARAMS_2 de dat hang hoac lien he CSKH: 18001091 (0d).");
        Constants.mapTemp.put("1093980", "[VSHOP]: Chuc mung Quy khach da mua thanh cong SIM so PARAMS_1, ma don hang PARAMS_2. Tong tien PARAMS_3d, PARAMS_4 (chua gom phi giao hang 20.000d). Ma giu so PARAMS_5. Chung toi se lien he de giao hang trong 48h. CSKH: 18001091 (0d). Tran trong");
        Constants.mapTemp.put("1093981", "[VSHOP]: Chuc mung Quy khach da mua thanh cong SIM so PARAMS_1, ma don hang PARAMS_2. Tong tien PARAMS_3d, PARAMS_4 (chua gom phi giao hang 20.000d). Ma giu so PARAMS_5. Quy khach nhan hang tai diem giao dich da chon trong vong 48h. CSKH: 18001091 (0d). Tran trong!");
        Constants.mapTemp.put("1093982", "[VSHOP]: PARAMS_1 la ma xac thuc (OTP) cua Quy khach de hoan tat dang ky PARAMS_2, goi cuoc PARAMS_3, chu ky PARAMS_4, gia goi PARAMS_5d. Ma co hieu luc trong PARAMS_6 phut.");

        Constants.mapTemp.put("1093983", "[VSHOP]: Chuc mung Quy khach da dang ky thanh cong goi cuoc PARAMS_1, gia goi PARAMS_2d, chu ky PARAMS_3, ma don hang PARAMS_4. Chung toi se lien he voi Quy khach theo so PARAMS_5 de xac nhan don hang trong vong 48h. CSKH: 18001166 (0d). Tran trong!");
        Constants.mapTemp.put("1093984", "[VSHOP] Vi tri lap dat dich vu theo ma don hang PARAMS_1 dang ky PARAMS_2 chua the thuc hien duoc. Chung toi se co gang dap ung nhu cau cua Quy khach trong thoi gian som nhat. CSKH: 18001166 (0d). Tran trong!");
        Constants.mapTemp.put("1093985", "[VSHOP] Chuc mung Quy khach da mua thanh cong goi PARAMS_1, chu ky PARAMS_2, gia goi PARAMS_3d, ma don hang PARAMS_4. PARAMS_5. PARAMS_6. CSKH: 18001166 (0d). Tran trong!");
    }

    public static String getHinhThucBan(String cpCode) {
        if (cpCode == null) {
            return "Không xác định";
        }
        if (cpCode.equals("XHH")) {
            return "Kênh xã hội hóa";
        }
        if (cpCode.equals("WEB")) {
            return "Affiliate link";
        }
        if (cpCode.equals("LAND")) {
            return "Landing page";
        }
        if (cpCode.equals("SMS")) {
            return "Mời SMS";
        }
        return "";
    }

    public static String getReasonFail(Integer status) {
        if (status == null) {
            return "";
        }
        if (status == -1) {
            return "Bị hủy bỏ";
        }
        if (status == 0) {
            return "Hóa đơn nháp";
        }
        if (status == 2) {
            return "Chờ kết quả thanh toán";
        }
//        if (status == 3) {
//            return "Thanh toán thành công";
//        }
//        if (status == 4) {
//            return "Đã gọi đặt hàng IT thành công";
//        }
//        if (status == 5) {
//            return "Đơn hàng Fiber đang xử lý";
//        }
//        if (status == 6) {
//            return "Sim đang xử lý";
//        }
//        if (status == 7) {
//            return "Sim chờ thanh toán";
//        }
//        if (status == 8) {
//            return "Sim chờ kich hoat";
//        }
        if (status == -9) {
            return "Thanh toán thất bại";
        }
        if (status == -10) {
            return " Đã gọi đặt hàng IT thất bại";
        }
        if (status == -11) {
            return "Không thể khời tạo giao dịch thanh toán";
        }
        if (status == -12) {
            return "Bị hủy bỏ do có nhiều hơn 1 phương thức giao hàng trong cùng một hóa đơn";
        }
        if (status == -2) {
            return "Bị huỷ bỏ từ phía ĐHSX";
        }
        if (status == -3) {
            return "Huỷ do sim quá 72h không kích hoạt";
        }
        return "";
    }

    private static final Logger LOGGER = Logger.getLogger(Utils.class);

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String getFileExtension(String fullName) {
        if (fullName == null) {
            return fullName;
        }
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static boolean isImageFile(byte[] bytes) {
        boolean result = false;
        int i = 0;
        System.out.println("Byte 0=" + bytes[i]);

        if ((bytes[i] & 0xFF) == 0xFF && (bytes[i + 1] & 0xFF) == 0xD8 && (bytes[i + 2] & 0xFF) == 0xFF
                && (bytes[i + 3] & 0xFF) == 0xE0) {
            // System.out.println("isJPEG");
            result = true;
        } else if ((bytes[i] & 0x89) == 0x89) {
            // System.out.println("isPNG");
            result = true;
        } else if ((bytes[i] & 0x47) == 0x47 && (bytes[i + 1] & 0x49) == 0x49 && (bytes[i + 2] & 0x46) == 0x46) {
            // System.out.println("isGIF");
            result = true;
        }

        return result;
    }

    public static Users getUsersInSession(HttpServletRequest request) {
        Users auth = (Users) request.getSession().getAttribute("auth");
        if (auth != null) {
            boolean checkUpdateRole = (boolean) request.getSession().getAttribute("UPDATE_ROLE_" + auth.getUsername());
            if (checkUpdateRole) {
                return null;
            }
        }
        return auth;
    }

    public static void removeUsersInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("auth");
    }

    public static Date stringddMMyyyyhhmmaToDate(String str) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            return df.parse(str);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public static Date stringMMMddyyyHHmmsstToStringddMMYYY(String str) {
        try {
            DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
            return df.parse(str);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public static String dateToStringddMMyyyy(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static String dateToStringddMMyyyyHHmmss(Date date) {
        DateFormat df = new SimpleDateFormat("ddMMyyyy HHmmss");
        return df.format(date);
    }

    public static String dateToStringHHmmddMMyyyy(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return df.format(date);
    }

    public static Date stringddMMyyyyHHmmssToDate(String str) {
        try {
            DateFormat df = new SimpleDateFormat("ddMMyyyy HHmmss");
            return df.parse(str);
        } catch (ParseException ex) {

            return new Date();
        }
    }

    public static Date stringToDate(String str, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(str);
        } catch (ParseException ex) {
//            ex.printStackTrace();
            LOGGER.error("Lỗi parse date");
            return new Date();
        }
    }

    public static String dateToString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date stringddMMyyyyToDate(String str) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(str);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public static void writeLog(org.apache.log4j.Logger logger, String customerId, String controller, String method) {
        logger.info("customerID: " + customerId + ", Controller: " + controller + ", Method: " + method);
    }

    public static void writeLog(org.apache.log4j.Logger logger, String customerId, String controller, String method, Map<String, String[]> map) {
        logger.info("customerID: " + customerId + ", Controller: " + controller + ", Method: " + method);
        map.entrySet().forEach((pair) -> {
            logger.info("Param: " + pair.getKey() + " " + pair.getValue()[0]);
        });
    }

    public static void writeLog(org.apache.log4j.Logger logger, String controller, String method) {
        logger.info("Controller: " + controller + ", Method: " + method);
    }

    public static void writeLog(org.apache.log4j.Logger logger, String controller, String method, String typeLog, String content) {
        if (typeLog.equalsIgnoreCase("ERROR")) {
            logger.error("Controller: " + controller + ", Method: " + method + ", Error: " + content);
        }
    }

    public static String readFromBufferReader(BufferedReader br) {
        try {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException ex) {

        }
        return "";
    }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        try {
            JsonObject root = new Gson().fromJson(s, JsonObject.class);
            JsonElement data = root.get("data");
            T[] arr = new Gson().fromJson(data.toString(), clazz);
            return new ArrayList(Arrays.asList(arr));
        } catch (JsonSyntaxException e) {
            LOGGER.error("Có lỗi xảy ra", e.fillInStackTrace());
            return new ArrayList<>();
        }
    }

    public static boolean verifyCaptcha(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }
        String response = HttpClientRequest.sendHttpPostRequestFormData(SITE_VERIFY_URL,
                "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse, LOGGER, "");
        JSONObject json = new JSONObject(response);
        return json.getBoolean("success");
    }

    public static List<List<HSSFCell>> readExcelFile(String fileName) throws Exception {
        List<List<HSSFCell>> cellArrayLisstHolder = new ArrayList<>();
        FileInputStream myInput = new FileInputStream(fileName);
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator rowIter = mySheet.rowIterator();
        while (rowIter.hasNext()) {
            HSSFRow myRow = (HSSFRow) rowIter.next();
            Iterator cellIter = myRow.cellIterator();
            List<HSSFCell> cellStoreArrayList = new ArrayList<>();
            while (cellIter.hasNext()) {
                HSSFCell myCell = (HSSFCell) cellIter.next();
                cellStoreArrayList.add(myCell);
            }
            cellArrayLisstHolder.add(cellStoreArrayList);
        }
        return cellArrayLisstHolder;
    }

    public static boolean writeFile(byte[] fileData, File strFilePath) {

        try {
            float quality = 1f;
            Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = (ImageWriter) iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            if (!strFilePath.exists()) {
                strFilePath.createNewFile();
            }
            FileImageOutputStream output = new FileImageOutputStream(strFilePath);
            writer.setOutput(output);
            if (fileData.length > 500 * 1024) {
                quality = 1 - ((10 * 1024 * 1024) / fileData.length);
            }
            iwp.setCompressionQuality(quality);
            InputStream in = new ByteArrayInputStream(fileData);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            IIOImage image = new IIOImage(bImageFromConvert, null, null);
            writer.write(null, image, iwp);
            writer.dispose();
            return true;
        } catch (IOException ex) {
            LOGGER.info("Co loi xay ra: ", ex.fillInStackTrace());
            return false;
        }
    }

    public static String OTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return String.valueOf(otp);
    }

    public static Date getDateFromString(String strDate, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public static String getStringFromDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static List<String> getListDate(String strFromDate, String strToDate, String formatIn, String formatOut) {

        List<String> list = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateFromString(strFromDate, formatIn));
        Calendar end = Calendar.getInstance();
        end.setTime(getDateFromString(strToDate, formatIn));
        while (!start.after(end)) {
            list.add(getStringFromDate(start.getTime(), formatOut));
            start.add(Calendar.DATE, 1);
        }
        return list;

    }

    public static List<String> getListMonth(String strFromDate, String strToDate, String formatIn, String formatOut) {

        List<String> list = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateFromString(strFromDate, formatIn));
        Calendar end = Calendar.getInstance();
        end.setTime(getDateFromString(strToDate, formatIn));
        while (!start.after(end)) {
            list.add(getStringFromDate(start.getTime(), formatOut));
            start.add(Calendar.MONTH, 1);
        }
        return list;

    }

    public static void saveDateTimeInSession(HttpServletRequest request, String dateFrom, String dateTo) {
        request.getSession().setAttribute("dateFrom", dateFrom);
        request.getSession().setAttribute("dateTo", dateTo);
    }

    public static Date getAddDate(int i, Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    //convert tieng viet thanh khong dau
    public static String covertCoDau(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra", e.fillInStackTrace());
        }
        return "";
    }

    public static void writeLog(String fileName, StringBuffer sBuffer) {

        FileOutputStream fos = null;
        PrintWriter pw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);
            pw = new PrintWriter(fos);
            bw = new BufferedWriter(pw);
            bw.write(sBuffer.toString());
        } catch (IOException ex) {
            LOGGER.error("Ghi file loi:" + fileName, ex.fillInStackTrace());
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
                if (pw != null) {
                    pw.flush();
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                LOGGER.error("Ghi file loi:" + fileName, e.fillInStackTrace());
            }
        }
    }

    public static void rename(String oldName, String newName) {
        String str;
        File file = new File(oldName);
        // File (or directory) with new name
        File file2 = new File(newName);
        // Rename file (or directory)
        boolean success = file.renameTo(file2);
        if (!success) {
            // File was not successfully renamed
            str = "Doi ten file khong thanh cong";
            LOGGER.error(str + ":" + oldName + "-" + newName);
        } else {
            file.delete();
            LOGGER.info("Rename File thanh cong");
        }
    }

    public static boolean checkPhoneVina(String phone) {
        phone = phone.trim();
        if (phone != null && !phone.equalsIgnoreCase("")) {
            String firstNumber1 = phone.substring(0, 1);
            String firstNumber2 = phone.substring(0, 2);
            if (firstNumber1.equals("0")
                    && phone.length() == 10) {
                return true;
            } else if (firstNumber2.equals("84") && phone.length() == 11) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static String prefixMsisdn(String msisdn) {
        if (msisdn.trim().indexOf("0") == 0) {
            msisdn = "84" + msisdn.substring(1);
        }
        return msisdn;
    }

    public static String convertMsisdn0x(String msisdn) {
        if (msisdn.trim().indexOf("84") == 0 && msisdn.trim().length() != 9) {
            msisdn = "0" + msisdn.substring(2);
        }
        return msisdn;
    }

    public static String convertVinaphone11To10(String msisdn) {
        if (msisdn == null || (msisdn.length() != 11 && msisdn.length() != 12)) {
            return msisdn;
        }

        //format 84xxx
        if (!msisdn.startsWith("84")) {
            return msisdn;
        }
        List<String> listNewPrefix, listOldPrefix;
        listNewPrefix = Constants.SET_NEW_PREFIX;
        listOldPrefix = Constants.SET_OLD_PREFIX;

        for (int i = 0; i < listOldPrefix.size(); i++) {
            if (msisdn.startsWith(listOldPrefix.get(i))) {
                msisdn = listNewPrefix.get(i) + msisdn.substring(listOldPrefix.get(i).length());
                break;
            }
        }

        return msisdn;
    }

    //public static String VALID_CHAR = " QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890@$_-^!\"'#%&()*+,/\\.:;<>=\\?[]";
    public static char[] VALID_CHAR = {'\'', '?', '@', '$', ',', '_', '-', '^', '!', '"', '#', '%', '&', '(', ')', '*', '+', ',', '/', '\\', '.', ':', ';', '<', '>', '=', ' ', '[', ']'};

    public static boolean isValidSMSChar(char c) {
        if (c >= 97 && c <= 122) {
            return true;
        }
        if (c >= 65 && c <= 90) {
            return true;
        }
        if (c == 95) {
            return true;
        }

        if (c >= 48 && c <= 57) {
            return true;
        }

        for (int i = 0; i < VALID_CHAR.length; i++) {
            if (c == VALID_CHAR[i]) {
                return true;
            }
        }
        return false;
    }

    public static String convertNoSign(String org) {
        char arrChar[] = org.toCharArray();
        char result[] = new char[arrChar.length];
        for (int i = 0; i < arrChar.length; i++) {
            switch (arrChar[i]) {
                case '\u00E1':
                case '\u00E0':
                case '\u1EA3':
                case '\u00E3':
                case '\u1EA1':
                case '\u0103':
                case '\u1EAF':
                case '\u1EB1':
                case '\u1EB3':
                case '\u1EB5':
                case '\u1EB7':
                case '\u00E2':
                case '\u1EA5':
                case '\u1EA7':
                case '\u1EA9':
                case '\u1EAB':
                case '\u1EAD':
                case '\u0203':
                case '\u01CE': {
                    result[i] = 'a';
                    break;
                }
                case '\u00E9':
                case '\u00E8':
                case '\u1EBB':
                case '\u1EBD':
                case '\u1EB9':
                case '\u00EA':
                case '\u1EBF':
                case '\u1EC1':
                case '\u1EC3':
                case '\u1EC5':
                case '\u1EC7':
                case '\u0207': {
                    result[i] = 'e';
                    break;
                }
                case '\u00ED':
                case '\u00EC':
                case '\u1EC9':
                case '\u0129':
                case '\u1ECB': {
                    result[i] = 'i';
                    break;
                }
                case '\u00F3':
                case '\u00F2':
                case '\u1ECF':
                case '\u00F5':
                case '\u1ECD':
                case '\u00F4':
                case '\u1ED1':
                case '\u1ED3':
                case '\u1ED5':
                case '\u1ED7':
                case '\u1ED9':
                case '\u01A1':
                case '\u1EDB':
                case '\u1EDD':
                case '\u1EDF':
                case '\u1EE1':
                case '\u1EE3':
                case '\u020F': {
                    result[i] = 'o';
                    break;
                }
                case '\u00FA':
                case '\u00F9':
                case '\u1EE7':
                case '\u0169':
                case '\u1EE5':
                case '\u01B0':
                case '\u1EE9':
                case '\u1EEB':
                case '\u1EED':
                case '\u1EEF':
                case '\u1EF1': {
                    result[i] = 'u';
                    break;
                }
                case '\u00FD':
                case '\u1EF3':
                case '\u1EF7':
                case '\u1EF9':
                case '\u1EF5': {
                    result[i] = 'y';
                    break;
                }
                case '\u0111': {
                    result[i] = 'd';
                    break;
                }
                case '\u00C1':
                case '\u00C0':
                case '\u1EA2':
                case '\u00C3':
                case '\u1EA0':
                case '\u0102':
                case '\u1EAE':
                case '\u1EB0':
                case '\u1EB2':
                case '\u1EB4':
                case '\u1EB6':
                case '\u00C2':
                case '\u1EA4':
                case '\u1EA6':
                case '\u1EA8':
                case '\u1EAA':
                case '\u1EAC':
                case '\u0202':
                case '\u01CD': {
                    result[i] = 'A';
                    break;
                }
                case '\u00C9':
                case '\u00C8':
                case '\u1EBA':
                case '\u1EBC':
                case '\u1EB8':
                case '\u00CA':
                case '\u1EBE':
                case '\u1EC0':
                case '\u1EC2':
                case '\u1EC4':
                case '\u1EC6':
                case '\u0206': {
                    result[i] = 'E';
                    break;
                }
                case '\u00CD':
                case '\u00CC':
                case '\u1EC8':
                case '\u0128':
                case '\u1ECA': {
                    result[i] = 'I';
                    break;
                }
                case '\u00D3':
                case '\u00D2':
                case '\u1ECE':
                case '\u00D5':
                case '\u1ECC':
                case '\u00D4':
                case '\u1ED0':
                case '\u1ED2':
                case '\u1ED4':
                case '\u1ED6':
                case '\u1ED8':
                case '\u01A0':
                case '\u1EDA':
                case '\u1EDC':
                case '\u1EDE':
                case '\u1EE0':
                case '\u1EE2':
                case '\u020E': {
                    result[i] = 'O';
                    break;
                }
                case '\u00DA':
                case '\u00D9':
                case '\u1EE6':
                case '\u0168':
                case '\u1EE4':
                case '\u01AF':
                case '\u1EE8':
                case '\u1EEA':
                case '\u1EEC':
                case '\u1EEE':
                case '\u1EF0': {
                    result[i] = 'U';
                    break;
                }

                case '\u00DD':
                case '\u1EF2':
                case '\u1EF6':
                case '\u1EF8':
                case '\u1EF4': {
                    result[i] = 'Y';
                    break;
                }
                case '\u0110':
                case '\u00D0':
                case '\u0089': {
                    result[i] = 'D';
                    break;
                }
                default:
                    result[i] = arrChar[i];
            }
        }
        return new String(result);
    }

    public static String getFileName(String path) {
        if (path == null || path.compareTo("") == 0) {
            return "";
        }

//        if (path.contains("http://") || path.contains("https://")) {
//            return path;
//        }
        String fileName = path.replace("\\", "/");
        String[] splittedFileName = fileName.split("/");
        if (splittedFileName.length > 0) {
            return splittedFileName[splittedFileName.length - 1];
        }
        return path;
    }

    public static void copyFile(String rootPath, String directPath) throws IOException {
        File directFile = new File(directPath);
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            return;
        }
        if (!directFile.exists()) {
            directFile.mkdirs();
        }
        directFile = new File(directPath + "/" + rootFile.getName());
        if (!directFile.exists()) {
            directFile.createNewFile();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(rootFile);
            os = new FileOutputStream(directFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(getFileName("/menu/fgsdfdsfs.jpg"));
    }
}
