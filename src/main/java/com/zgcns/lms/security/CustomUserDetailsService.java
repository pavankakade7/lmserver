//package com.zgcns.lms.security;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.zgcns.lms.model.User;
//import com.zgcns.lms.repositories.UserRepository;
//
//import lombok.AllArgsConstructor; 
//
//@Service		
//@AllArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService{
//		
//	private UserRepository userRepository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//			
//		User user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("user not exists by username"));
//		
//		
//		Set<GrantedAuthority> authorities = user.getRoles().stream()
//				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//			
//		
//		
//		return new org.springframework.security.core.userdetails.User(
//				
//				username,
//				user.getPassword(),
//				authorities
//				);
//	}
//
//}
