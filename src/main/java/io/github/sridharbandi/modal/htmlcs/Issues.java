package io.github.sridharbandi.modal.htmlcs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errors",
        "warnings",
        "notices",
        "standard",
        "date",
        "width",
        "height",
        "dimension",
        "device",
        "browser",
        "url",
        "title",
        "results",
        "id"
})
public class Issues {
    @JsonProperty("errors")
    private Integer errors;
    @JsonProperty("warnings")
    private Integer warnings;
    @JsonProperty("notices")
    private Integer notices;
    @JsonProperty("standard")
    private String standard;
    @JsonProperty("date")
    private String date;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("device")
    private String device;
    @JsonProperty("browser")
    private String browser;
    @JsonProperty("url")
    private String url;
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private String id;
    @JsonProperty("results")
    private List<Issue> results = null;

    @JsonProperty("errors")
    public Integer getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(Integer errors) {
        this.errors = errors;
    }

    @JsonProperty("warnings")
    public Integer getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(Integer warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("notices")
    public Integer getNotices() {
        return notices;
    }

    @JsonProperty("notices")
    public void setNotices(Integer notices) {
        this.notices = notices;
    }

    @JsonProperty("standard")
    public String getStandard() {
        return standard;
    }

    @JsonProperty("standard")
    public void setStandard(String standard) {
        this.standard = standard;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @JsonProperty("device")
    public String getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(String device) {
        this.device = device;
    }

    @JsonProperty("browser")
    public String getBrowser() {
        return browser;
    }

    @JsonProperty("browser")
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("results")
    public List<Issue> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Issue> issues) {
        this.results = issues;
    }
}
