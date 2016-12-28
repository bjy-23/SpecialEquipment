package com.wondersgroup.special.constant;

public interface UrlConstant {

    public static final String BASE_URL1 =
            "http://10.1.8.122:9003/zjtzxxh/android/";// 测试环境
    String BASE_URL2 = "http://10.1.40.62:8080/zjtzxxh/android/";//本地环境
    String BASE_URL3 = "http://10.1.40.62:8080/zjtzxxh/android/";//本地环境
    String BASE_URL4 = "http://tzxx.shzj.gov.cn/zjtzxxh/android/";//正式环境

    /**
     * 附件保存主路径
     */
    static final String FILE_SAVE_PATH = "http://222.190.121.130:18922/";
    // static final String FILE_SAVE_PATH = "http://10.1.8.124:9999";

    /**
     * 附件保存路径
     */
    public static final String IMAGE_PATH = FILE_SAVE_PATH + "/";

    public final static String BASE_URL = BASE_URL1;

    /**
     * 用户登录
     */
    public static final String LOGIN = BASE_URL + "login.do";
    /**
     * 版本更新
     */
    public static final String VERSION = BASE_URL + "getAppInfo";

    public static final String GET_WHOLE_OVERVIEW = BASE_URL + "getWholeOverview";
    public static final String GET_SECTION_OVERVIEW = BASE_URL + "getSectionOverviewByDate";
    public static final String GET_ANALYSIS_DATA = BASE_URL + "getAnalysisData";
    public static final String LOGOUT = BASE_URL + "logout.do";
    public final static String GET_EQUIP_UNIT_TOTAL = BASE_URL + "getEquipUnitTotal";
    public final static String GET_EMERGENCIES = BASE_URL + "getEmergencies";
    String QUERY_DATA_INFO = BASE_URL + "queryDataInfoByOverview";
    public final static String QUERY_DATA_INFO_BY_EQUIP_OVERVIEW = BASE_URL + "queryDataInfoByEquipOverview";
    String QUERY_DATA_INFO_BY_USE_AREA = BASE_URL + "queryDataInfoByUseArea";
    String QUERY_DATA_INFO_BY_BURST = BASE_URL + "queryDataInfoByBurst";
    String QUERY_DATA_INFO_BY_ELEVATOR_AREA = BASE_URL + "queryDataInfoByElevatorArea";
    String QUERY_EQUIP_INFO_BY_CHART = BASE_URL + "queryEquipInfoByChart";//分类跳转链接Type0~9，0为首页，9为客用索道orderNum1~18param为横坐标id
    /**
     * 分类
     */
    String START_EUNIT = BASE_URL + "statEUnit";//从业单位统计
    String STAT_EUNIT_CERT = BASE_URL + "statEUnitCert";//生产单位许可统计
    String STAT_EUNIT_GROW = BASE_URL + "statEUnitGrow";//从业单位增长情况

    /**
     * 档案
     */
    String QUERY_UNIT_LICENSE = BASE_URL + "queryUnitLicense";//单位许可查询
    String GET_UNIT_LICENSE = BASE_URL + "getUnitLicense";//单位许可详情
    String GET_LICENSE_PRO = BASE_URL + "getLicensePro";//核准项目列表
    String QUERY_PERSON_LICENSE = BASE_URL + "queryPersonLicense";//人员许可查询
    String GET_PERSON_LICENSE = BASE_URL + "getPersonLicense";//人员许可详情
    String QUERY_UNIT_UNIT = BASE_URL + "queryUnitUnit";//管理单元查询
    String GET_UNIT_UNIT = BASE_URL + "getUnitUnit";//管理单元详情
    String QUERY_COMP_INFO = BASE_URL + "queryCompInfo";//投诉档案查询
    String GET_COMP_INFO = BASE_URL + "getCompInfo";//投诉档案详情

