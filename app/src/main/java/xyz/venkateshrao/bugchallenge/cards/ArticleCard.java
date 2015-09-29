package xyz.venkateshrao.bugchallenge.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import it.gmariotti.cardslib.library.internal.Card;
import xyz.venkateshrao.bugchallenge.R;
import xyz.venkateshrao.bugchallenge.models.dto.Article;

/**
 * Created by venkatesh on 30/9/15.
 */
public class ArticleCard extends Card {

    private TextView tvArticleTitle;
    private TextView tvArticleExcerpt;
    private Article article;

    public ArticleCard(Context context, Article article) {
        super(context, R.layout.article_card_inner_layout);
        this.article = article;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        tvArticleTitle = (TextView) parent.findViewById(R.id.tvArticleTitle);
        tvArticleExcerpt = (TextView) parent.findViewById(R.id.tvArticleExcerpt);

        tvArticleTitle.setText(article.title);
        tvArticleExcerpt.setText(article.excerpt);

    }
}
