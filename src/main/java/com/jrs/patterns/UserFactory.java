package com.jrs.patterns;

import com.jrs.model.User;
import com.jrs.model.Candidate;
import com.jrs.model.Recruiter;

public class UserFactory {
    public static User createUser(String type, String name, String email) {
        if (type.equalsIgnoreCase("CANDIDATE")) {
            Candidate c = new Candidate();
            c.setName(name);
            c.setEmail(email);
            return c;
        } else if (type.equalsIgnoreCase("RECRUITER")) {
            Recruiter r = new Recruiter();
            r.setName(name);
            r.setEmail(email);
            return r;
        }
        throw new IllegalArgumentException("Unknown user type: " + type);
    }
}