    String QUERY_CHECK_UNIT = BASE_URL + "queryCheckUnitInfo";//检验检测单位
    String GET_CHECK_UNIT = BASE_URL + "getCheckUnitDetailInfo";//检验检测单位详情
    String QUERY_UNIT_INFO = BASE_URL + "queryUnitInfo";//从业单位查询
    String GET_UNIT_INFO = BASE_URL + "getUnitInfo";//从业单位详情
    String QUERY_PRO_UNIT_INFO = BASE_URL + "queryProUnitInfo";//生产单位查询
    String GET_PRP_UNIT_INFO = BASE_URL + "getProUnitInfo";//生产单位详情

    String QUERY_PERSON_INFO = BASE_URL + "queryPersonInfo";//从业人员查询
    String QUERY_PERSON_DETAIL_INFO_BY_ID = BASE_URL + "queryPersonDetailInfoById";//从业人员基本信息详情查询接口
    String QUERY_PERSON_EXPAND_INFO = BASE_URL + "queryPersonExpandInfo";//人员扩展信息概览
    String QUERY_PERSON_AWARD_DETAIL = BASE_URL + "queryPersonAwardDetail";//从业人员奖惩信息
    String QUERY_EQUIP_INFO = BASE_URL + "queryEquipInfo";//特种设备查询
    String GET_EQUIP_BASIC_INFO = BASE_URL + "getEquipBasicInfo";//设备基本信息详情
    String GET_EQUIP_REGISTER_INFO = BASE_URL + "getEquipRegisterInfo";//设备等级信息详情
    String GET_EQUIP_MAKE_INFO = BASE_URL + "getEquipMakeInfo";//设备制造信息详情
    String GET_EQUIP_DESIGN_INFO = BASE_URL + "getEquipDesignInfo";//设备设计信息详情
    String GET_EQUIP_PROPERTY_RIGHT = BASE_URL + "getEquipPropertyRight";//设备产权信息
    String QUERY_ACCIDENT_INFO = BASE_URL + "queryAccidentInfo";//事故信息查询
    String GET_ACCIDENT_INFO = BASE_URL + "getAccidentInfo";
    String QUERY_CHECKOUT_INFO = BASE_URL + "queryCheckoutInfo";//检验档案
    String QUERY_CHECKOUT_RECORD = BASE_URL + "queryCheckoutRecord";//检验档案记录
    String GET_CHECKOUT_INFO = BASE_URL + "getCheckoutInfo";//检验档案详情
    String QUERY_SUPERVISION_INFO = BASE_URL + "querySupervisionInfo";//监察档案
    String GET_SUPERVISION_INFO = BASE_URL + "getSupervisionInfo";//监察档案详情
    String GET_SUPERVISION_VIEW = BASE_URL + "getSupervisionView";//监察档案概览
    String GET_SUPERVISION_CONTENT = BASE_URL + "getSupervisionContent";//监察档案内容
    String GET_SUPERVISION_SITUATION = BASE_URL + "getSupervisionSituation";//监察档案情况
    String GET_SUPERVISION_DOCUMENT = BASE_URL + "getSupervisionDocument";//监察档案安全指令书
    String QUERY_CASE_INFO = BASE_URL + "queryCaseInfo";//行政处罚
    String GET_CASE_INFO = BASE_URL + "getCaseInfo";//行政处罚详情
    String QUERY_PERSON_EXPAND_INFO_DETAIL = BASE_URL + "queryPersonExpandInfoDetail";//人员扩展信息详情
    String QUERY_PERSON_CERT_PRO_INFO = BASE_URL + "queryPersonCertProInfo";//许可项目信息

    String QUERY_UNIT_AWARD = BASE_URL + "queryUnitAward";//获奖信息列表
    String GET_UNIT_AWARD = BASE_URL + "getUnitAward";//获奖信息详情
    String GET_AREA_DIC = BASE_URL + "getAreaDic";//行政区划字典
    String GET_UNIT_NATURE_DIC = BASE_URL + "getUnitNatureDic";//检验检测机构字典
    String GET_DEVICE_TYPE_DIC = BASE_URL + "getDeviceTypeDic";//设备种类（一级）字典
    String GET_DEVICE_KIND_DIC = BASE_URL + "getDeviceKindDic";//设备品类字典（二级）
    String GET_USE_PLACE = BASE_URL + "getUsePlace";//设备使用场所
}
