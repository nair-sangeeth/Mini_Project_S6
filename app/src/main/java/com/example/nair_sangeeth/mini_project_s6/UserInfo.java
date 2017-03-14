package com.example.nair_sangeeth.mini_project_s6;

/**
 * Created by nair_sangeeth on 3/8/17.
 */

public class UserInfo {

    public String BLastName;
    public String FName;
    public String Brand;
    public String RegNum;
    public String HardID;
    public String Email;

    public void VehicleInfo(String brand, String regNum, String hardID) {
        Brand = brand;
        RegNum = regNum;
        HardID = hardID;
    }


    UserInfo(){

    }

    public void UserDetails(String BLastName, String FName,String Email) {
        this.BLastName = BLastName;
        this.Email = Email;
        this.FName = FName;
    }

}
