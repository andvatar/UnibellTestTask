package ru.unibell.tarasov.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(schema="public", name="contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceContactInfoId")
    @SequenceGenerator(name = "SequenceContactInfoId", sequenceName = "contact_info_id_seq", allocationSize = 1)
    private int id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name="contact")
    private String contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public @NotBlank(message = "Contact is mandatory") @Size(max = 100, message = "Contact is too long") String getContact() {
        return contact;
    }

    public void setContact(@NotBlank(message = "Contact is mandatory") @Size(max = 100, message = "Contact is too long") String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return type == that.type && Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, contact);
    }
}
