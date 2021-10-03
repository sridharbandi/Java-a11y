
package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "browser",
        "date",
        "device",
        "dimension",
        "id",
        "passes",
        "testEngine",
        "testEnvironment",
        "testRunner",
        "timestamp",
        "title",
        "toolOptions",
        "url",
        "violations",
        "inapplicable",
        "incomplete"
})
@Generated("jsonschema2pojo")
public class Issues {

    @JsonProperty("browser")
    private String browser;
    @JsonProperty("date")
    private String date;
    @JsonProperty("device")
    private String device;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("id")
    private String id;
    @JsonProperty("testEngine")
    private TestEngine testEngine;
    @JsonProperty("testEnvironment")
    private TestEnvironment testEnvironment;
    @JsonProperty("testRunner")
    private TestRunner testRunner;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("title")
    private String title;
    @JsonProperty("toolOptions")
    private ToolOptions toolOptions;
    @JsonProperty("url")
    private String url;
    @JsonProperty("violations")
    private List<Violation> violations = null;
    @JsonProperty("inapplicable")
    private List<Inapplicable> inapplicable = null;
    @JsonProperty("incomplete")
    private List<Incomplete> incomplete = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("inapplicable")
    public List<Inapplicable> getInapplicable() {
        return inapplicable;
    }

    @JsonProperty("inapplicable")
    public void setInapplicable(List<Inapplicable> inapplicable) {
        this.inapplicable = inapplicable;
    }

    @JsonProperty("incomplete")
    public List<Incomplete> getIncomplete() {
        return incomplete;
    }

    @JsonProperty("incomplete")
    public void setIncomplete(List<Incomplete> incomplete) {
        this.incomplete = incomplete;
    }

    @JsonProperty("browser")
    public String getBrowser() {
        return browser;
    }

    @JsonProperty("browser")
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("device")
    public String getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(String device) {
        this.device = device;
    }

    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("testEngine")
    public TestEngine getTestEngine() {
        return testEngine;
    }

    @JsonProperty("testEngine")
    public void setTestEngine(TestEngine testEngine) {
        this.testEngine = testEngine;
    }

    @JsonProperty("testEnvironment")
    public TestEnvironment getTestEnvironment() {
        return testEnvironment;
    }

    @JsonProperty("testEnvironment")
    public void setTestEnvironment(TestEnvironment testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

    @JsonProperty("testRunner")
    public TestRunner getTestRunner() {
        return testRunner;
    }

    @JsonProperty("testRunner")
    public void setTestRunner(TestRunner testRunner) {
        this.testRunner = testRunner;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("toolOptions")
    public ToolOptions getToolOptions() {
        return toolOptions;
    }

    @JsonProperty("toolOptions")
    public void setToolOptions(ToolOptions toolOptions) {
        this.toolOptions = toolOptions;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("violations")
    public List<Violation> getViolations() {
        return violations;
    }

    @JsonProperty("violations")
    public void setViolations(List<Violation> violations) {
        this.violations = violations;
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
