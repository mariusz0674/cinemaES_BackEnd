package com.example.cinemaES.repository;
import com.example.cinemaES.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Integer> {

//    @Query(value = """
//      select t from RefreshToken t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
//    List<RefreshToken> findAllValidTokenByUser(Integer id);

    Optional<RefreshToken> findByToken(String refreshToken);
    void delete(RefreshToken refreshToken);
}