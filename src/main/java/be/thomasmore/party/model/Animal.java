package be.thomasmore.party.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Animal {
    @Id
    private int id;
    private String name;
    private String city;
    @Column(length=1000)
    private String bio;
    @ManyToMany(mappedBy = "animals")
    private Collection<Party> parties;

    public Animal(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Collection<Party> getParties() {
        return parties;
    }

    public void setParties(Collection<Party> parties) {
        this.parties = parties;
    }

    public boolean hasParties(){
        if(parties == null || parties.isEmpty()){
            return false;
        }
        return true;
    }
}
