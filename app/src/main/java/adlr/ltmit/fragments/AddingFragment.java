package adlr.ltmit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adlr.ltmit.R;
import adlr.ltmit.activities.CreateNewCategoryActivity;

/**
 * Created by Agata on 2015-02-26.
 */
public class AddingFragment extends Fragment {

    public AddingFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adding, container, false);
    }

    public void addNewCategory(View view){
        Intent intent = new Intent(getActivity(), CreateNewCategoryActivity.class);
        startActivity(intent);
    }
}
