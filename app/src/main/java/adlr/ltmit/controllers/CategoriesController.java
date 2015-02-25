package adlr.ltmit.controllers;

import com.activeandroid.query.Delete;

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
//        List<Category> l = new Delete().from(Category.class).execute();
//        createExamples();;
        cd = new CategoryDao();

    }

    private void createExamples(){
        Category cat = new Category("cat1");
        cat.save();
        Category cat2 = new Category("cat2");
        cat2.save();
        Category cat3 = new Category("cat3");
        cat3.save();
        Category cat4 = new Category("cat4");
        cat4.save();
        Category cat5 = new Category("cat5");
        cat5.save();
    }

    public String[] getAllCategories(){
        List<String> list = new ArrayList<>();
        for (Category c : cd.selectAll()){
            list.add(c.getName());
        }
       return list.toArray(new String[list.size()]);
    }
}
