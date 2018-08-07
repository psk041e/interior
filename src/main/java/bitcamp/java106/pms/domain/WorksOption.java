package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class WorksOption implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int optionNumber; // 옵션번호
    private int worksNumber;  // 작품번호
    private String optionYn; // 옵션 여부
    private String attributeValue; // 속성값
    private String attributeName; // 속성명
    
    
    
    @Override
    public String toString() {
        return "WorksOption [optionNumber=" + optionNumber + ", worksNumber="
                + worksNumber + ", optionYn=" + optionYn + ", attributeValue="
                + attributeValue + ", attributeName=" + attributeName + "]";
    }

    public int getWorksNumber() {
        return worksNumber;
    }

    public void setWorksNumber(int worksNumber) {
        this.worksNumber = worksNumber;
    }

    public String getOptionYn() {
        return optionYn;
    }

    public void setOptionYn(String optionYn) {
        this.optionYn = optionYn;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    
}