package xyz.venkateshrao.bugchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import xyz.venkateshrao.bugchallenge.cards.ArticleCard;
import xyz.venkateshrao.bugchallenge.models.dto.Article;
import xyz.venkateshrao.bugchallenge.models.dto.ArticleListResponse;
import xyz.venkateshrao.bugchallenge.services.MyService;

/**
 * Created by venkatesh on 30/9/15.
 */
public class ArticleCardsFragment extends BaseFragment {

    private CardRecyclerView cardRecyclerView;
    private CardArrayRecyclerViewAdapter cardArrayRecyclerViewAdapter;
    private List<Card> cards;
    private Gson gson;
    private ProgressBar progressBar;

    public static ArticleCardsFragment newInstance() {
        ArticleCardsFragment articleCardsFragment = new ArticleCardsFragment();
        return articleCardsFragment;
    }

    public ArticleCardsFragment() {
        gson = new Gson();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_article_cards;
    }

    @Override
    protected void onViewReady(View view, Bundle savedInstanceState) {
        progressBar = (ProgressBar) view.findViewById(R.id.pbArticlesLoading);
        progressBar.setVisibility(View.VISIBLE);
        cardRecyclerView = (CardRecyclerView) view.findViewById(R.id.crvArticles);
        cards = new ArrayList<>();
        cardArrayRecyclerViewAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);
        cardRecyclerView.setAdapter(cardArrayRecyclerViewAdapter);
        cardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateArticles();
    }

    private void updateArticles() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.API_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArticleListResponse articleListResponse = gson.fromJson(response.toString(), ArticleListResponse.class);
                refreshList(articleListResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    private void refreshList(ArticleListResponse articleListResponse) {
        List<Article> articles = articleListResponse.articles;
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            ArticleCard articleCard = new ArticleCard(getActivity(), articles.get(i));
            cardList.add(articleCard);
        }
        cardArrayRecyclerViewAdapter.addAll(cardList);
        progressBar.setVisibility(View.GONE);
        cardRecyclerView.setVisibility(View.VISIBLE);
        getActivity().startService(new Intent(getActivity(), MyService.class));
    }
}
