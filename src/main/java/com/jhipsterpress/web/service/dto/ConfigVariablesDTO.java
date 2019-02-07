package com.jhipsterpress.web.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConfigVariables entity.
 */
public class ConfigVariablesDTO implements Serializable {

    private Long id;

    private Long configVarLong1;

    private Long configVarLong2;

    private Long configVarLong3;

    private Long configVarLong4;

    private Long configVarLong5;

    private Long configVarLong6;

    private Long configVarLong7;

    private Long configVarLong8;

    private Long configVarLong9;

    private Long configVarLong10;

    private Long configVarLong11;

    private Long configVarLong12;

    private Long configVarLong13;

    private Long configVarLong14;

    private Long configVarLong15;

    private Boolean configVarBoolean16;

    private Boolean configVarBoolean17;

    private Boolean configVarBoolean18;

    private String configVarString19;

    private String configVarString20;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConfigVarLong1() {
        return configVarLong1;
    }

    public void setConfigVarLong1(Long configVarLong1) {
        this.configVarLong1 = configVarLong1;
    }

    public Long getConfigVarLong2() {
        return configVarLong2;
    }

    public void setConfigVarLong2(Long configVarLong2) {
        this.configVarLong2 = configVarLong2;
    }

    public Long getConfigVarLong3() {
        return configVarLong3;
    }

    public void setConfigVarLong3(Long configVarLong3) {
        this.configVarLong3 = configVarLong3;
    }

    public Long getConfigVarLong4() {
        return configVarLong4;
    }

    public void setConfigVarLong4(Long configVarLong4) {
        this.configVarLong4 = configVarLong4;
    }

    public Long getConfigVarLong5() {
        return configVarLong5;
    }

    public void setConfigVarLong5(Long configVarLong5) {
        this.configVarLong5 = configVarLong5;
    }

    public Long getConfigVarLong6() {
        return configVarLong6;
    }

    public void setConfigVarLong6(Long configVarLong6) {
        this.configVarLong6 = configVarLong6;
    }

    public Long getConfigVarLong7() {
        return configVarLong7;
    }

    public void setConfigVarLong7(Long configVarLong7) {
        this.configVarLong7 = configVarLong7;
    }

    public Long getConfigVarLong8() {
        return configVarLong8;
    }

    public void setConfigVarLong8(Long configVarLong8) {
        this.configVarLong8 = configVarLong8;
    }

    public Long getConfigVarLong9() {
        return configVarLong9;
    }

    public void setConfigVarLong9(Long configVarLong9) {
        this.configVarLong9 = configVarLong9;
    }

    public Long getConfigVarLong10() {
        return configVarLong10;
    }

    public void setConfigVarLong10(Long configVarLong10) {
        this.configVarLong10 = configVarLong10;
    }

    public Long getConfigVarLong11() {
        return configVarLong11;
    }

    public void setConfigVarLong11(Long configVarLong11) {
        this.configVarLong11 = configVarLong11;
    }

    public Long getConfigVarLong12() {
        return configVarLong12;
    }

    public void setConfigVarLong12(Long configVarLong12) {
        this.configVarLong12 = configVarLong12;
    }

    public Long getConfigVarLong13() {
        return configVarLong13;
    }

    public void setConfigVarLong13(Long configVarLong13) {
        this.configVarLong13 = configVarLong13;
    }

    public Long getConfigVarLong14() {
        return configVarLong14;
    }

    public void setConfigVarLong14(Long configVarLong14) {
        this.configVarLong14 = configVarLong14;
    }

    public Long getConfigVarLong15() {
        return configVarLong15;
    }

    public void setConfigVarLong15(Long configVarLong15) {
        this.configVarLong15 = configVarLong15;
    }

    public Boolean isConfigVarBoolean16() {
        return configVarBoolean16;
    }

    public void setConfigVarBoolean16(Boolean configVarBoolean16) {
        this.configVarBoolean16 = configVarBoolean16;
    }

    public Boolean isConfigVarBoolean17() {
        return configVarBoolean17;
    }

    public void setConfigVarBoolean17(Boolean configVarBoolean17) {
        this.configVarBoolean17 = configVarBoolean17;
    }

    public Boolean isConfigVarBoolean18() {
        return configVarBoolean18;
    }

    public void setConfigVarBoolean18(Boolean configVarBoolean18) {
        this.configVarBoolean18 = configVarBoolean18;
    }

    public String getConfigVarString19() {
        return configVarString19;
    }

    public void setConfigVarString19(String configVarString19) {
        this.configVarString19 = configVarString19;
    }

    public String getConfigVarString20() {
        return configVarString20;
    }

    public void setConfigVarString20(String configVarString20) {
        this.configVarString20 = configVarString20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfigVariablesDTO configVariablesDTO = (ConfigVariablesDTO) o;
        if (configVariablesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configVariablesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfigVariablesDTO{" +
            "id=" + getId() +
            ", configVarLong1=" + getConfigVarLong1() +
            ", configVarLong2=" + getConfigVarLong2() +
            ", configVarLong3=" + getConfigVarLong3() +
            ", configVarLong4=" + getConfigVarLong4() +
            ", configVarLong5=" + getConfigVarLong5() +
            ", configVarLong6=" + getConfigVarLong6() +
            ", configVarLong7=" + getConfigVarLong7() +
            ", configVarLong8=" + getConfigVarLong8() +
            ", configVarLong9=" + getConfigVarLong9() +
            ", configVarLong10=" + getConfigVarLong10() +
            ", configVarLong11=" + getConfigVarLong11() +
            ", configVarLong12=" + getConfigVarLong12() +
            ", configVarLong13=" + getConfigVarLong13() +
            ", configVarLong14=" + getConfigVarLong14() +
            ", configVarLong15=" + getConfigVarLong15() +
            ", configVarBoolean16='" + isConfigVarBoolean16() + "'" +
            ", configVarBoolean17='" + isConfigVarBoolean17() + "'" +
            ", configVarBoolean18='" + isConfigVarBoolean18() + "'" +
            ", configVarString19='" + getConfigVarString19() + "'" +
            ", configVarString20='" + getConfigVarString20() + "'" +
            "}";
    }
}
