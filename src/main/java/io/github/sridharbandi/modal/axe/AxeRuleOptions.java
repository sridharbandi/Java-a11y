package io.github.sridharbandi.modal.axe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AxeRuleOptions {
    private Boolean enabled;

    @JsonProperty(value = "enabled")
    public Boolean getEnabled() {
        return this.enabled;
    }

    @JsonProperty(value = "enabled")
    public void setEnabled(final Boolean newEnabled) {
        this.enabled = newEnabled;
    }
}
