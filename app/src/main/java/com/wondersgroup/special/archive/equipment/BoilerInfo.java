package com.wondersgroup.special.archive.equipment;

import java.util.LinkedHashMap;

/**
 * Created by chan on 12/8/16.
 * 锅炉设备
 */

public class BoilerInfo {
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
        data.put("出厂编号", "");
        data.put("制造日期", "");
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
        data.put("产权单位法人代表", "");
        data.put("产权所有者地址", "");
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
        data.put("单位内部编号", "");
        data.put("设备监控级别", "");
        data.put("设备启用日期", "");
        data.put("设备停用日期", "");
        data.put("使用单位地址", "");
        data.put("使用单位名称", "");
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
        data.put("产品合格证编号", "");
        data.put("设计使用年限", "");
        data.put("产品图号", "");
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
     * 技术参数-基本参数
     *
     * @return
     */
    public static LinkedHashMap<String, String> tecBase() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("额定出力", "" + "t/h");
        data.put("额定功率", "" + "MW");
        data.put("许可使用压力", "" + "MPa");
        data.put("锅炉结构形式", "");
        data.put("加热方式", "");
        data.put("燃料种类", "");
        data.put("燃烧方式", "");
        data.put("锅炉用途", "");
        data.put("使用状态", "");
        data.put("水处理方式", "");
        data.put("除氧方式", "");
        data.put("消烟除尘方式", "");
        data.put("新旧状态", "");
        data.put("出渣方式", "");
        data.put("锅炉房类别", "");
        data.put("介质出口温度", "" + "℃");
        data.put("单位司炉数量", "" + "人");
        data.put("水质人员数量", "" + "人");
        data.put("能效测试日期", "");
        data.put("能效测试机构", "");
        data.put("排烟温度tpy", "" + "℃");
        data.put("过量空气系数apy", "");
        data.put("介质", "");
        data.put("热效率", "");
        data.put("能效测试机构代码", "");
        data.put("能效测试类型", "");
        data.put("测试报告编号", "");
        data.put("测试人员", "");
        return data;
    }

    /**
     * 技术参数-电站锅炉扩展参数
     *
     * @return
     */
    public static LinkedHashMap<String, String> tecExpa() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("燃烧器布置方式", "");
        data.put("水循环方式", "");
        data.put("再热蒸汽调温方式", "");
        data.put("过热蒸汽调温方式", "");
        data.put("汽水分离装置", "");
        data.put("最大连续蒸发量", "" + "t/h");
        data.put("再热蒸汽流量", "" + "t/h");
        data.put("锅筒工作压力", "" + "MPa");
        data.put("过热器出口压力", "" + "MPa");
        data.put("再热器入口压力", "" + "MPa");
        data.put("再热器出口压力", "" + "MPa");
        data.put("给水温度", "" + "℃");
        data.put("过热器出口温度", "" + "℃");
        data.put("再热器入口温度", "" + "℃");
        data.put("再热器出口温度", "" + "℃");
        data.put("直流锅炉启动压力", "" + "MPa");
        data.put("直流锅炉启动流量", "" + "t/h");
        data.put("设计燃料应用基低位发热值", "" + "大卡");
        data.put("再热器调温方式", "" + "℃");
        data.put("锅炉汽水分离方式", "" + "℃");
        return data;
    }

}
