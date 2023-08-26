package com.who.userservice.repository;

import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactsRepository extends CrudRepository<Contact, UUID> {
    @Query("select c from Contact c where c.user.id = :userID and c.isBlocked = :isBlocked " +
            "and (:contactCategory IS NULL OR c.contactCategory = :contactCategory)")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "contact")
    List<Contact> getAllContactByUserIdAndIsBlocked(@Param("userID") UUID userID,
                                                    @Param("isBlocked") Boolean isBlocked,
                                                    @Param("contactCategory") ContactCategory contactCategory);

    @Query("select c from Contact c where c.user.id = :userID and c.contact.id = :contactID")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "contact")
    Optional<Contact> getContactsByUserID(@Param("userID") UUID userID,
                                          @Param("contactID") UUID contactID);
    @Modifying
    @Query("delete from Contact c where c.user.id = :userID and c.contact.id = :contactID")
    void deleteContactByIdAndUserId(@Param("userID") UUID userID,
                                    @Param("contactID") UUID contactID);

}
