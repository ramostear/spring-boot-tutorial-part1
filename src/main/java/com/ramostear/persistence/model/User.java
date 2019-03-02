package com.ramostear.persistence.model;

import javax.persistence.*;

/**
 * @author ramostear
 * @create-time 2019/3/3 0003-2:42
 * @modify by :
 * @since:
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String address;

    public User(){
        super();
    }

    public User(String username,String address){
        super();
        this.username = username;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return address != null ? address.equals(user.address) : user.address == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
