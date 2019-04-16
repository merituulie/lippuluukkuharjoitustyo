package swd20.lippuluukku.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import swd20.lippuluukku.domain.Kayttaja;
import swd20.lippuluukku.domain.KayttajaRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final KayttajaRepository repository;
	
	@Autowired
	public UserDetailServiceImpl(KayttajaRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override // tämän luokan toiminta olisi kiva kuulla tunnilla uudestaan, mistä nämä kaikki sanat tuleepi?
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Kayttaja curruser = repository.findByKayttajaNimi(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getSalasanaHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRooli()));
        return user;
	}
	
}

