/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmin.dao;

import artmin.model.Artist;
import artmin.model.User;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("artistDao")
public class ArtistDao extends AbstractDao<Long, Artist> {

    public Artist findById(Long id) {
        return getByKey(id);
    }

    public void saveArtist(Artist artist) {
        persist(artist);
    }

    public void updateArtist(Artist artist) {
         getSession().saveOrUpdate(artist);
    }

    public void deleteArtistById(Long id) {
        Query query = getSession().createSQLQuery("delete from Artists where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Artist> findAllArtists() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Artist>) criteria.list();
    }

    public Set<User> findAllMatchingUsers(Long id) {
        return getByKey(id).getUsers();
    }
}
