package adlr.ltmit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import adlr.ltmit.R;
import adlr.ltmit.controllers.CategoriesController;

/**
 * Created by Agata on 2015-02-25.
 */
public class DatabasesListFragment extends ListFragment
{
    CategoriesController cc;
    String[] stringArray;


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        cc = new CategoriesController();
        stringArray = cc.getAllCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),   R.layout.row_layout, R.id.label,
                stringArray);
        setListAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setSelector(R.drawable.lselector);
    }

}