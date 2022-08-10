package com.usc.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity // jpa or mapping //ormapping
@Table(name = "usc_user")
public class User implements UserDetails{
private static final long serialVersionUID = 1L;
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ",sequenceName = "USC_USER_SEQ",allocationSize = 1)
	private int id;
	@Column(name = "username",unique = true,nullable = false)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "c_user_c_user_profile",joinColumns = {
			@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {
				@JoinColumn(name = "user_profile_id",referencedColumnName = "id")})
	private List<UserProfile> profiles = new ArrayList<UserProfile>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return profiles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", usrname=" + username + ", password=" + password + ", profiles=" + profiles + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<UserProfile> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String username, String password, List<UserProfile> profiles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.profiles = profiles;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	
	
	


}
