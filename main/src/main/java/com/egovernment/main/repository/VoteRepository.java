package com.egovernment.main.repository;

import com.egovernment.main.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vote v WHERE v.user.PIN = :userPin AND v.election.id = :electionId")
    boolean voteExistsByUserPinAndElectionId(@Param("userPin") String userPin, @Param("electionId") Long electionId);
}
