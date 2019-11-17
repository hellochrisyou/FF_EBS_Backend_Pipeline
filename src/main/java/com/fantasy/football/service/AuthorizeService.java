package com.fantasy.football.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void authorizeAdminOnly() {}	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public void authorizeClientOnly() {}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public void authorizeBoth() {}	
}
