package stream.join;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 8:42
 **/
public class UserView {


    private String userId;

    private String userName;

    private List<ViewInfo> list;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ViewInfo> getList() {
        return list;
    }

    public void setList(List<ViewInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String s="";
        s+="view的userId为"+userId;
        s+="    name为"+userName;
        return s;
    }
}
