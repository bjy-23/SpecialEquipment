package com.wondersgroup.special.archive;

import java.util.LinkedHashMap;

/**
 * Created by chan on 12/8/16.
 * <p>
 * 档案其他信息
 */

public class ArchiveInfo {
    /**
     * 隐患情况
     *
     * @return
     */
    public static LinkedHashMap<String, String> hiddenInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("使用单位", "");
        data.put("危害程度等级", "");
        data.put("处理费用", "");
        data.put("隐患发现日期", "");
        data.put("隐患处理日期", "");
        data.put("隐患类别", "");
        data.put("隐患原因", "");
        data.put("隐患描述", "");
        data.put("危害类别", "");
        data.put("处理意见", "");
        data.put("处理结果", "");
        data.put("备注", "");
        data.put("上传机构", "");
        data.put("上传时间", "");
        data.put("上传使用单位", "");
        data.put("上传唯一性编号", "");
        data.put("数据来源", "");
        data.put("来源类型", "");
        data.put("设备名称", "");
        data.put("设备地址", "");
        data.put("检验人员", "");
        data.put("整改截止日期", "");
        data.put("检验性质", "");
        data.put("检验结论", "");
        data.put("使用单位联系人", "");
        data.put("使用单位联系电话", "");
        data.put("使用单位收到日期", "");
        data.put("检验单位联系人", "");
        data.put("检验单位联系电话", "");
        return data;
    }

    /**
     * 事故信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> accidentInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("事故单位", "");
        data.put("事故单位组织机构代码", "");
        data.put("发生时间", "");
        data.put("上报时间", "");
        data.put("行业", "");
        data.put("接报时间", "");
        data.put("所属地区", "");
        data.put("信息来源", "");
        data.put("事故地点", "");
        data.put("设备种类", "");
        data.put("死亡人数", "");
        data.put("重伤人数", "");
        data.put("轻伤人数", "");
        data.put("直接经济损失", "");
        data.put("间接经济损失", "");
        data.put("事故现象", "");
        data.put("责任环节", "");
        data.put("案发环节", "");
        data.put("事故主要原因", "");
        data.put("事故具体原因", "");
        data.put("是否特种设备事故", "");
        data.put("经济类型", "");
        data.put("结案日期", "");
        data.put("事故类别", "");
        data.put("事故简要经过描述", "");
        data.put("事故现场破坏描述", "");
        data.put("设备代码", "");
        data.put("设备其他相关信息", "");
        data.put("备注", "");
        data.put("调查组负责人", "");
        data.put("调查组不同意见成员", "");
        data.put("调查组部门负责人", "");
        data.put("结案单位", "");
        return data;
    }

    /**
     * 案件信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> caseInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("主键", "");
        data.put("立案编号", "");
        data.put("案件标题", "");
        data.put("执法单位名称", "");
        data.put("案件来源", "");
        data.put("案由名称", "");
        data.put("当事人类型", "");
        data.put("处罚对象类型", "");
        data.put("案发单位", "");
        data.put("组织机构代码", "");
        data.put("12365信息主键", "");
        data.put("移送类型", "");
        data.put("打假经费", "");
        data.put("案件承办人", "");
        data.put("立案审批表里的经办人意见", "");
        data.put("立案审批表的审批意见", "");
        data.put("案件状态", "");
        data.put("当前操作人", "");
        data.put("办理部门名称", "");
        data.put("案情简介", "");
        data.put("是否整治及整治类型", "");
        data.put("出动执法人员次数", "");
        data.put("法人代表", "");
        data.put("案件承办人名称串", "");
        data.put("涉案金额", "");
        data.put("查获货值", "");
        data.put("产品分类", "");
        data.put("查处环节", "");
        data.put("违法业务大类", "");
        data.put("违法类型明细", "");
        data.put("处理结果", "");
        data.put("结案日期", "");
        data.put("结案类型", "");
        data.put("直属单位/区县", "");
        data.put("备注", "");
        data.put("产品类别名称", "");
        data.put("是否引用法律法规，产品类型/旧案件", "");
        data.put("是否责令整改", "");
        data.put("是否诉讼申诉", "");
        data.put("是否诉讼败诉", "");
        data.put("是否应该听证", "");
        data.put("是否实际听证", "");
        data.put("是否复议", "");
        data.put("复议结果", "");
        data.put("是否强制执行", "");
        data.put("历史案件举报人次", "");
        data.put("产品名称", "");
        data.put("执法类型", "");
        data.put("受理编号(信息编号)", "");
        data.put("举报奖励金额", "");
        data.put("举报人次", "");
        data.put("回复方式", "");
        data.put("罚款金额", "");
        data.put("没收金额", "");
        data.put("引用法律法规", "");
        data.put("是否需要回复", "");
        data.put("是否督办", "");
        data.put("登记时间", "");
        data.put("违反法律法规条款", "");
        data.put("处罚法律法规条款", "");
        data.put("处罚内容", "");
        data.put("移送源案件信息ID", "");
        data.put("企业类型", "");
        data.put("违法食品类型", "");
        data.put("立案时间", "");
        data.put("调查终结日期", "");
        data.put("处罚决定书送达时间", "");
        data.put("处罚决定书编号", "");
        data.put("办理部门ID", "");
        data.put("违法产品数量", "");
        data.put("违法产品数量单位", "");
        data.put("违法产品计量", "");
        data.put("违法产品计量单位", "");
        data.put("违法产品货值", "");
        data.put("扣押产品数量", "");
        data.put("扣押产品数量单位", "");
        data.put("扣押产品计量", "");
        data.put("扣押产品计量单位", "");
        data.put("扣押产品货值", "");
        data.put("没收产品数量", "");
        data.put("没收产品数量单位", "");
        data.put("没收产品计量", "");
        data.put("没收产品计量单位", "");
        data.put("没收产品货值", "");
        data.put("办理最后期限的时间", "");
        data.put("告知时间", "");
        return data;
    }

    /**
     * 企业获奖记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> awardInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("内码", "");
        data.put("录入表编号", "");
        data.put("填报年", "");
        data.put("主管部门", "");
        data.put("成果名称", "");
        data.put("填报时间", "");
        data.put("机构内码", "");
        data.put("组织机构代码", "");
        data.put("单位名称", "");
        data.put("法人代表", "");
        data.put("单位地址", "");
        data.put("电话", "");
        data.put("项目名称", "");
        data.put("项目组长", "");
        data.put("联系人", "");
        data.put("联系电话", "");
        data.put("传真", "");
        data.put("获奖情况", "");
        data.put("攻关前12个月销售额", "");
        data.put("攻关前12个月利润", "");
        data.put("攻关前12个月节能降耗", "");
        data.put("攻关前12个月创汇节汇", "");
        data.put("攻关前12个月其他", "");
        data.put("攻关后12个月销售额", "");
        data.put("攻关后12个月利润", "");
        data.put("攻关后12个月节能降耗", "");
        data.put("攻关后12个月创汇节汇", "");
        data.put("攻关后12个月其他", "");
        data.put("受理局", "");
        data.put("归档号", "");
        data.put("是否通过", "");
        data.put("是否受理", "");
        return data;
    }

    /**
     * 投举信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> compInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("业务类型号", "");
        data.put("业务受理方式", "");
        data.put("业务受理编号", "");
        data.put("呼叫序号", "");
        data.put("受理日期", "");
        data.put("受理时间", "");
        data.put("受理人账号", "");
        data.put("受理人姓名", "");
        data.put("受理机构", "");
        data.put("客户号", "");
        data.put("客户姓名", "");
        data.put("客户性别", "");
        data.put("客户地址", "");
        data.put("客户单位", "");
        data.put("客户电话", "");
        data.put("客户手机", "");
        data.put("客户邮箱", "");
        data.put("被投诉对象名称", "");
        data.put("被投诉单位机构代码", "");
        data.put("被投诉单位（人）地址", "");
        data.put("被投诉单位所在地", "");
        data.put("被投诉单位（人）联系电话", "");
        data.put("被投诉企业法人代表", "");
        data.put("被投诉企业位当事人", "");
        data.put("被投诉企业类型", "");
        data.put("被反映人单位", "");
        data.put("被反映人性别", "");
        data.put("被反映人职务", "");
        data.put("业务内容", "");
        data.put("内容类型", "");
        data.put("反映问题类型", "");
        data.put("产品类型", "");
        data.put("产品产地", "");
        data.put("发票号码", "");
        data.put("购买日期", "");
        data.put("产品品牌", "");
        data.put("来访数", "");
        data.put("标题", "");
        data.put("回复方式", "");
        data.put("回复号码", "");
        data.put("备注", "");
        data.put("拟办意见", "");
        data.put("领导批示", "");
        data.put("分发类型", "");
        data.put("处理机构/移送部门", "");
        data.put("要求完成日期", "");
        data.put("要求完成时间", "");
        data.put("承办单位", "");
        data.put("承办人", "");
        data.put("联系号码", "");
        data.put("最终完成日", "");
        data.put("最终完成时间", "");
        data.put("延迟办案缘由", "");
        data.put("处理类型", "");
        data.put("处理结果", "");
        data.put("涉及货值/商品价值", "");
        data.put("挽回损失", "");
        data.put("罚款金额", "");
        data.put("举报奖金", "");
        data.put("归档编号", "");
        data.put("归档日期", "");
        data.put("客户满意度", "");
        data.put("客户意见", "");
        data.put("回复日期", "");
        data.put("回复时间", "");
        data.put("备注", "");
        data.put("状态", "");
        return data;
    }

    /**
     * 管理单元
     *
     * @return
     */
    public static LinkedHashMap<String, String> unitInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("单元代码","");
        data.put("单元名称","");
        data.put("父单元名称","");
        data.put("单元坐标","");
        data.put("单元地址","");
        data.put("单元类型","");
        return data;
    }
}
