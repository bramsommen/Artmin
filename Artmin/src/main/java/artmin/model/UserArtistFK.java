/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmin.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * TODO REMOVE UNUSED
 */

@Embeddable
public class UserArtistFK implements Serializable{  
   
    
    private Long artistID;
    private Long userID;

    public UserArtistFK() {}

    public UserArtistFK(Long artistID, Long userID) {
        this.artistID = artistID;
        this.userID = userID;
    }
    
}
