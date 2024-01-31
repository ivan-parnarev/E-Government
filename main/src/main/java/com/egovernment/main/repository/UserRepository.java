<<<<<<< Updated upstream
package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.User;
=======
package com.egovernment.egovbackend.repository;

import com.egovernment.egovbackend.domain.entity.User;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPIN(String pin);
}
