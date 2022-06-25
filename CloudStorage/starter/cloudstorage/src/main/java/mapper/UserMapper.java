package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import model.User;

@Mapper
public interface UserMapper {

	@Select("select * from USERS")
	public List<User> findAllUsers();
	
	@Select("select * from USERS where username=#{userName}")
	public User getUser(String userName);
	
	@Select("select * from USERS where userid=#{userId}")
	public User findUserById(int userId);
	
//	@Insert("insert into USERS values(userName, salt, password, firstName, lastName, notes)")
//	public int insert(User user);

}
