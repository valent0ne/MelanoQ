package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Rule {
    private String rule;
    private String selectedOperator;
    private String selectedOperand;
    private String value;

    public Rule() {
    }

    public Rule(String rule, String selectedOperator, String selectedOperand, String value) {
        this.rule = rule;
        this.selectedOperator = selectedOperator;
        this.selectedOperand = selectedOperand;
        this.value = value;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getSelectedOperator() {
        return selectedOperator;
    }

    public void setSelectedOperator(String selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    public String getSelectedOperand() {
        return selectedOperand;
    }

    public void setSelectedOperand(String selectedOperand) {
        this.selectedOperand = selectedOperand;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
