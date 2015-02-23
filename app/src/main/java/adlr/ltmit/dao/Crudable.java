package adlr.ltmit.dao;

import com.activeandroid.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agata on 2015-02-22.
 */

public interface Crudable<T extends Model> {

    T select(Serializable id);

    List<T> selectAll();
}

