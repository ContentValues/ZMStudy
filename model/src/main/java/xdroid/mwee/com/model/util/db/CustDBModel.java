package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbcust")
public class CustDBModel extends DBModel {
    @ColumnInf(name = "fsTelCt")
    public String fsTelCt = "";
    @ColumnInf(name = "fsExpArea")
    public String fsExpArea = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsProvinceId_Cp")
    public String fsProvinceId_Cp = "";
    @ColumnInf(name = "fsBusiScope")
    public String fsBusiScope = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fsFaxCp")
    public String fsFaxCp = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsTradeId")
    public String fsTradeId = "";
    @ColumnInf(name = "fsTelPl")
    public String fsTelPl = "";
    @ColumnInf(name = "fsEmailCt")
    public String fsEmailCt = "";
    @ColumnInf(name = "fsEmailPl")
    public String fsEmailPl = "";
    @ColumnInf(name = "fsPostal_Pl")
    public String fsPostal_Pl = "";
    @ColumnInf(name = "fsTelCp")
    public String fsTelCp = "";
    @ColumnInf(name = "fsExecutive")
    public String fsExecutive = "";
    @ColumnInf(name = "fiCardSte")
    public int fiCardSte = 0;
    @ColumnInf(name = "fsCustSurId")
    public String fsCustSurId = "";
    @ColumnInf(name = "fsSex")
    public String fsSex = "";
    @ColumnInf(name = "fsCompName")
    public String fsCompName = "";
    @ColumnInf(name = "fsDistrictId_Cp")
    public String fsDistrictId_Cp = "";
    @ColumnInf(name = "fsCustTypeId")
    public String fsCustTypeId = "";
    @ColumnInf(name = "fsPid")
    public String fsPid = "";
    @ColumnInf(name = "fsDistrictId_Pl")
    public String fsDistrictId_Pl = "";
    @ColumnInf(name = "fiIsSms")
    public int fiIsSms = 0;
    @ColumnInf(name = "fsAddrPl")
    public String fsAddrPl = "";
    @ColumnInf(name = "fsJobTitle")
    public String fsJobTitle = "";
    @ColumnInf(name = "fsCellphoneCt")
    public String fsCellphoneCt = "";
    @ColumnInf(name = "fsAddrCp")
    public String fsAddrCp = "";
    @ColumnInf(name = "fiIsVIP")
    public int fiIsVIP = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsCellphonePl")
    public String fsCellphonePl = "";
    @ColumnInf(name = "fsCustEngName")
    public String fsCustEngName = "";
    @ColumnInf(name = "fsProvinceId_Pl")
    public String fsProvinceId_Pl = "";
    @ColumnInf(name = "fsCardNo")
    public String fsCardNo = "";
    @ColumnInf(name = "fsEmailCp")
    public String fsEmailCp = "";
    @ColumnInf(name = "fsPostal_Cp")
    public String fsPostal_Cp = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "fsTelCpExt")
    public String fsTelCpExt = "";
    @ColumnInf(name = "fsCompEngName")
    public String fsCompEngName = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsCardFreezeDesc")
    public String fsCardFreezeDesc = "";
    @ColumnInf(name = "fsHobby")
    public String fsHobby = "";
    @ColumnInf(name = "fsBirthDate")
    public String fsBirthDate = "";
    @ColumnInf(name = "fsCustName")
    public String fsCustName = "";
    @ColumnInf(name = "fsJobTitleCt")
    public String fsJobTitleCt = "";
    @ColumnInf(name = "fiExpTag")
    public int fiExpTag = 0;
    @ColumnInf(name = "fsCelebrateDate")
    public String fsCelebrateDate = "";
    @ColumnInf(name = "fsVIPLvlId")
    public String fsVIPLvlId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsCustSteId")
    public String fsCustSteId = "";
    @ColumnInf(name = "fiPoint")
    public int fiPoint = 0;
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCustCls")
    public String fsCustCls = "";
    @ColumnInf(name = "fsCompanyGUID")
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsCustId")
    public String fsCustId = "";
    @ColumnInf(name = "fsContact")
    public String fsContact = "";
    @ColumnInf(name = "fsExpendDate")
    public String fsExpendDate = "";
    @ColumnInf(name = "fsCustGUID", primaryKey = true)
    public String fsCustGUID = "";
    @ColumnInf(name = "fsCityId_Cp")
    public String fsCityId_Cp = "";
    @ColumnInf(name = "fsCityId_Pl")
    public String fsCityId_Pl = "";
    @ColumnInf(name = "fsCareful")
    public String fsCareful = "";

    public CustDBModel() {

    }

    @Override
    public CustDBModel clone() {
        CustDBModel cloneObj = null;
        try {
            cloneObj = (CustDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}