package com.jrs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    private double salary;

    // Private constructor forces Builder
    private JobPosting(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.location = builder.location;
        this.salary = builder.salary;
    }

    public JobPosting() {} // for JPA

    // Getters
    public String getTitle() { return title; }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public double getSalary() {
        return salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Static inner Builder class .
    public static class Builder {
        private String title;
        private String description;
        private String location;
        private double salary;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public JobPosting build() {
            return new JobPosting(this);
        }
    }
}