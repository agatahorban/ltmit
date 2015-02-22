package adlr.ltmit.dao;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agata on 2015-02-22.
 */

public abstract class CrudDao<T extends Model> implements Crudable<T>  {

    private Class<T> entityClass;

    public CrudDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T select(Serializable id) {
            return new Select()
                    .from(entityClass)
                    .where("Id = ?",id)
                    .executeSingle();
    }


    @Override
    public List<T> selectAll() {
        return new Select()
                .from(entityClass)
                .execute();
    }


}
