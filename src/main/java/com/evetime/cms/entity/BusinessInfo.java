package com.evetime.cms.entity;

/**
 * Created by Rains
 * 商家信息
 * on 2016-07-25.
 */
public class BusinessInfo {

    private String id;                                      //主键
    private String name;                                // 联系人姓名
    private String phone;                               //联系人电话
    private String email;                                 //联系人常用邮箱


    private String businessShortName;           //商户简称
    private String category;                            //经营类目
    private String bigCategory;                       //大类
    private String smallCategory;                    //小类
    private String serviceTel;                           //服务电话
    private String website;                              //公司网站

    private String appendFile;                          //附件文件


    private String businessFullName;            //商户全称
    private String registAddress;                   //注册地址
    private String registCode;                       //注册号
    private String businessScope;                   //经营范围
    private String businessLicenseStartTime;               //营业执照办理时间
    private String businessLicenseEndTime;                 //营业执照截止时间

    private String businessLicense;                 //营业执照

    private String idCardFrontPage;                 //身份证正面照
    private String idCardBackPage;                  //身份证反面照
    private String certificateHolderType;           //证件持有人类型
    private String certificateHolderName;        //证件持有人姓名
    private String certificateType;                     //证件类型

    private String certificateStartTime;                //证件有效期: 开始时间
    private String certificateEndTime;              //证件有效期  : 截止时间
    private String certificateNo;                        //证件号码

    private String accountType;              //法人账户
    private String accountName;                         //开户名称
    private String accountBank;                         //开户银行
    private String accountBankCity;                 //开户行城市
    private String accountBranchBank;           //开户支行
    private String accountNo;                        //银行账号
    private String businessRate;                    //商户费率

    private String contractPhoto;                   //合同图片
    private String contractFile;                        //合同文件


    private String contractNo;                         //合同编号
    private String contractStartTime;            //签约时间
    private String contractEndTime;               //到期时间
    private String evetimeContractImage;                    //每时合约图片

    private String contractStatus;                     //合约状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessShortName() {
        return businessShortName;
    }

    public void setBusinessShortName(String businessShortName) {
        this.businessShortName = businessShortName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(String bigCategory) {
        this.bigCategory = bigCategory;
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(String smallCategory) {
        this.smallCategory = smallCategory;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusinessFullName() {
        return businessFullName;
    }

    public void setBusinessFullName(String businessFullName) {
        this.businessFullName = businessFullName;
    }

    public String getRegistAddress() {
        return registAddress;
    }

    public void setRegistAddress(String registAddress) {
        this.registAddress = registAddress;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessLicenseStartTime() {
        return businessLicenseStartTime;
    }

    public void setBusinessLicenseStartTime(String businessLicenseStartTime) {
        this.businessLicenseStartTime = businessLicenseStartTime;
    }

    public String getBusinessLicenseEndTime() {
        return businessLicenseEndTime;
    }

    public void setBusinessLicenseEndTime(String businessLicenseEndTime) {
        this.businessLicenseEndTime = businessLicenseEndTime;
    }

    public String getCertificateHolderType() {
        return certificateHolderType;
    }

    public void setCertificateHolderType(String certificateHolderType) {
        this.certificateHolderType = certificateHolderType;
    }

    public String getCertificateHolderName() {
        return certificateHolderName;
    }

    public void setCertificateHolderName(String certificateHolderName) {
        this.certificateHolderName = certificateHolderName;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateStartTime() {
        return certificateStartTime;
    }

    public void setCertificateStartTime(String certificateStartTime) {
        this.certificateStartTime = certificateStartTime;
    }

    public String getCertificateEndTime() {
        return certificateEndTime;
    }

    public void setCertificateEndTime(String certificateEndTime) {
        this.certificateEndTime = certificateEndTime;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountBankCity() {
        return accountBankCity;
    }

    public void setAccountBankCity(String accountBankCity) {
        this.accountBankCity = accountBankCity;
    }

    public String getAccountBranchBank() {
        return accountBranchBank;
    }

    public void setAccountBranchBank(String accountBranchBank) {
        this.accountBranchBank = accountBranchBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBusinessRate() {
        return businessRate;
    }

    public void setBusinessRate(String businessRate) {
        this.businessRate = businessRate;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(String contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public String getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIdCardFrontPage() {
        return idCardFrontPage;
    }

    public void setIdCardFrontPage(String idCardFrontPage) {
        this.idCardFrontPage = idCardFrontPage;
    }

    public String getIdCardBackPage() {
        return idCardBackPage;
    }

    public void setIdCardBackPage(String idCardBackPage) {
        this.idCardBackPage = idCardBackPage;
    }

    public String getContractPhoto() {
        return contractPhoto;
    }

    public void setContractPhoto(String contractPhoto) {
        this.contractPhoto = contractPhoto;
    }

    public String getEvetimeContractImage() {
        return evetimeContractImage;
    }

    public void setEvetimeContractImage(String evetimeContractImage) {
        this.evetimeContractImage = evetimeContractImage;
    }

    public String getAppendFile() {
        return appendFile;
    }

    public void setAppendFile(String appendFile) {
        this.appendFile = appendFile;
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", businessShortName='" + businessShortName + '\'' +
                ", category='" + category + '\'' +
                ", bigCategory='" + bigCategory + '\'' +
                ", smallCategory='" + smallCategory + '\'' +
                ", serviceTel='" + serviceTel + '\'' +
                ", website='" + website + '\'' +
                ", businessFullName='" + businessFullName + '\'' +
                ", registAddress='" + registAddress + '\'' +
                ", registCode='" + registCode + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", businessLicenseStartTime='" + businessLicenseStartTime + '\'' +
                ", businessLicenseEndTime='" + businessLicenseEndTime + '\'' +
                ", certificateHolderType='" + certificateHolderType + '\'' +
                ", certificateHolderName='" + certificateHolderName + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateStartTime='" + certificateStartTime + '\'' +
                ", certificateEndTime='" + certificateEndTime + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountBank='" + accountBank + '\'' +
                ", accountBankCity='" + accountBankCity + '\'' +
                ", accountBranchBank='" + accountBranchBank + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", businessRate='" + businessRate + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractStartTime='" + contractStartTime + '\'' +
                ", contractEndTime='" + contractEndTime + '\'' +
                ", contractImage='" + evetimeContractImage + '\'' +
                '}';
    }
}
