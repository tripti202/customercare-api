package com.customercare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    @Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", tickets="
				+ tickets + ", createdAt=" + createdAt + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() + ", getTickets()=" + getTickets()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Customer(Long id, String name, String email, String phone, List<Ticket> tickets, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.tickets = tickets;
		this.createdAt = createdAt;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
