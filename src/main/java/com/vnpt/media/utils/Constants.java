/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vnpt2
 */
public interface Constants {

    public final static Long ID_GROUPS_APPROVE = 3L;
    public final static Long ID_GROUPS_NHAP_LIEU = 2L;
    public final static Long ID_GROUPS_MANAGER = 1L;

    public final static String SERVICE_NUMBER = "SERVICE_NUMBER";
    public final static String SEPARATOR_REPORT = "::";

    public final static String OTP_ERROR = "Nhập sai mã OTP";
    public final static String OTP_EXPIRE = "Quá hạn gửi mã OTP";

    public final static String FINISH_SESSION = "Đã hết phiên đăng nhập. Vui lòng đăng nhập lại!";
    public final static String BINDDING_DATA_ERROR = "Lỗi trong quá trình binding dữ liệu.";
    public final static String INSERT_DATA_SUCCESS = "Thêm mới dữ liệu thành công.";
    public final static String INSERT_DATA_FAIL = "Thêm mới dữ liệu thất bại.";
    public final static String UPDATE_DATA_SUCCESS = "Cập nhật dữ liệu thành công.";
    public final static String UPDATE_DATA_FAIL = "Cập nhật dữ liệu thất bại.";
    public final static String DELETE_DATA_SUCCESS = "Xóa dữ liệu thành công.";
    public final static String DELETE_DATA_FAIL = "Xóa dữ liệu thất bại.";

    // Ten bang
    public final static String SUCCESS = "Thành công";
    public final static String FAIL = "Thất bại";
    
    
    //Bang api
    public final static String API_NOT_EXIST = "API không tồn tại";
    public final static String API_EXIST = "API đã tồn tại";
    public final static String API_EXIST_EXCEL = "API trong Excel có bị trùng lặp với những API đã tồn tại";

    //APIOSF
    public final static String SF_NOT_EXIST = "Filename không tồn tại";
    public final static String SF_EXIST = "Filename đã tồn tại";
    
    // Trang thai don hang 
    public final static String ORDER_STATUS_SUCCESS = "SUCCESS";
    public final static String ORDER_STATUS_FAIL = "FAIL";
    public final static String ORDER_STATUS_INPROGRESS = "INPROGRESS";
    public final static String ORDER_STATUS_ORDER_DRAFT = "ORDER_DRAFT";
    public final static String ORDER_STATUS_WAITING_SHIP = "WAITING_SHIP";
    public final static String ORDER_STATUS_WAITING_ACTIVE = "WAITING_ACTIVE";

    // local name VNP
    public final static int LOCAL_ID_VNPT = 65;

    // Bang User
    public final static String LOGIN = "Đăng nhập hệ thống";
    public final static String LOGIN_SUCCESS = "Đăng nhập thành công";
    public final static String LOGIN_NOT_SUCCESS = "Đăng nhập không thành công";
    public final static String USER_DETAIL = "Hiển thị chi tiết users";
    public final static String USER_UPDATE_PERSONAL = "Cập nhật thông tin cá nhân";
    public final static String USER_UPDATE = "Cập nhật users";
    public final static String USER_INSERT = "Thêm mới users";
    public final static String USER_DELETE = "Xóa users";
    public final static String USER_CHANGE_PASS = "Thay đổi mật khẩu";
    public final static String USER_RESET_PASS = "Reset mật khẩu";
    public final static String USER_EXISTED = "Người dùng đã tồn tại";
    public final static String OLD_PASS_WRONG = "Mật khẩu cũ không đúng";
    public final static String UPDATE_PASS_FAIL = "Cập nhật mật khẩu thất bại";
    public final static String UPDATE_PASS_SUCCESS = "Cập nhật mật khẩu thành công";
    public final static String ACTIVE_SUCCESS = "Active người dùng thành công";
    public final static String LOCK_SUCCESS = "Lock người dùng thành công";

