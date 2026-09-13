package com.blog.app.SrpingBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.SrpingBoot.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
