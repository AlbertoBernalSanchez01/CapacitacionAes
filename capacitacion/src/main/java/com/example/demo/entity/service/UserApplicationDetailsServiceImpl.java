package  com.example.demo.entity.service;

import static java.util.Collections.emptyList;

import com.example.demo.domain.UserApplication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
* @author Zathura Code Generator Version 23.05 http://zathuracode.org/
* www.zathuracode.org
* @generationDate 2023-06-16T14:55:15.569972700
* 
*/

@Service
public class UserApplicationDetailsServiceImpl implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username==null) {
			throw new UsernameNotFoundException(username);
		}
		
		if (username.isBlank()) {
			throw new UsernameNotFoundException(username);
		}
		
		if (!username.equals("admin")) {
			throw new UsernameNotFoundException(username);
		}
	
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		UserApplication userApplication=new UserApplication(username,bCryptPasswordEncoder.encode("password"));
			
		return new User(userApplication.getUsername(), userApplication.getPassword(), emptyList());
	}

}
