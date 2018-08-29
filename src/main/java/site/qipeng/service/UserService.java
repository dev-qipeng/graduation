package site.qipeng.service;


import site.qipeng.entity.User;
import site.qipeng.entity.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(String username, String password);

    List<User> getUserList(Map<String,Object> map, Integer pageNum, Integer pageSize);

    User getById(Integer id);

    int update(UserDTO userDTO);

    int delete(Integer id);
}
