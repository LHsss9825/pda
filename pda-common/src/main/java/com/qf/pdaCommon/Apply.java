package com.qf.pdaCommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:11
 */

public class Apply {
    private String applyName;
    private String applyTime;
    private String inTime;
    private String type;
    private String unit;
    private String types;
    private int num;
    public Apply() {
    }

    public Apply(String applyName, String applyTime, String inTime, String type, String unit, int num) {
        this.applyName = applyName;
        this.applyTime = applyTime;
        this.inTime = inTime;
        this.type = type;
        this.unit = unit;
        this.num = num;
        this.types=type;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "applyName='" + applyName + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", inTime='" + inTime + '\'' +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", num=" + num +
                '}';
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
