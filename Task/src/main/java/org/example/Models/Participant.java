package org.example.Models;

import javax.persistence.*;

@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean pcrTestPassed;

    private Long eventId;

    private int balance;

    public Participant() {
    }

    public Participant(String full_name, int age, boolean pcrTestPassed, Long eventId, int balance) {
        this.full_name = full_name;
        this.age = age;
        this.pcrTestPassed = pcrTestPassed;
        this.eventId = eventId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String fullName) {
        this.full_name = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPcrTestPassed() {
        return pcrTestPassed;
    }

    public void setPcrTestPassed(boolean pcrTestPassed) {
        this.pcrTestPassed = pcrTestPassed;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
