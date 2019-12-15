package com.example.project1213;

import org.litepal.crud.LitePalSupport;

public class Record extends LitePalSupport {

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
}
