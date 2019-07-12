package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Filter {

    private String type;
    private Rule query;

    public Filter() {
    }

    public Filter(String type, Rule rule) {
        this.type = type;
        this.query = rule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Rule getQuery() {
        return query;
    }

    public void setQuery(Rule query) {
        this.query = query;
    }
}
