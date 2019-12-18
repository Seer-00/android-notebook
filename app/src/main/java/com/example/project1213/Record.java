package com.example.project1213;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class Record extends LitePalSupport implements Serializable {

    private int id;

    private String recordTitle;

    private String recordText;

    private String recordDate;

    private String recordLocation;

    private byte[] recordImage;

    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public String getRecordLocation() {
        return recordLocation;
    }

    public void setRecordLocation(String recordLocation) {
        this.recordLocation = recordLocation;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public byte[] getRecordImage() {
        return recordImage;
    }

    public void setRecordImage(byte[] recordImage) {
        this.recordImage = recordImage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    /*
    public Account getAccount() {
        //子表中会生成一个关联父表的id供父表查询，且字表中id生成符合规则："父表类名小写_id"
        //若父表为Person类(父表中会自动生成一个id自增列)，子表为User类,则字表中会自动生成字段person_id对应父表中id，以供查询
        String linkId = this.getClass().getSimpleName().toLowerCase();
        List<Account> list= LitePal.where(linkId+"_id=?",String.valueOf(id)).find(Account.class);
        if(CollectionUtil.isEmpty(list)){
            user= null;
        }else{
            user=list.get(0);
        }
        return user;
    }
    */
}