    // Bang cmdCode
    public final static String CMDCODE_DETAIL = "Hiển thị chi tiết commandCode";
    public final static String CMDCODE_UPDATE = "Cập nhật commandCode";
    public final static String CMDCODE_INSERT = "Thêm mới commandCode";
    public final static String CMDCODE_DELETE = "Xóa commandCode";
    public final static String CMDCODE_APPROVE = "Duyệt commandCode";
    public final static String CMDCODE_APPROVE_DEL = "Duyệt commandCode chờ xóa";
    public final static String CMDCODE_APPROVE_LIST = "Duyệt list commandCode";
    public final static String CMDCODE_APPROVE_LIST_DEL = "Duyệt list commandCode chờ xóa";
    public final static String CMDCODE_REJECT = "Từ chối commandCode";
    public final static String CMDCODE_REJECT_DEL = "Từ chối commandCode chờ xóa";
    public final static String CMDCODE_REJECT_LIST = "Từ chối list commandCode";
    public final static String CMDCODE_REJECT_LIST_DEL = "Từ chối list commandCode chờ xóa";
    public final static String CMDCODE_APPROVED = "Không cập nhật được vì CommandCode đã được duyệt";
    public final static String CMDCODE_SHCODE_ASSIGNED = "Commande Code này đã gán với Đầu số";
    public final static String CMDCODE_APPROVE_SC = "Duyệt dữ liệu thành công.";
    public final static String CMDCODE_APPROVE_FAIL = "Duyệt dữ liệu thất bại.";
    public final static String CMDCODE_REJECT_SC = "Từ chối dữ liệu thành công.";
    public final static String CMDCODE_REJECT_FAIL = "Từ chối dữ liệu thất bại.";
    public final static String CMDCODE_ADDFILE = "Thêm commandCode theo file.";

    // Bang cmdCode
    public final static String SHCODE_DETAIL = "Hiển thị chi tiết đầu số";
    public final static String SHCODE_UPDATE = "Cập nhật đầu số";
    public final static String SHCODE_INSERT = "Thêm mới đầu số";
    public final static String SHCODE_DELETE = "Xóa đầu số";
    public final static String SHCODE_APPROVE = "Duyệt đầu số";
    public final static String SHCODE_APPROVE_LIST = "Duyệt list đầu số";
    public final static String SHCODE_REJECT = "Từ chối đầu số";
    public final static String SHCODE_REJECT_LIST = "Từ chối list đầu số";
    public final static String SHCODE_APPROVED = "Không cập nhật được vì đầu số đã được duyệt";
    public final static String SHCODE_SHCODE_ASSIGNED = "đầu sốnày đã gán với Đầu số";
    public final static String SHCODE_APPROVE_SC = "Duyệt dữ liệu thành công.";
    public final static String SHCODE_APPROVE_FAIL = "Duyệt dữ liệu thất bại.";
    public final static String SHCODE_REJECT_SC = "Từ chối dữ liệu thành công.";
    public final static String SHCODE_REJECT_FAIL = "Từ chối dữ liệu thất bại.";
    public final static String SHCODE_ADDFILE = "Thêm đầu số theo file.";

    // Bang cp
    public final static String CP_UPDATE_LIST_SHCODE = "Gán đầu số cho CP";
    public final static String CP_REMOVE_SHCODE = "Bỏ gán đầu số cho CP";
    public final static String CP_UPDATE = "Cập nhật CP";
    public final static String CP_INSERT = "Thêm mới CP";
    public final static String CP_DELETE = "Xóa CP";
    public final static String CP_NOT_EXIST = "CP không tồn tại";

    // Bang exportCP
    public final static String EXPORT_CP_UPDATE = "Cập nhật exportCP";
    public final static String EXPORT_CP_INSERT = "Thêm mới exportCP";
    public final static String EXPORT_CP_DELETE = "Xóa exportCP";

    // Bang nottifyCp
    public final static String NOTIFY_CP_UPDATE = "Cập nhật NotifyCp";
    public final static String NOTIFY_CP_INSERT = "Thêm mới NotifyCp";
    public final static String NOTIFY_CP_DELETE = "Xóa NotifyCp";

    // Bang nottifyCp
    public final static String TYPE_UPDATE = "Cập nhật thể loại";
    public final static String TYPE_INSERT = "Thêm mới thể loại";
    public final static String TYPE_DELETE = "Xóa thể loại";

