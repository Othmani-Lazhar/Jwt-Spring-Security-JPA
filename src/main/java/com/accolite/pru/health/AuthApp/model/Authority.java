package com.accolite.pru.health.AuthApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

/**
 * The type Authority. Defines the role and the list of users who are associated with that role
 */
@Entity(name = "AUTHORITY")
public class Authority {

    @Id
	@Column(name = "AUTHORITY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORITY_SEQ")
    @SequenceGenerator(name = "AUTHORITY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "AUTHORITY_ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityRole role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> userList;

	public Long getId() {
		return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityRole getRole() {
		return this.role;
    }

    public void setRole(AuthorityRole role) {
        this.role = role;
    }

    public List<User> getUserList() {
		return this.userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
