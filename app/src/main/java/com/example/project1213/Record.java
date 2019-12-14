package com.example.project1213;

import org.litepal.crud.LitePalSupport;

public class Record extends LitePalSupport {

    private int id;

    private String recordTitle;

    private String recordText;

    private byte[] recordImage;

    public byte[] getRecordImage() {
        return recordImage;
    }

    public void setRecordImage(byte[] recordImage) {
        this.recordImage = recordImage;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    private String recordDate;

    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