    // Bang group
    public final static String GROUP_UPDATE = "Cập nhật nhóm quyền";
    public final static String GROUP_INSERT = "Thêm mới nhóm quyền";
    public final static String GROUP_DELETE = "Xóa nhóm quyền";
    public final static String GROUP_UPDATE_LIST_ROLE = "Gán quyền cho nhóm";
    public final static String GROUP_UPDATE_LIST_USER = "Gán users cho nhóm";
    public final static String GROUP_USER_FK = "Nhóm quyền đang được gán cho user";
    public final static String GROUP_NOT_EXIST = "Nhóm quyền không tồn tại";

    // bang configMT
    public final static String CONFIG_MT_SAVE = "Thêm mới, cập nhật MT sai cú pháp";
    public final static String CONFIG_MT_DETAIL = "Hiển thị chi tiết ConfigMT";
    public final static String CONFIG_MT_DELETE = "Xóa configMT";
    public final static String CONFIG_MT_EXISTED = "Đầu số và MT_Code đã tồn tại.";

    public final static String INSERT_LOG_SUCCESS = "Insert log thanh cong";
    public final static String INSERT_LOG_NOT_SUCCESS = "Insert log ko thanh cong";

    public final static String TOPUP_STATS = "TOPUP_STATS";

    public final static String CONTENT_LIST = "CONTENT_LIST";
    public final static String CONTENT_INSERT = "CONTENT_INSERT";
    public final static String CONTENT_UPDATE = "CONTENT_UPDATE";
    public final static String CONTENT_DELETE = "CONTENT_DELETE";
    public final static String CONTENT_NOT_EXIST = "Nội dung không tồn tại";
    public final static String CONTENT_CAMPAINGN_FK = "Nội dung đang gắn với chiến dịch";

    public final static String CONTENT_TYPE_LIST = "CONTENTTYPE_LIST";
    public final static String CONTENT_TYPE_INSERT = "CONTENTTYPE_INSERT";
    public final static String CONTENT_TYPE_UPDATE = "CONTENTTYPE_UPDATE";
    public final static String CONTENT_TYPE_DELETE = "CONTENTTYPE_DELETE";
    public final static String CONTENT_TYPE_NOT_EXIST = "Loại nội dung không tồn tại";
    public final static String CONTENT_TYPE_NOT_DELETE = "Không xóa được loại nội dung";

    public final static String ENTITY_LIST = "ENTITY_LIST";
    public final static String ENTITY_INSERT = "ENTITY_INSERT";
    public final static String ENTITY_UPDATE = "ENTITY_UPDATE";
    public final static String ENTITY_DELETE = "ENTITY_DELETE";
    public final static String ENTITY_NOT_EXIST = "Entity không tồn tại";
    public final static String ENTITY_NOT_DELETE = "Không xóa được Entity";

    public final static String EVENT_LIST = "EVENT_LIST";
    public final static String EVENT_INSERT = "EVENT_INSERT";
    public final static String EVENT_UPDATE = "EVENT_UPDATE";
    public final static String EVENT_DELETE = "EVENT_DELETE";
    public final static String EVENT_NOT_EXIST = "Sự kiện không tồn tại";
    public final static String EVENT_NOT_DELETE = "Không xóa được sự kiện";
    public final static String EVENT_CAMPAINGN_FK = "Sự kiện đang gắn với chiến dịch";

    public final static String EVENT_TYPE_LIST = "EVENTTYPE_LIST";
    public final static String EVENT_TYPE_INSERT = "EVENTTYPE_INSERT";
    public final static String EVENT_TYPE_UPDATE = "EVENTTYPE_UPDATE";
    public final static String EVENT_TYPE_DELETE = "EVENTTYPE_DELETE";
    public final static String EVENT_TYPE_NOT_EXIST = "Loại sự kiện không tồn tại";
    public final static String EVENT_TYPE_NOT_DELETE = "Không xóa được loại sự kiện";
    public final static String EVENT_CODE_EXIST = "Mã sự kiện đã tồn tại";

    public final static String PERMISSION_LIST = "PERMISSION_LIST";
    public final static String PERMISSION_INSERT = "PERMISSION_INSERT";
    public final static String PERMISSION_UPDATE = "PERMISSION_UPDATE";
    public final static String PERMISSION_DELETE = "PERMISSION_DELETE";
    public final static String PERMISSION_NOT_EXIST = "Permission không tồn tại";
    public final static String PERMISSION_NOT_DELETE = "Không xóa được Permission";
    public final static String PERMISSION_ROLE_FK = "Permission đang gắn với quền";

