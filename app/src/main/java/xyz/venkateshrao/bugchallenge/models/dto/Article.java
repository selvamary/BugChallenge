package xyz.venkateshrao.bugchallenge.models.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by venkatesh on 30/9/15.
 */
public class Article {
    public String title;

    @SerializedName("abstract")
    public String excerpt;
}
