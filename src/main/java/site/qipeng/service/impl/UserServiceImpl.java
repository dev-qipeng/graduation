package site.qipeng.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.qipeng.dao.UserMapper;
import site.qipeng.entity.User;
import site.qipeng.entity.UserDTO;
import site.qipeng.entity.UserExample;
import site.qipeng.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria cri = example.createCriteria();
        cri.andNicknameEqualTo(username);
        cri.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUserList(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample example = new UserExample();
        example.setOrderByClause("create_time desc");
        return userMapper.selectByExample(example);
    }

    @Override
    public User getById(Integer id) {
        if(id == null){
            return null;
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int update(UserDTO dto) {
        User user = getById(dto.getId());
        if (user == null){
            return 0;
        }
        user.setNickname(dto.getNickname());
        user.setPassword(dto.getPassword());
        user.setHeadImg(dto.getImgUrl());
        user.setSex(dto.getSex());
        user.setCity(dto.getCity());
        user.setUpdateTime(new Date());
        logger.debug("update one user...");
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public int delete(Integer id) {
        User user = getById(id);
        if (user == null){
            return 0;
        }
        logger.debug("delete one user...");
        return userMapper.deleteByPrimaryKey(id);
    }
}
