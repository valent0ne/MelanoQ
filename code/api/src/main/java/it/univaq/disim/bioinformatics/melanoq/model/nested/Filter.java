package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Filter {

    private String type;
    private Rule rule;

    public Filter() {
    }

    public Filter(String type, Rule rule) {
        this.type = type;
        this.rule = rule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
