package com.sample.service;

import com.sample.dao.CartItemDao;
import com.sample.dao.UserDao;
import com.sample.exception.DuplicatedIdAndEmailException;
import com.sample.exception.SampleException;
import com.sample.vo.User;
import com.sample.web.utils.SessionUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    CartItemDao cartItemDao;

  /*  @Autowired
    OrderService orderService;

    @Autowired
    PointHistoryDao pointHistoryDao;*/

    @Override
    public void registerUser(User user) {
        User savedUser = userDao.getUserById(user.getId());
        if (savedUser != null) {
            throw new SampleException("ERR_USER_001", "아이디 중복", "[ " + user.getId() + " ]는 이미 사용중인 아이디 입니다.");
        }
        savedUser = userDao.getUserByEmail(user.getEmail());
        if (savedUser != null) {
            throw new SampleException("ERR_USER_001", "이메일 중복", "[ " + user.getEmail() + " ]는 이미 사용중인 아메일 입니다.");
        }

        String secretPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(secretPassword);

        userDao.insertUser(user);
    }

    @Override
    public void login(String id, String password) {
        // 사용자정보 조회 - null 인지 체크, 삭제된 사용자인지 체크, 비밀번호가 일치하는지 체크
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new SampleException("ERR_USER_002", "아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
        }

        if (!"active".equalsIgnoreCase(user.getStatus())) {
            throw new SampleException("ERR_USER_002", "사용중지된 회원", "탈퇴 혹은 일시정지 처리된 사용자 입니다.");
        }
        String secretPassword = DigestUtils.sha256Hex(password);
        if(!user.getPassword().equals(secretPassword)) {
            throw new SampleException("ERR_USER_002", "아이디/비밀번호 오류", "아이디 혹은 비밀번호가 유효하지 않습니다.");
        }

        // HttpSession 객체에 사용자 인증이 완료된 사용자정보를 속성으로 추가한다.
        SessionUtils.addAttribute("LOGINED_USER", user);
    }

    @Override
    public Map<String, Object> getUserDetail(String id) {
        Map<String, Object> userDetailMap = new HashMap<>();

        // 최신의 사용자 정보 조회
        User user = userDao.getUserById(id);

        userDetailMap.put("user", user);
        userDetailMap.put("cartItems", "장바구니에 저장된 아이템정보들");
        userDetailMap.put("orders", "최근 구매내역");
        userDetailMap.put("reviews", "내가 작성한 리뷰");
        userDetailMap.put("pointHistory", "포인트변경 이력");
        
        return userDetailMap;
    }
}