    public final static String ROLE_LIST = "ROLE_LIST";
    public final static String ROLE_INSERT = "ROLE_INSERT";
    public final static String ROLE_UPDATE = "ROLE_UPDATE";
    public final static String ROLE_DELETE = "ROLE_DELETE";
    public final static String ROLE_NOT_EXIST = "Role không tồn tại";
    public final static String ROLE_NOT_DELETE = "Không xóa được Role";
    public final static String ROLE_USERS_FK = "Quyền này đang gán với Users";

    public final static String SUBJECT_MEDIA_LIST = "SUBJECTMEDIA_LIST";
    public final static String SUBJECT_MEDIA_INSERT = "SUBJECTMEDIA_INSERT";
    public final static String SUBJECT_MEDIA_UPDATE = "SUBJECTMEDIA_UPDATE";
    public final static String SUBJECT_MEDIA_DELETE = "SUBJECTMEDIA_DELETE";
    public final static String SUBJECT_MEDIA_NOT_EXIST = "Đối tượng truyền thông không tồn tại";
    public final static String SUBJECT_MEDIA_NOT_DELETE = "Không xóa được Đối tượng truyền thông";
    public final static String SUBJECT_MEDIA_CAMPAINGN_FK = "Đối tượng đang gắn với chiến dịch";

    public final static String SUBJECT_MEDIA_TYPE_LIST = "SUBJECTMEDIATYPE_LIST";
    public final static String SUBJECT_MEDIA_TYPE_INSERT = "SUBJECTMEDIATYPE_INSERT";
    public final static String SUBJECT_MEDIA_TYPE_UPDATE = "SUBJECTMEDIATYPE_UPDATE";
    public final static String SUBJECT_MEDIA_TYPE_DELETE = "SUBJECTMEDIATYPE_DELETE";
    public final static String SUBJECT_MEDIA_TYPE_NOT_EXIST = "Loại đối tượng truyền thông không tồn tại";
    public final static String SUBJECT_MEDIA_TYPE_NOT_DELETE = "Không xóa được loại đối tượng truyền thông";

    public final static String SERVICE_LIST = "SERVICE_LIST";
    public final static String SERVICE_INSERT = "SERVICE_INSERT";
    public final static String SERVICE_UPDATE = "SERVICE_UPDATE";
    public final static String SERVICE_DELETE = "SERVICE_DELETE";
    public final static String SERVICE_NOT_EXIST = "Dịch vụ không tồn tại";
    public final static String SERVICE_NOT_DELETE = "Không xóa được dịch vụ";
    public final static String SERVICE_PACKAGE_FK = "Gói dịch vụ đang gắn với dịch vụ";

    public final static String PACKAGE_LIST = "PACKAGE_LIST";
    public final static String PACKAGE_INSERT = "PACKAGE_INSERT";
    public final static String PACKAGE_UPDATE = "PACKAGE_UPDATE";
    public final static String PACKAGE_DELETE = "PACKAGE_DELETE";
    public final static String PACKAGE_NOT_EXIST = "Gói dịch vụ không tồn tại";
    public final static String PACKAGE_NOT_DELETE = "Không xóa được gói dịch vụ";
    public final static String PACKAGE_CAMPAINGN_FK = "Gói dịch vụ đang gắn với chiến dịch";

    public final static String USERS_LIST = "USERS_LIST";
    public final static String USERS_INSERT = "USERS_INSERT";
    public final static String USERS_UPDATE = "USERS_UPDATE";
    public final static String USERS_DELETE = "USERS_DELETE";
    public final static String USERS_NOT_EXIST = "Users không tồn tại";
    public final static String USERS_NOT_DELETE = "Không xóa được Users";
    public final static String USERNAME_EXIST = "Tên đăng nhập đã tồn tại";
    public final static String CHANGE_PASS_SC = "Cập nhật mật khẩu thành công";
    public final static String USERS_GROUP_FK = "Users đang gắn với nhóm quyền";

