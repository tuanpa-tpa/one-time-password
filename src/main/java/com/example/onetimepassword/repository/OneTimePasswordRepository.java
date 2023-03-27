package com.example.onetimepassword.repository;

import com.example.onetimepassword.model.OneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pat on 27-Mar-23 - 9:48 AM
 *
 * @author pat
 * @project one-time-password
 */
@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {
}
