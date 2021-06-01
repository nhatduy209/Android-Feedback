package com.example.androidfeedback.ui.module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddModuleSpinner {
    @SerializedName("listAdminID")
    ArrayList<AdminModel> listAdminID;
    @SerializedName("listFeedbackID")
    ArrayList<FeedbackModel> listFeedbackID;

    public AddModuleSpinner(ArrayList<AdminModel> listAdminID, ArrayList<FeedbackModel> listFeedbackID)
    {
        this.listAdminID = listAdminID;
        this.listFeedbackID = listFeedbackID;
    }

    public ArrayList<AdminModel> getListAdminID() {
        return listAdminID;
    }

    public ArrayList<FeedbackModel> getListFeedbackID() {
        return listFeedbackID;
    }

    public void setListAdminID(ArrayList<AdminModel> listAdminID) {
        this.listAdminID = listAdminID;
    }

    public void setListFeedbackID(ArrayList<FeedbackModel> listFeedbackID) {
        this.listFeedbackID = listFeedbackID;
    }
}
