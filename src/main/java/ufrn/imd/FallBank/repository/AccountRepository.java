package ufrn.imd.FallBank.repository;

import ufrn.imd.FallBank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}