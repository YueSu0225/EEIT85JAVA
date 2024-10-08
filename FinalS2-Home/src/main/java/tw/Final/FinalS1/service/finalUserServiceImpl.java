package tw.Final.FinalS1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.RegisterRequest;

import tw.Final.FinalS1.model.cartModel;
import tw.Final.FinalS1.model.finalUserModel;
import tw.Final.FinalS1.model.userInfoMedel;
import tw.Final.FinalS1.model.wishListModel;
import tw.Final.FinalS1.repository.UserInfoRepository;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.repository.cartRepository;
import tw.Final.FinalS1.repository.wishListRepository;


@Service
public class finalUserServiceImpl implements finalUserService{

	@Autowired
	private cartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private wishListRepository wishListRepository;
	
	

	@Override
	public ResponseEntity<Map<String, Object>> registerUser(RegisterRequest request) {
		
		 // 檢查帳號是否已存在
	    List<finalUserModel> existingUser = userRepository.findByAccount(request.getAccount());
	    if (!existingUser.isEmpty()) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "信箱已被註冊");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
		 finalUserModel user = new finalUserModel();
	        user.setAccount(request.getAccount());
	        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
	        user.setEmail(request.getEmail());
	        user.setUuid(null);
	        user.setGoogleId(null); // 如果不使用 Google 登錄

	        // 保存用户
	        userRepository.save(user);

	        // 創建購物車
	        cartModel cart = new cartModel();
	        cart.setUser(user);
	        cartRepository.save(cart);
	        
	        // 創建喜愛清單
	        wishListModel wishList = new wishListModel();
	        wishList.setUser(user);
	        wishListRepository.save(wishList);

	        // 創建用戶資訊
	        userInfoMedel userInfo = new userInfoMedel();
	        userInfo.setUser(user);
	        userInfo.setName(request.getName());
	        userInfo.setPhone_number(Integer.parseInt(request.getPhone()));
	        userInfo.setAddress(request.getStreet());
	        userInfo.setBirthday(request.getBirthday());
	        userInfoRepository.save(userInfo);
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("message", "Registration successful");
	        return ResponseEntity.ok(response);
	}


	@Override
	public ResponseEntity<Map<String, Object>> loginUser(RegisterRequest request) {
	    List<finalUserModel> existingUser = userRepository.findByAccount(request.getAccount());
	    Map<String, Object> response = new HashMap<>();
	    
	    // 檢查帳號是否存在
	    if (existingUser.isEmpty()) {	        
	        response.put("success", false);
	        response.put("message", "此帳號未註冊");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	    
	    finalUserModel user = existingUser.get(0); // 資料庫獲取用戶
	    
	    // 檢查密碼是否正確
	    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
	        response.put("success", true);
	        response.put("message", "登入成功");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        response.put("success", false);
	        response.put("message", "密碼不正確");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> logincheck(String account) {
	    List<finalUserModel> user = userRepository.findByAccount(account);
        Map<String, Object> response = new HashMap<>();
        
        if (!user.isEmpty()) {
            response.put("exists", true);
        } else {
            response.put("exists", false);
        }

        return ResponseEntity.ok(response);
	}


	@Override
	public void googleUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email"); // 獲取 Google 信箱
        String name = oAuth2User.getAttribute("name");   // 獲取 Google 姓名
        String googleId = oAuth2User.getAttribute("sub"); // 獲取 Google ID
	    List<finalUserModel> existGoogleId = userRepository.findByAccount(googleId);
	    if (!existGoogleId.isEmpty()) {
	        System.out.println("User already exists: " );
	       
	    } else {
		 finalUserModel user = new finalUserModel();
	        user.setAccount(null);
	        user.setPassword(null);
	        user.setEmail(email);
	        user.setUuid(null);
	        user.setGoogleId(googleId); // 使用 Google 登錄
	        userRepository.save(user);

	        // 創建購物車
	        cartModel cart = new cartModel();
	        cart.setUser(user);
	        cartRepository.save(cart);
	        
	        // 創建喜愛清單
	        wishListModel wishList = new wishListModel();
	        wishList.setUser(user);
	        wishListRepository.save(wishList);

	        // 創建用戶資訊
	        userInfoMedel userInfo = new userInfoMedel();
	        userInfo.setUser(user);
	        userInfo.setName(name);
	        userInfo.setPhone_number(0);
	        userInfo.setAddress(null);
	        userInfo.setBirthday(null);
	        userInfoRepository.save(userInfo);

        
        System.out.println("User email: " + email);
        System.out.println("User name: " + name);
        System.out.println("User Google ID: " + googleId);
    
	    }
	}	
}
