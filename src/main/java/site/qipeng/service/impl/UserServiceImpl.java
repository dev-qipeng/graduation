package site.qipeng.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.qipeng.entity.User;
import site.qipeng.mapper.UserMapper;
import site.qipeng.service.UserService;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    public User login(String username, String password) {
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getNickname, username).eq(User::getPassword, password));
        if (user != null) {
            return user;
        }
        return null;
    }


    @Transactional
    @Override
    public boolean update(User user) {
        user.setNickname(user.getNickname());
        user.setPassword(null);
        user.setHeadimg(user.getHeadimg());
        user.setSex(user.getSex());
        user.setCity(user.getCity());
        user.setUpdateTime(new Date());
        log.debug("update one user...");
        return updateById(user);
    }

    @Override
    public boolean changePwd(User user) {
        User update = new User();
        update.setId(user.getId());
        update.setPassword(user.getPassword());
        log.debug("change password by user:{} .", user.getNickname());
        return updateById(update);
    }

    @Override
    public User getByUsername(String userName) {
        List<User> list = list(Wrappers.<User>lambdaQuery().eq(User::getNickname, userName));
        if (list != null && list.size() > 1) {
            throw new RuntimeException("查询用户大于一个");
        }
        return list.get(0);
    }
}
