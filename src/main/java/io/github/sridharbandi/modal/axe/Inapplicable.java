
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "description",
    "help",
    "helpUrl",
    "id",
    "impact",
    "nodes",
    "tags"
})
@Generated("jsonschema2pojo")
public class Inapplicable {

    @JsonProperty("description")
    private String description;
    @JsonProperty("help")
    private String help;
    @JsonProperty("helpUrl")
    private String helpUrl;
    @JsonProperty("id")
    private String id;
    @JsonProperty("impact")
    private Object impact;
    @JsonProperty("nodes")
    private List<Object> nodes = null;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("help")
    public String getHelp() {
        return help;
    }

    @JsonProperty("help")
    public void setHelp(String help) {
        this.help = help;
    }

    @JsonProperty("helpUrl")
    public String getHelpUrl() {
        return helpUrl;
    }

    @JsonProperty("helpUrl")
    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
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
    public Object getImpact() {
        return impact;
    }

    @JsonProperty("impact")
    public void setImpact(Object impact) {
        this.impact = impact;
    }

    @JsonProperty("nodes")
    public List<Object> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
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
