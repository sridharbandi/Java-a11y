
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orientationAngle",
    "orientationType",
    "userAgent",
    "windowHeight",
    "windowWidth"
})
@Generated("jsonschema2pojo")
public class TestEnvironment {

    @JsonProperty("orientationAngle")
    private Integer orientationAngle;
    @JsonProperty("orientationType")
    private String orientationType;
    @JsonProperty("userAgent")
    private String userAgent;
    @JsonProperty("windowHeight")
    private Integer windowHeight;
    @JsonProperty("windowWidth")
    private Integer windowWidth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("orientationAngle")
    public Integer getOrientationAngle() {
        return orientationAngle;
    }

    @JsonProperty("orientationAngle")
    public void setOrientationAngle(Integer orientationAngle) {
        this.orientationAngle = orientationAngle;
    }

    @JsonProperty("orientationType")
    public String getOrientationType() {
        return orientationType;
    }

    @JsonProperty("orientationType")
    public void setOrientationType(String orientationType) {
        this.orientationType = orientationType;
    }

    @JsonProperty("userAgent")
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty("userAgent")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @JsonProperty("windowHeight")
    public Integer getWindowHeight() {
        return windowHeight;
    }

    @JsonProperty("windowHeight")
    public void setWindowHeight(Integer windowHeight) {
        this.windowHeight = windowHeight;
    }

    @JsonProperty("windowWidth")
    public Integer getWindowWidth() {
        return windowWidth;
    }

    @JsonProperty("windowWidth")
    public void setWindowWidth(Integer windowWidth) {
        this.windowWidth = windowWidth;
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
