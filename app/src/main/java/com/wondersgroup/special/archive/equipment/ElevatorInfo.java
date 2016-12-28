package com.wondersgroup.special.archive.equipment;

import java.util.LinkedHashMap;

/**
 * Created by chan on 12/8/16.
 * 电梯
 */

public class ElevatorInfo {
    /**
     * 注册登记信息
     * 一样
     *
     * @return
     */
    public static LinkedHashMap<String, String> regInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("注册登记机构", "");
        data.put("注册登记人", "");
        data.put("注册日期", "");
        data.put("登记类别", "");
        return data;
    }

    /**
     * 设备标识
     * 一样
     *
     * @return
     */
    public static LinkedHashMap<String, String> identInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("设备注册代码", "");
        data.put("检验责任所在单位", "");
        data.put("设备名称", "");
        data.put("投用日期", "");
        data.put("设备种类", "");
        data.put("设备类别", "");
        data.put("设备品种", "");
        data.put("设备级别", "");
        data.put("数据源", "");
        data.put("归属管理机构", "");
        data.put("国产/进口", "");
        data.put("所属行政区划", "");
        return data;
    }

    /**
     * 制造信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> productInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("制造单位组织机构代码", "");
        data.put("制造单位名称", "");
        data.put("制造单位法人代表", "");
        data.put("制造单位国籍", "");
        data.put("出厂编号", "");
        data.put("制造日期", "");
        data.put("制造单位资格证书名称", "");
        data.put("制造单位资格证书号", "");
        data.put("制造单位联系电话", "");
        data.put("文件监定单位名称", "");
        data.put("质量证明书编号", "");
        data.put("制造监检机构核准证编号", "");
        data.put("监检证书编号", "");
        data.put("型式试验机构", "");
        data.put("型式试验机构核准证编号", "");
        data.put("型式试验证书编号", "");
        return data;
    }

    /**
     * 产权信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> propertyInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("产权单位名称", "");
        data.put("产权单位组织机构代码", "");
        data.put("联系电话", "");
        data.put("经济类型", "");
        data.put("所属行业", "");
        data.put("购买日期", "");
        return data;
    }

    /**
     * 使用信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> useInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("使用证编号", "");
        data.put("使用单位代码", "");
        data.put("使用证发证日期", "");
        data.put("使用单位地址", "");
        data.put("使用单位名称", "");
        data.put("单位内部编号", "");
        data.put("设备监控级别", "");
        data.put("设备启用日期", "");
        data.put("设备停用日期", "");
        data.put("设备安装单位详细地址", "");
        data.put("乡/镇", "");
        data.put("设备使用状态", "");
        data.put("设备用途", "");
        data.put("布置数量", "");
        data.put("经度", "" + "度");
        data.put("维度", "" + "度");
        data.put("海拔高度", "" + "米");
        data.put("设计单位许可证编号", "");
        data.put("设计文件鉴定报告编号", "");
        data.put("设备使用场所", "");
        data.put("老旧设备状况", "");
        data.put("备注", "");
        return data;
    }

    /**
     * 设备管理单位
     *
     * @return
     */
    public static LinkedHashMap<String, String> manageUnit() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("管理单位名称", "");
        data.put("管理单位联系人", "");
        data.put("管理单位联系电话", "");
        data.put("管理单位地址", "");
        return data;
    }

    /**
     * 安全管理
     *
     * @return
     */
    public static LinkedHashMap<String, String> safeManage() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("安全管理部门", "");
        data.put("安全管理人员", "");
        data.put("联系电话", "");
        data.put("部门地址", "");
        data.put("重点监控设备选项", "");
        return data;
    }

    /**
     * 监检单位信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> bpvi() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("机安检机构名称", "");
        data.put("监检机构代码", "");
        return data;
    }

    /**
     * 维修保养信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> maintenance() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("维保单位名称", "");
        data.put("维保单位组织机构代码", "");
        data.put("维保单位法人代表", "");
        data.put("维保单位法人代表身份证", "");
        data.put("维保单位资格证书号", "");
        data.put("大修周期", "" + "周");
        data.put("维保单位维保负责人", "");
        data.put("维保单位电话", "");
        return data;
    }

    /**
     * 技术参数
     *
     * @return
     */
    public static LinkedHashMap<String, String> tec() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("电梯控制方式", "");
        data.put("电梯拖动方式", "");
        data.put("电梯顶升形式", "");
        data.put("电梯油缸形式", "");
        data.put("自动扶梯梯级型式", "");
        data.put("自动扶梯设备新旧状况", "");
        data.put("扶梯倾斜角", "" + "度");
        data.put("电梯提升高度", "" + "m");
        data.put("梯级宽度", "" + "m");
        data.put("电梯运行速度", "" + "m/s");
        data.put("电梯额定载荷", "" + "kg");
        data.put("层", "");
        data.put("站", "");
        return data;
    }
}
