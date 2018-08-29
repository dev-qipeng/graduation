package site.qipeng.service;



import site.qipeng.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category getCategoryById(Integer id);
    List<Category> getCategoryList(Map<String, Object> map, Integer pageNum, Integer pageSize);
    List<Category> getCategoryListJson();
    int insertCategory(String name);
    int updateCategory(Integer id, String name);
    int deleteCategory(Integer id);

}
