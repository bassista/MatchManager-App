package it.valeriovaudi.matchmanager.support.json;

/**
 * Created by Valerio on 02/05/2014.
 */
public enum JSONType {
    SINGLE_JSON("singleType"),ARRAY_JSON("complexType");

    private String type;

    JSONType(String type) {
        this.type = type;
    }

}
