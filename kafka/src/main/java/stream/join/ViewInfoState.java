package stream.join;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/4/19 0019 9:56
 **/
public class ViewInfoState {

    private String userId;

    private List<ViewInfo> list=new ArrayList<>();





    public ViewInfoState add(ViewInfo viewInfo){
        list.add(viewInfo);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ViewInfo> getList() {
        return list;
    }

    public void setList(List<ViewInfo> list) {
        this.list = list;
    }
}
