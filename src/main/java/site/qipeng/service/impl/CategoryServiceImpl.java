package site.qipeng.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qipeng.dao.CategoryMapper;
import site.qipeng.util.StringUtils;
import site.qipeng.entity.Category;
import site.qipeng.entity.CategoryExample;
import site.qipeng.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(Integer id) {
        if(!StringUtils.isEmpty(id.toString())){
            return categoryMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<Category> getCategoryList(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CategoryExample example = new CategoryExample();
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> getCategoryListJson() {
        CategoryExample example = new CategoryExample();
        return categoryMapper.selectByExample(example);
    }

    @Override
    public int insertCategory(String name) {
        Category record = new Category();
        record.setName(name);
        record.setCreateTime(new Date());
        logger.debug("insert one category...");
        return categoryMapper.insert(record);
    }

    @Override
    public int updateCategory(Integer id, String name) {
        Category category = getCategoryById(id);
        category.setName(name);
        category.setUpdateTime(new Date());
        logger.debug("update one category...");
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        if(!StringUtils.isEmpty(id.toString())){
            logger.debug("delete one category...");
            // TODO 删除分类后，视频怎么办
            return categoryMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }
}
