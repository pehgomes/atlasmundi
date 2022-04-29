package br.com.atlasmundi.atlasmundi.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Friends {


    @EmbeddedId
    private FriendId friendId;



    public FriendId getFriendId() {
        return friendId;
    }
}
