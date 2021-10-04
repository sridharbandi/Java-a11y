
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "id",
    "impact",
    "message",
    "relatedNodes"
})
@Generated("jsonschema2pojo")
public class None {

    @JsonProperty("data")
    private Object data;
    @JsonProperty("id")
    private String id;
    @JsonProperty("impact")
    private String impact;
    @JsonProperty("message")
    private String message;
    @JsonProperty("relatedNodes")
    private List<Object> relatedNodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public Object getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Object data) {
        this.data = data;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("impact")
    public String getImpact() {
        return impact;
    }

    @JsonProperty("impact")
    public void setImpact(String impact) {
        this.impact = impact;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("relatedNodes")
    public List<Object> getRelatedNodes() {
        return relatedNodes;
    }

    @JsonProperty("relatedNodes")
    public void setRelatedNodes(List<Object> relatedNodes) {
        this.relatedNodes = relatedNodes;
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
