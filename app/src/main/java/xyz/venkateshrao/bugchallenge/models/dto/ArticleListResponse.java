package xyz.venkateshrao.bugchallenge.models.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by venkatesh on 30/9/15.
 */
public class ArticleListResponse {
    @SerializedName("results")
    public List<Article> articles;
}
