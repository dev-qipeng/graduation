package site.qipeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import site.qipeng.entity.User;

public interface UserService extends IService<User> {
    @Transactional
    boolean update(User user);

    boolean changePwd(User user);

    User getByUsername(String s);
}
