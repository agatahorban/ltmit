package adlr.ltmit.bl;

/**
 * Created by Agata on 2015-03-06.
 */
public class DatabaseItem {
    private String name;
    private int icon;
    private String dateToRepeat;

    public DatabaseItem(String name, int icon, String dateToRepeat) {
        this.name = name;
        this.icon = icon;
        this.dateToRepeat = dateToRepeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDateToRepeat() {
        return dateToRepeat;
    }

    public void setDateToRepeat(String dateToRepeat) {
        this.dateToRepeat = dateToRepeat;
    }
}
