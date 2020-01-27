/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmin.dao;

import artmin.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rei
 */

@Repository("userDao")
public class UserDao extends AbstractDao<Long, User>{
    
    public User findById(long id){
        return getByKey(id);
    }
    
    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUser(User user) {
        delete(user);
    }

    public void deleteUserById(Long id) {
        Query query = getSession().createSQLQuery("delete from user where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> findByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (List<User>) criteria.list();
    }
    
}
