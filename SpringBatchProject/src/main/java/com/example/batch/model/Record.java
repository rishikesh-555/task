package com.example.batch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_table") // Update with your actual table name
@Data
public class Record implements Serializable {

    @Id
    @Column(name = "CUST_ID_NO")
    private String custIdNo;

    @Column(name = "ACCT_NO")
    private long acctNo;

    @Column(name = "NPA")
    private String npa;

    @Column(name = "NXX")
    private String nxx;

    @Column(name = "TLN")
    private String tln;

    @Column(name = "BL_PROD_ID")
    private long blProdId;

    @Column(name = "DELETE_IND")
    private String deleteInd;

    @Column(name = "ADMIN_CRT_TMSTAMP")
    private LocalDateTime adminCrtTmstamp;

    @Column(name = "BL_GRP_NO")
    private long blGrpNo;

    @Column(name = "MTN_EFF_DT")
    private LocalDate mtnEffDt;

    @Column(name = "ADMIN_EFF_DT")
    private LocalDate adminEffDt;

    @Column(name = "ADMIN_CHG_AMT")
    private Double adminChgAmt;

    @Column(name = "BL_PER_FROM_DT")
    private LocalDate blPerFromDt;

    @Column(name = "BL_PER_TO_DT")
    private LocalDate blPerToDt;

    @Column(name = "ADMIN_FEE_RSN_CD")
    private String adminFeeRsnCd;

    @Column(name = "DISCNT_OFFR_ID")
    private long discntOffrId;

    @Column(name = "VISION_USER_ID_CD")
    private String visionUserIdCd;

    @Column(name = "ORIG_ADMIN_TMSTAMP")
    private LocalDateTime origAdminTmstamp;

    @Column(name = "ORIG_INVOICE_NO")
    private long origInvoiceNo;

    @Column(name = "CUST_DISC_IND")
    private String custDiscInd;

    @Column(name = "CNTRCT_TERMS_ID")
    private int cntrctTermsId;

    @Column(name = "CREDIT_ADJ_CD")
    private String creditAdjCd;

    @Column(name = "ADMIN_FEE_TYP")
    private String adminFeeTyp;

    @Column(name = "ADMIN_FEE_TYP_ID")
    private long adminFeeTypId;

    @Column(name = "ORIG_TBL_SUBSYS_CD")
    private String origTblSubsysCd;

    @Column(name = "CHRG_CAT_CD")
    private String chrgCatCd;

    @Column(name = "CSEQ_IND")
    private String cseqInd;

    @Column(name = "DB_USERID")
    private String dbUserid;

    @Column(name = "DB_TMSTAMP")
    private LocalDateTime dbTmstamp;

    @Column(name = "SOURCE_CLIENT_ID")
    private String sourceClientId;

    @Column(name = "ADMIN_CRT_METH_CD")
    private String adminCrtMethCd;

    @Column(name = "EQ_ORD_NO")
    private long eqOrdNo;

    @Column(name = "NETACE_LOC_ID")
    private String netaceLocId;

    @Column(name = "SVC_PROD_ID_DISCNT")
    private long svcProdIdDiscnt;

    @Column(name = "BL_CYC_NO")
    private String blCycNo;

    @Column(name = "CYC_MTH_YR")
    private String cycMthYr;

    @Column(name = "TAXABLE_MNY")
    private Double taxableMny;

    @Column(name = "TAX_PROD_ID")
    private long taxProdId;

    @Column(name = "OTC_TYPE")
    private String otcType;

    @Column(name = "CHGBCK_SUBMISSION_ID")
    private String chgbckSubmissionId;

    @Column(name = "TAX_GEO_CODE")
    private String taxGeoCode;

    @Column(name = "VODA_COUNTRY_CD")
    private String vodaCountryCd;

    @Column(name = "DATA_RT_FTPRNT_NO")
    private int dataRtFtpRntNo;

    @Column(name = "AUDIT_TRANS_ID")
    private String auditTransId;

    @Column(name = "ORIG_CREATE_TS")
    private LocalDateTime origCreateTs;

    @Column(name = "INSTALL_LOAN_NO")
    private int installLoanNo;

    @Column(name = "V2_USER_ID")
    private String v2UserId;

    @Column(name = "V2_UPDATE_DTM")
    private LocalDate v2UpdateDtm;

    @Column(name = "INSTALL_FIN_MARKET_ID")
    private String installFinMarketId;

    @Column(name = "LOAN_TERM_MTH_QTY")
    private int loanTermMthQty;

    @Column(name = "TERM_BILLED_QTY")
    private int termBilledQty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        return acctNo == record.acctNo &&
                blProdId == record.blProdId &&
                blGrpNo == record.blGrpNo &&
                discntOffrId == record.discntOffrId &&
                origInvoiceNo == record.origInvoiceNo &&
                cntrctTermsId == record.cntrctTermsId &&
                adminFeeTypId == record.adminFeeTypId &&
                eqOrdNo == record.eqOrdNo &&
                svcProdIdDiscnt == record.svcProdIdDiscnt &&
                taxProdId == record.taxProdId &&
                dataRtFtpRntNo == record.dataRtFtpRntNo &&
                installLoanNo == record.installLoanNo &&
                loanTermMthQty == record.loanTermMthQty &&
                termBilledQty == record.termBilledQty &&
                custIdNo.equals(record.custIdNo) &&
                npa.equals(record.npa) &&
                nxx.equals(record.nxx) &&
                tln.equals(record.tln) &&
                deleteInd.equals(record.deleteInd) &&
                adminCrtTmstamp.equals(record.adminCrtTmstamp) &&
                mtnEffDt.equals(record.mtnEffDt) &&
                adminEffDt.equals(record.adminEffDt) &&
                adminChgAmt.equals(record.adminChgAmt) &&
                blPerFromDt.equals(record.blPerFromDt) &&
                blPerToDt.equals(record.blPerToDt) &&
                adminFeeRsnCd.equals(record.adminFeeRsnCd) &&
                creditAdjCd.equals(record.creditAdjCd) &&
                adminFeeTyp.equals(record.adminFeeTyp) &&
                origTblSubsysCd.equals(record.origTblSubsysCd) &&
                chrgCatCd.equals(record.chrgCatCd) &&
                cseqInd.equals(record.cseqInd) &&
                dbUserid.equals(record.dbUserid) &&
                dbTmstamp.equals(record.dbTmstamp) &&
                sourceClientId.equals(record.sourceClientId) &&
                adminCrtMethCd.equals(record.adminCrtMethCd) &&
                netaceLocId.equals(record.netaceLocId) &&
                blCycNo.equals(record.blCycNo) &&
                cycMthYr.equals(record.cycMthYr) &&
                taxableMny.equals(record.taxableMny) &&
                otcType.equals(record.otcType) &&
                chgbckSubmissionId.equals(record.chgbckSubmissionId) &&
                taxGeoCode.equals(record.taxGeoCode) &&
                vodaCountryCd.equals(record.vodaCountryCd) &&
                auditTransId.equals(record.auditTransId) &&
                origCreateTs.equals(record.origCreateTs) &&
                v2UserId.equals(record.v2UserId) &&
                v2UpdateDtm.equals(record.v2UpdateDtm) &&
                installFinMarketId.equals(record.installFinMarketId);

    }

}
