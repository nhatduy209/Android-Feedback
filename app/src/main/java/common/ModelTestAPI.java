package common;

import com.google.gson.annotations.SerializedName;

public class ModelTestAPI {
    private int userID , id ;
    private String titleTestAPITTTT ;

    @SerializedName("body")
    private String text;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return titleTestAPITTTT;
    }

    public String getText() {
        return text;
    }
}
