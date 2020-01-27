package artmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import artmin.dao.DemoUserDao;
import artmin.dao.UserDao;
import artmin.model.DemoUser;
import artmin.model.User;
 
@Service("userService")
@Transactional
public class UserService{
    @Autowired
    private DemoUserDao dao;

    @Autowired
    private UserDao userDao;
     
    // zoeken van gebruiker op basis van ID
    public DemoUser findById(int id) {
        return dao.findById(id);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }
 
    // Bewaren van gebruiker
    public void saveUser(DemoUser user) {
        dao.saveUser(user);
    }
 
    // bijwerken van bestaande gebruiker
    public void updateUser(DemoUser user) {
        DemoUser entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
            entity.setConfirmPassword(user.getConfirmPassword());
        }
    }
 
    // verwijderen van gebruiker
    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }
     
    // zoeken van alle gebruikers
    public List<DemoUser> findAllUsers() {
        return dao.findAllUsers();
    }
 
 // controleer of paswoord gelijk is
    public boolean arePasswordsEqual(String password, String confirmPassword) {
        return (password.equals(confirmPassword));
    }

    public User findByEmail(String email) {
        if (userDao.findByEmail(email).size() == 0) {
            return null;
        }
        return userDao.findByEmail(email).get(0);
    }
}
