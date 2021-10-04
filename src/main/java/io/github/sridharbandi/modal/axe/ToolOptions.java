
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reporter",
    "resultTypes",
    "runOnly"
})
@Generated("jsonschema2pojo")
public class ToolOptions {

    @JsonProperty("reporter")
    private String reporter;
    @JsonProperty("resultTypes")
    private List<String> resultTypes = null;
    @JsonProperty("runOnly")
    private RunOnly runOnly;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reporter")
    public String getReporter() {
        return reporter;
    }

    @JsonProperty("reporter")
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    @JsonProperty("resultTypes")
    public List<String> getResultTypes() {
        return resultTypes;
    }

    @JsonProperty("resultTypes")
    public void setResultTypes(List<String> resultTypes) {
        this.resultTypes = resultTypes;
    }

    @JsonProperty("runOnly")
    public RunOnly getRunOnly() {
        return runOnly;
    }

    @JsonProperty("runOnly")
    public void setRunOnly(RunOnly runOnly) {
        this.runOnly = runOnly;
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
