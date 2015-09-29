package xyz.venkateshrao.bugchallenge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by venkatesh on 30/9/15.
 */
public abstract class BaseFragment extends Fragment {

    public abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onViewReady(view, savedInstanceState);
    }

    protected abstract void onViewReady(View view, Bundle savedInstanceState);
}
