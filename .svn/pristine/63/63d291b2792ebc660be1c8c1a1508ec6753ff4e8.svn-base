package com.wondersgroup.special.archive;

import com.wondersgroup.special.entity.UnitApprovalEntity;
import com.wondersgroup.special.entity.UnitPermissionEnitity;

import java.util.LinkedHashMap;

/**
 * Created by root on 12/12/16.
 * 单位许可
 */

public class EntPermitInfo {
    /**
     * 检验检测机构许可基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkEnt() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("许可证编号", "");
        data.put("鉴定评审机构代码", "");
        data.put("鉴定评审机构", "");
        data.put("审批机关", "");
        data.put("发证机关", "");
        data.put("发证日期", "");
        data.put("有效日期", "");
        data.put("变更日期", "");
        data.put("备注", "");
        data.put("持证状态", "");
        data.put("受理日期", "");
        data.put("检验地址", "");
        return data;
    }

    /**
     * 生产单位许可基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> productEnt() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("许可证编号", "");
        data.put("鉴定评审机构代码", "");
        data.put("鉴定评审机构", "");
        data.put("审批机关", "");
        data.put("发证机关", "");
        data.put("发证日期", "");
        data.put("有效日期", "");
        data.put("变更日期", "");
        data.put("备注", "");
        data.put("证件类型", "");
        data.put("设备种类", "");
        data.put("业务审批通过日期", "");
        data.put("生产地址", "");
        return data;
    }

    /**
     * 检验检测机构许可核准项目列表
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkEntList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("作废状态", "");
        data.put("限制条件", "");
        data.put("许可项目", "");
        data.put("许可级别", "");
        data.put("设备类别", "");
        data.put("备注", "");
        return data;
    }

    /**
     * 生产单位许可核准项目列表
     *
     * @return
     */
    public static LinkedHashMap<String, String> productEntList(UnitApprovalEntity result) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("作废状态", result.getCancelStatus());
        data.put("限制条件", result.getLimit());
        data.put("项目代码", result.getProCode());
        data.put("核准项目", result.getProName());
        data.put("检验分址", result.getTestAssess());
        return data;
    }

    public static LinkedHashMap<String, String> entPermit(UnitPermissionEnitity result) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("许可证编号", result.getCertCode());
        data.put("鉴定评审机构代码", result.getUnitCode());
        data.put("鉴定评审机构", result.getUnitName());
        data.put("审批机关", result.getApproveOrgan());
        data.put("发证机关", result.getGrantGov());
        data.put("发证日期", result.getCertGrantDate());
        data.put("有效日期", result.getCertAvailableDate());
        data.put("变更日期", result.getChangeDate());
        data.put("备注", result.getRemark());
        data.put("证件类型", result.getCertType());
        data.put("设备种类", result.getDeviceType());
        data.put("业务审批通过日期", result.getPassDate());
        data.put("生产地址", result.getProductAddress());
        data.put("持证状态", result.getCertStatus());
        data.put("受理日期", result.getAcceptDate());
        data.put("检验地址", result.getCheckAddress());
        return data;
    }
}
