package com.heshanthenura.talkio.Database.Repositories;

import com.heshanthenura.talkio.Database.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
