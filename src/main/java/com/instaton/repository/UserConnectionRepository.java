package com.instaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.instaton.model.UserConnection;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

	@Query(value = "SELECT * FROM user_connection t where t.providerId = :providerId AND t.providerUserId = :providerUserId", nativeQuery = true)
	public UserConnection findByProviderIdAndProviderUserId(@Param("providerId") String providerId,
			@Param("providerUserId") String providerUserId);

}
