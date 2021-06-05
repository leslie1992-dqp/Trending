package com.example.trending;

import java.util.List;

public interface ParentView {

    void success(List<ListBean> listBeans);

    void error(String error);

}
