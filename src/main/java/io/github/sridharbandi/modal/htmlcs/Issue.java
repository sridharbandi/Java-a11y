package io.github.sridharbandi.modal.htmlcs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "code",
        "techniques",
        "msg",
        "tag",
        "element"
})
public class Issue {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("code")
    private String code;
    @JsonProperty("techniques")
    private Set<String> techniques;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("element")
    private String element;

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("techniques")
    public void setTechniques(Set<String> techniques) {
        this.techniques = techniques;
    }

    @JsonProperty("techniques")
    public Set<String> getTechniques() {
        return techniques;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    @JsonProperty("element")
    public String getElement() {
        return element;
    }

    @JsonProperty("element")
    public void setElement(String element) {
        this.element = element;
    }

}