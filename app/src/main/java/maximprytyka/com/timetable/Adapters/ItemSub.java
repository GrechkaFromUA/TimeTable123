package maximprytyka.com.timetable.Adapters;


/**
 * Created by maks on 29.05.17.
 */

public class ItemSub {
    private String sub;
    private String sTime;
    private String eTime;
    private String type;
    private String build;
    private String teacher;


    public ItemSub(String sub, String sTime, String eTime, String type, String build, String teacher) {
        this.sub = sub;
        this.sTime = sTime;
        this.eTime = eTime;
        this.type = type;
        this.build = build;
        this.teacher = teacher;
    }

    public String getSub() {
        return sub;
    }

    public String getsTime() {
        return sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public String getType() {
        return type;
    }

    public String getBuild() {
        return build;
    }

    public String getTeacher() {
        return teacher;
    }


}
