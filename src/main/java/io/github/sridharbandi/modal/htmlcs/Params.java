package io.github.sridharbandi.modal.htmlcs;

import io.github.sridharbandi.a11y.AxeTag;
import io.github.sridharbandi.modal.axe.AxeRuleOptions;

import java.util.*;
import java.util.stream.Collectors;

public class Params {

    String standard;
    String[] ignoreCodes;
    String pageTitle;
    Map<String, AxeRuleOptions> rules;
    List<String> tags;
    String scriptURL;

    public Params(){
        this.rules = new HashMap<>();
        this.tags = Arrays.asList("wcag2aa", "section508");
    }

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

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getScriptURL() {
      return scriptURL;
    }
    
    public void setScriptURL(String scriptURL) {
      this.scriptURL = scriptURL;
    }

    public Map<String, AxeRuleOptions> getRules() {
        return rules;
    }
    public void setTags(AxeTag... tags) {
        List<String> tagList = Arrays.stream(tags).map(tag -> tag.name().toLowerCase()).collect(Collectors.toList());
        if (!tagList.isEmpty()) this.tags = tagList;
    }
    public List<String> getTags() {
        return tags;
    }

    public void disableRules(String... rules) {
        Map<String, AxeRuleOptions> disableRulesMap = prepareAxeRuleMapByStatus(rules, false);
        this.rules.putAll(disableRulesMap);
    }

    public void enableRules(String... rules) {
        Map<String, AxeRuleOptions> enabledRulesMap = prepareAxeRuleMapByStatus(rules, true);
        this.rules.putAll(enabledRulesMap);
    }

    private Map<String, AxeRuleOptions> prepareAxeRuleMapByStatus(String[] rules, boolean status) {
        Map<String, AxeRuleOptions> rulesMap = new HashMap<>();
        AxeRuleOptions axeRuleOptions = new AxeRuleOptions();
        axeRuleOptions.setEnabled(status);

        for (String rule : rules) {
            if (!rule.isEmpty()) rulesMap.put(rule, axeRuleOptions);
        }
        return rulesMap;
    }
}
