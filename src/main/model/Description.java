package model;

import org.json.JSONObject;
import persistence.Writable;

public class Description implements Writable {
    private String description;

    //EFFECTS: creates new description from given string
    public Description(String description) {
        this.description = description;
    }

    //MODIFIES: this
    //EFFECTS: sets given string as a description
    public void setDescription(String s) {
        this.description = s;
    }

    //EFFECTS: returns description as string
    public String getDescription() {
        return description;
    }


    //EFFECTS: converts description to JSON object for data persistence
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        return json;
    }
}
