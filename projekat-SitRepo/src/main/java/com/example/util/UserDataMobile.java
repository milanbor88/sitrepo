package com.example.util;

import com.example.dto.*;

public class UserDataMobile {

    private ApiResult apiResult;

    private UserDTO user;

    private VoziloDTO vozilo;

    private PrikolicaDTO prikolica;

    private OsiguranjeDTO osiguranje;

    private UgovaracOsiguranjaDTO ugovaracOsiguranja;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public VoziloDTO getVozilo() {
        return vozilo;
    }

    public void setVozilo(VoziloDTO vozilo) {
        this.vozilo = vozilo;
    }

    public PrikolicaDTO getPrikolica() {
        return prikolica;
    }

    public void setPrikolica(PrikolicaDTO prikolica) {
        this.prikolica = prikolica;
    }

    public OsiguranjeDTO getOsiguranje() {
        return osiguranje;
    }

    public void setOsiguranje(OsiguranjeDTO osiguranje) {
        this.osiguranje = osiguranje;
    }

    public UgovaracOsiguranjaDTO getUgovaracOsiguranja() {
        return ugovaracOsiguranja;
    }

    public void setUgovaracOsiguranja(UgovaracOsiguranjaDTO ugovaracOsiguranja) {
        this.ugovaracOsiguranja = ugovaracOsiguranja;
    }


    public ApiResult getApiResult() {
        return apiResult;
    }

    public void setApiResult(ApiResult apiResult) {
        this.apiResult = apiResult;
    }

}
