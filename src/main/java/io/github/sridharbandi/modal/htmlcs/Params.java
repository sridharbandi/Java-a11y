package io.github.sridharbandi.modal.htmlcs;

public class Params {

    String standard;
    String[] ignoreCodes;
    String pageTitle;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String[] getIgnoreCodes() {
        return ignoreCodes;
    }

    public void setIgnoreCodes(String[] ignoreCodes) {
        this.ignoreCodes = ignoreCodes;
    }

    public String getTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
