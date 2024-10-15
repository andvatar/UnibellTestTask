package ru.unibell.tarasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.*;

@Entity
@Table(schema = "public", name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceClientId")
    @SequenceGenerator(name = "SequenceClientId", sequenceName = "client_id_seq", allocationSize = 1)
    private int id;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="patronymic")
    private String patronymicName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Set<ContactInfo> contactInfo = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public void setContactInfo(Set<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(lastName, client.lastName) && Objects.equals(firstName, client.firstName) && Objects.equals(patronymicName, client.patronymicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymicName);
    }
}
