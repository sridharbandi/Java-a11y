
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bgColor",
    "contrastRatio",
    "expectedContrastRatio",
    "fgColor",
    "fontSize",
    "fontWeight",
    "messageKey",
    "shadowColor"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("bgColor")
    private String bgColor;
    @JsonProperty("contrastRatio")
    private Double contrastRatio;
    @JsonProperty("expectedContrastRatio")
    private String expectedContrastRatio;
    @JsonProperty("fgColor")
    private String fgColor;
    @JsonProperty("fontSize")
    private String fontSize;
    @JsonProperty("fontWeight")
    private String fontWeight;
    @JsonProperty("messageKey")
    private Object messageKey;
    @JsonProperty("shadowColor")
    private Object shadowColor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bgColor")
    public String getBgColor() {
        return bgColor;
    }

    @JsonProperty("bgColor")
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    @JsonProperty("contrastRatio")
    public Double getContrastRatio() {
        return contrastRatio;
    }

    @JsonProperty("contrastRatio")
    public void setContrastRatio(Double contrastRatio) {
        this.contrastRatio = contrastRatio;
    }

    @JsonProperty("expectedContrastRatio")
    public String getExpectedContrastRatio() {
        return expectedContrastRatio;
    }

    @JsonProperty("expectedContrastRatio")
    public void setExpectedContrastRatio(String expectedContrastRatio) {
        this.expectedContrastRatio = expectedContrastRatio;
    }

    @JsonProperty("fgColor")
    public String getFgColor() {
        return fgColor;
    }

    @JsonProperty("fgColor")
    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    @JsonProperty("fontSize")
    public String getFontSize() {
        return fontSize;
    }

    @JsonProperty("fontSize")
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    @JsonProperty("fontWeight")
    public String getFontWeight() {
        return fontWeight;
    }

    @JsonProperty("fontWeight")
    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    @JsonProperty("messageKey")
    public Object getMessageKey() {
        return messageKey;
    }

    @JsonProperty("messageKey")
    public void setMessageKey(Object messageKey) {
        this.messageKey = messageKey;
    }

    @JsonProperty("shadowColor")
    public Object getShadowColor() {
        return shadowColor;
    }

    @JsonProperty("shadowColor")
    public void setShadowColor(Object shadowColor) {
        this.shadowColor = shadowColor;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
