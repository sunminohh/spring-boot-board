package kr.co.jhta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.jhta.dao.UserDao;
import kr.co.jhta.dao.UserRoleDao;
import kr.co.jhta.vo.User;
import kr.co.jhta.vo.UserRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("["+email+"]");
		}
		
		List<UserRole> userRoles = userRoleDao.getUserRoleByUserNo(user.getNo());
		List<String> roleNames = new ArrayList<String>();
		for (UserRole role : userRoles) {
			roleNames.add(role.getRoleName());
		} 
		user.setRoleNames(roleNames);
		
		return user;
	}
	
	// 신규 사용자 등록하기
	public void createUser(String email, String password) {
		
		// 1. 비밀번호 암호화
		User user = new User();
		user.setEmail(email);
		
		String encryptedPassword = passwordEncoder.encode(password);
		user.setEncryptedPassword(encryptedPassword); 
		
		// 2. 테이블에 사용자 정보 저장하기
		userDao.insertUser(user);
		
		// 3. 테이블에 사용자의 보유권한 저장하기
		UserRole role = new UserRole();
		role.setUser(user);
		role.setRoleName("ROLE_USER"); 
		
		userRoleDao.insertUserRole(role); 
	}
	
	
	
	// 사용자 상세정보 제공하기(보유권한 정보도 같이 조회함)
	public User getUserDetail(int userNo) {
		
		return null;
	}
	
	// 사용자 정보 변경하기	
	public void modifyUser(User user) {

	}
}
