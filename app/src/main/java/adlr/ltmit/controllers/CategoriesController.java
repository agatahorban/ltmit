package adlr.ltmit.controllers;

import java.util.ArrayList;
import java.util.List;

import adlr.ltmit.dao.CategoryDao;
import adlr.ltmit.entities.Category;

/**
 * Created by Agata on 2015-02-25.
 */

public class CategoriesController {
    private CategoryDao cd;

    public CategoriesController(){
        cd = new CategoryDao();

    }

    public String[] getAllCategories(){
        List<String> list = new ArrayList<>();
        for (Category c : cd.selectAll()){
            list.add(c.getName());
        }
       return list.toArray(new String[list.size()]);
    }

    public void addNewCategory(String name){
        Category cat = new Category(name);
        cat.save();
    }
}
