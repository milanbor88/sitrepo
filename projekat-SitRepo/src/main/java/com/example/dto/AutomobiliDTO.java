package com.example.dto;

import javax.persistence.Version;
import java.io.Serializable;

public class AutomobiliDTO implements Serializable {

    long id;

    String marka;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }


}
