package adlr.ltmit.bl;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import adlr.ltmit.R;

/**
 * Created by Agata on 2015-03-06.
 */
public class DbAdapter extends ArrayAdapter<DatabaseItem> {
    Context context;
    int layoutResourceId;
    DatabaseItem data[] = null;

    public DbAdapter(Context context, int layoutResourceId, DatabaseItem[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DbHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new DbHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtName = (TextView)row.findViewById(R.id.txtName);
            holder.txtDate = (TextView)row.findViewById(R.id.txtDate);
            row.setTag(holder);
        }
        else
        {
            holder = (DbHolder)row.getTag();
        }

        DatabaseItem db = data[position];
        holder.txtName.setText(db.getName());
        holder.txtDate.setText(db.getDateToRepeat());
        holder.imgIcon.setImageResource(db.getIcon());

        return row;
    }

    static class DbHolder
    {
        ImageView imgIcon;
        TextView txtName;
        TextView txtDate;
    }
}
