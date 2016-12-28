package com.wondersgroup.special.constant;

public interface Constant {
    String CREDIT_CODE_REGEX = "^[A-Za-z0-9]{7}|[A-Za-z0-9]{18}$";
    String DIGIT_REGEX = "\\d{1,8}|\\d{1,8}(\\.\\d{1,2})";// 小数点后1位或2位实数
    //    String DIGIT_REGEX = "-?\\d*(\\.\\d{1,2})";// 小数点后1位或2位实数
    String INTEGER_REGEX = "\\d";

    public static interface HomeDateType {
        public static final String TYPE_DEVICE_OVERVIEW = "1";
        public static final String TYPE_DEVICE_USEINFO = "2";
        public static final String TYPE_ELEVATOR_USE = "3";
        public static final String TYPE_EMERGENCY = "4";
        String TYPE_ANALYSIS = "5";
    }

    public static interface Overview {
        public static final String HOME_PAGE = "0";//首页
        public static final String BOILER = "1";//锅炉
        public static final String VESSEL = "2";//压力容器
        public static final String CYLINDER = "3";//气瓶
        public static final String PIPE = "4";//压力管道
        public static final String ELEVATOR = "5";//电梯
        public static final String LIFTING = "6";//起重器械
        public static final String VEHICLE = "7";//场内车辆
        public static final String FACILITY = "8";//游乐设施
        String TRANSPORT = "9";//客运索道
    }

    public static interface AreaType {
        String TYPE_EQU = "1";//设备
        String TYPE_UNIT = "2";//单位
    }

    /**
     * 锅炉
     */
    public static interface Boiler {
        public static final String CATEGORY = "1";//类别分析
        public static final String AREA = "2";//使用场所
        public static final String FUEL = "3";//燃料种类
        public static final String PRESSURE = "4";//锅炉压力
    }

    /**
     * 压力容器
     */
    public static interface Vessel {
        public static final String CATEGORY = "1";//类别分析
        public static final String AREA = "2";//使用场所
        public static final String PURPOSE = "5";//用途分析
        public static final String EQUIPMENT = "6";//特殊设备
    }

    /**
     * 气瓶
     */
    public static interface Cylinder {
        public static final String CATEGORY = "1";//类别分析
        public static final String FILLING = "7";//按充装单位
        public static final String MEDIUM = "8";//按介质种类
        public static final String VOLUME = "9";//按容积分
    }

    /**
     * 压力管道
     */
    public static interface Pipe {
        public static final String CATEGORY = "1";//类别分析
        public static final String MEDIUM = "8";//按介质种类
    }

    /**
     * 电梯
     */
    public static interface Elevator {
        public static final String CATEGORY = "1";//类别分析
        public static final String AREA = "2";//使用场所
        public static final String DISTRIBUTION = "12";//电梯分布情况
        public static final String SERVICE_LIFE = "13";//住宅电梯使用年限
        public static final String EVALUATION = "14";//电梯评估分析
        public static final String GROWTH = "15";//提增长情况
        public static final String MAINTENANCE = "16";//维保单位
        public static final String RENOVATION = "17";//住宅电梯改造情况分析
    }

    /**
     * 起重器械分析
     */
    public static interface Lifting {
        public static final String CATEGORY = "1";//类别分析
        public static final String AREA = "2";//使用场所
        public static final String TONNAGE = "10";//吨位分析
    }

    /**
     * 场内车辆
     */
    public static interface Vehicle {
        public static final String CATEGORY = "1";//类别分析
    }

    /**
     * 游乐设施
     */
    public static interface Facility {
        public static final String CATEGORY = "1";//类别分析
        public static final String LEVEL = "11";//按设备级别
    }

    public static interface Transport {
        String TRANSPORT = "18";//客运索道
    }

    /**
     * 首页及分类查询数据链接接口
     */
    public static interface DataInfo {
        String EQUIPMENT_NO = "1";//特种设备数量
        String USE_NO = "2";//使用单位数量
        String PRODUCTION_NO = "3";//生产单位数量
        String MANAGE_NO = "4";//管理单元数量
        String ELEVATOR_UNIT = "5";//电梯维保单位
        String CHECK_NO = "6";//检查单位数
        String CHECK_EQU_NO = "7";//检查设备数
        String ACCIDENT_NO = "8";//事故数
        String CASES_NO = "9";//案件数
    }

    public interface BackCode {
        public static final int CODE_ZERO = 0;
    }

    public interface Extra {
        public static final String FLAG_QRCODE = "flagQrcode";
        public static final String URL = "url";
        public static final String ID = "id";
        public static final String DEALER_ID = "dealer_id";
        public static final String MARKET_ID = "market_id";
        public static final String MARKET_NAME = "market_name";
        public static final String NAME = "name";
        public static final String TITLE = "title";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String UUID = "uuid";
        public static final String ADD = "add";

        /***/
        public static final String SCAN = "scan";
    }

    /**
     * 信息类型
     */
    interface Info {
        String EQUIP_BASIC_INFO = "1";//设备基本信息详情
        String EQUIP_REG_INFO = "2";//设备登记信息
        String EQUIP_MAKE_INFO = "3";//设备制造信息
        String EQUIP_DESIGN_INFO = "4";//设备设计信息
        String PERSON_DETAIL_INFO = "5";//从业人员基本信息
        String UNIT_INFO_INFO = "6";//从业单位基本信息
        String CHECK_UNIT_INFO = "7";//检验检测单位基本信息
        String PERSON_AWARD_INFO = "8";//从业人员奖惩信息
        String PERSON_LICENSE_INFO = "9";//从业人员许可信息
    }

    /**
     * 设备基本信息
     */
    interface EquipInfo {
        String REG_INFO = "1";//注册登记信息
        String IDENT_INFO = "2";//设备标识
        String DESIGN_INFO = "3";//设计信息
        String PRODUCT_INFO = "4";//制造信息
        String PROPERTY_INFO = "5";//产权信息
        String USE_INFO = "6";//使用信息
        String MANAGE_INFO = "7";//设备管理单位
        String SAFE_INFO = "8";//安全管理
        String BPVI_INFO = "9";//监检单位信息
        String MAINTENANCE = "10";//维修保养信息
        String TEC_INFO = "11";//技术参数
        String TEC_EXPA_INFO = "12";//技术参数-扩展参数
    }

    /**
     * 从业人员类型
     */
    interface EmployType {
        String EMPLOYMENT = "0";//从业人员
        String TEST = "1";//检验检测人员
        String INSPECTOR = "2";//监察人员
        String NONDESTRUCTIVE = "3";//专家
    }

    interface CheckoutRoutineType {
        String CHECKOUT = "01";//检验档案
        String ROUTINE = "02";//监察档案
    }

}
