
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "all",
    "any",
    "html",
    "impact",
    "none",
    "target"
})
@Generated("jsonschema2pojo")
public class Node {

    @JsonProperty("all")
    private List<Checks> all = null;
    @JsonProperty("any")
    private List<Checks> any = null;
    @JsonProperty("html")
    private String html;
    @JsonProperty("impact")
    private Object impact;
    @JsonProperty("none")
    private List<Checks> none = null;
    @JsonProperty("target")
    private List<String> target = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("all")
    public List<Checks> getAll() {
        return all;
    }

    @JsonProperty("all")
    public void setAll(List<Checks> all) {
        this.all = all;
    }

    @JsonProperty("any")
    public List<Checks> getAny() {
        return any;
    }

    @JsonProperty("any")
    public void setAny(List<Checks> any) {
        this.any = any;
    }

    @JsonProperty("html")
    public String getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(String html) {
        this.html = html;
    }

    @JsonProperty("impact")
    public Object getImpact() {
        return impact;
    }

    @JsonProperty("impact")
    public void setImpact(Object impact) {
        this.impact = impact;
    }

    @JsonProperty("none")
    public List<Checks> getNone() {
        return none;
    }

    @JsonProperty("none")
    public void setNone(List<Checks> none) {
        this.none = none;
    }

    @JsonProperty("target")
    public List<String> getTarget() {
        return target;
    }

    @JsonProperty("target")
    public void setTarget(List<String> target) {
        this.target = target;
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