    public final static String CAMPAIGN_LIST = "CAMPAIGN_LIST";
    public final static String CAMPAIGN_INSERT = "CAMPAIGN_INSERT";
    public final static String CAMPAIGN_UPDATE = "CAMPAIGN_UPDATE";
    public final static String CAMPAIGN_DELETE = "CAMPAIGN_DELETE";
    public final static String CAMPAIGN_APPROVE = "CAMPAIGN_APPROVE";
    public final static String CAMPAIGN_NOT_EXIST = "Chiến dịch không tồn tại";
    public final static String CAMPAIGN_NOT_DELETE = "Không xóa được chiến dịch";
    public final static String CAMPAIGN_TIME_FK = "Vui lòng xóa các khung giờ gắn với chiến dịch";
    public final static String CAMPAIGN_EVENT_FK = "Vui lòng xóa các sự kiện gắn với chiến dịch";
    public final static String CAMPAIGN_SUBJECT_MEDIA_FK = "Vui lòng xóa các đối tượng truyền thông gắn với chiến dịch";
    public final static String CAMPAIGN_ALL = "ALL";
    public final static String CAMPAIGN_APPROVE_SUCCESS = "Duyệt thành công chiến dịch";
    public final static String CAMPAIGN_REJECT_SUCCESS = "Dừng thành công chiến dịch";
    public final static String CAMPAIGN_CODE_EXIST = "Mã chiến dịch đã tồn tại";

    public final static String TIME_SLOT_LIST = "TIMESLOT_LIST";
    public final static String TIME_SLOT_INSERT = "TIMESLOT_INSERT";
    public final static String TIME_SLOT_UPDATE = "TIMESLOT_UPDATE";
    public final static String TIME_SLOT_DELETE = "TIMESLOT_DELETE";
    public final static String TIME_SLOT_NOT_EXIST = "Khung giờ không tồn tại";
    public final static String TIME_SLOT_NOT_DELETE = "Không xóa được khung giờ";
    public final static String TIME_SLOT_CAMPAINGN_FK = "Khung giờ đang gắn với chiến dịch";

    public final static String BLACK_LIST_LIST = "BLACKLIST_LIST";
    public final static String BLACK_LIST_INSERT = "BLACKLIST_INSERT";
    public final static String BLACK_LIST_UPDATE = "BLACKLIST_UPDATE";
    public final static String BLACK_LIST_DELETE = "BLACKLIST_DELETE";
    public final static String BLACK_LIST_NOT_EXIST = "BlackList không tồn tại";
    public final static String BLACK_LIST_EXIST = "BlackList đã tồn tại";
    public final static String BLACK_LIST_NOT_DELETE = "Không xóa được BlackList";

    public final static String WHITE_LIST_LIST = "WHITELIST_LIST";
    public final static String WHITE_LIST_INSERT = "WHITELIST_INSERT";
    public final static String WHITE_LIST_UPDATE = "WHITELIST_UPDATE";
    public final static String WHITE_LIST_DELETE = "WHITELIST_DELETE";
    public final static String WHITE_LIST_NOT_EXIST = "WhiteList không tồn tại";
    public final static String WHITE_LIST_EXIST = "WhiteList đã tồn tại";
    public final static String WHITE_LIST_NOT_DELETE = "Không xóa được WhiteList";

    public final static String EVENT_CODE_TOPUP = "TOPUP";
    public final static String ALERT = "ALERT";
    public final static String MT = "MT";

    public final static String FOLDER_SAVE_IMG = "folder.save.img";
    public final static Integer STATUS_REQUEST_SUCESS = 2;
    public static final List<String> SET_OLD_PREFIX = Arrays.asList("84123", "84124", "84125", "84127", "84129");
    public static final List<String> SET_NEW_PREFIX = Arrays.asList("8483", "8484", "8485", "8481", "8482");
    public static final List<String> APPLIED_OLD_PREFIX = new ArrayList<>();
    public static final List<String> APPLIED_NEW_PREFIX = new ArrayList<>();

    public static List<Long> usersListLocalIds = new ArrayList<>();

    public static Map<String, String> mapTemp = new HashMap<>();
    public static String usersBlockId = "";
    public static String usersDepartmentId = "";
    
}
