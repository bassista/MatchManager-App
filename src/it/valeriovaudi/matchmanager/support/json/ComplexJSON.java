package it.valeriovaudi.matchmanager.support.json;

/**
 * Created by Valerio on 02/05/2014.
 */
public class ComplexJSON {

    private JSONType jsonType;
    private Object json;

    public JSONType getJsonType() {
        return jsonType;
    }

    public void setJsonType(JSONType jsonType) {
        this.jsonType = jsonType;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
}
