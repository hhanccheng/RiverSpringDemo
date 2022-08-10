package com.usc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.beans.User;
import com.usc.beans.UserInfo;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
	UserInfo findByUser(User user);
}
