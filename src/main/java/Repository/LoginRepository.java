package Repository;

import model.LoginUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<LoginUser,Long> {

}
