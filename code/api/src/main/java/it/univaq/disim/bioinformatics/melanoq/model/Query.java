package it.univaq.disim.bioinformatics.melanoq.model;

import it.univaq.disim.bioinformatics.melanoq.model.nested.Filter;

import java.util.*;

public class Query {
    // mapping of the operators obtained from the client's query builder (as key,
    // https://dabernathy89.github.io/vue-query-builder/), to the n1ql operators (as value)

    public static final Map<String, String> logicalOperators = new HashMap<String, String>() {{
        put("any", "OR");
        put("all", "AND");
    }};

    public static final Map<String, String> numericOperators = new HashMap<String, String>() {{
        put("=", "=");
        put("<>", "!=");
        put(">", ">");
        put(">=", ">=");
        put("<", "<");
        put("<=", "<=");
    }};

    public static final Map<String, String> textOperators = new HashMap<String, String>() {{
        put("equals", "=");
        put("does not equal", "!=");
        put("contains", "LIKE");
        put("does not contain", "NOT LIKE");

    }};

    private String logicalOperator;
    private List<Filter> children;

    public Query() {
    }

    public Query(String logicalOperator, List<Filter> children) {
        this.logicalOperator = logicalOperator;
        this.children = children;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public List<Filter> getChildren() {
        return children;
    }

    public void setChildren(List<Filter> children) {
        this.children = children;
    }

    public String toNumber(String value){
        return "TONUMBER(\""+value+"\")";
    }

    public String toNumber(int value){
        return "TONUMBER(\""+value+"\")";
    }

    public String toString(String value){
        return "TOSTRING(\""+value+"\")";
    }

    public String toString(int value){
        return "TOSTRING(\""+value+"\")";
    }

    public String likeWrapper(String value){
        return "%"+value+"%";
    }
}
