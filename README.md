Job Recruitment System - Endterm Project

This is my Job Recruitment System project using Spring Boot and PostgreSQL. It is a REST API that helps to manage job postings and users like candidates and recruiters.
Project Overview

I created this application to show how design patterns and component principles work in a real Java project. It consists of: Controller, Service, Repository, and Database. The system handles different types of users and allows management of jobs.
How to Run

    Database: First, you need to open pgAdmin 4 and create a new database called job_db.

    Config: Go to src/main/resources/application.properties and write your own database username and password there.

    Clean Up: Run mvn clean install in the terminal to make sure there are no old build conflicts.

    Start: Run JobRecruitmentSystemApplication.java . The server starts on http://localhost:8080.

REST API Documentation:
Users API

GET /api/users

    You can see the list of every user in the system.

POST /api/users

    This creates a new user. The system uses a Factory Pattern to decide if the user is a Candidate or a Recruiter.

    Example for Candidate:
    JSON

    {
      "type": "CANDIDATE",
      "name": "Sanzhar Zhunis",
      "email": "sanzhar@test.com",
      "portfolioLink": "https://github.com/santsane"
    }

    Example for Recruiter:
    JSON

    {
      "type": "RECRUITER",
      "name": "Apple HR",
      "email": "hr@apple.com",
      "companyName": "Apple"
    }

Jobs API

GET /api/jobs

    Shows all the job postings that are available.

POST /api/jobs

    Create a new job. The code uses a Builder Pattern for this.

    Body: { "title": "Java Developer", "description": "Backend", "location": "Remote", "salary": 5500.0 }.

PUT /api/jobs/{id}

    If you need to change job details, use this endpoint with the ID.

DELETE /api/jobs/{id}

    This will remove the job posting from the database .

Design Patterns:

    Singleton Pattern: I used this for LoggerSingleton. It makes sure we have only one logger for the app so we don't waste memory.

    Factory Pattern: The UserFactory handles making the objects. It looks at the "type" string and creates either a Candidate or Recruiter.

    Builder Pattern: I put this in JobPosting. It's helpful because jobs have many fields, and the builder makes it easy to set them one by one

Component Principles:

    REP (Reuse/Release Equivalence): I put the patterns like Factory and Singleton in their own package so they are easy to find or reuse.

    CCP (Common Closure): I put all model classes together in one package. If the database changes, I only need to look in that folder.

    CRP (Common Reuse): The layers (Controller, Service, Repository) are separate. This way, components only use what they really needd.

OOP and SOLID:

    Inheritance: User is the parent class, and Candidate and Recruiter are children. They are all saved in one table using a discriminator column.

    Liskov Substitution: My Service code works with the User type, so it doesn't matter if it's a Candidate or Recruiter because the code doesn't break.

    Single Responsibility: I tried to make every class do just one job. Controllers manage HTTP requests, and Services handle the logic.

Database:

The schema is created automatically by Spring Boot using my Java classes:

    users table: Stores the ID, name, email, and the type of user. It also has columns for portfolio links or company names.

    job_postings table: Stores all the information about the jobs like title and salary.


Memory Cache Update:
I added a Singleton Cache to improve the speed of getAllJobs(). It saves the data in RAM memory.

    I created the JobCache class, it uses Singleton so only 1 cache instance is in the project's memory.

    When user calls GET /api/jobs, the service first looks in the Cache:

        If jobs are in memory then it returns them;

        If the memory is empty, the Service takes jobs from the Database and saves them in Cache for future use.

    The Cache is cleared either when you Create, Update or Delete a job. This makes sure that the data is relevant

    Cache Logic: src/main/java/com/jrs/patterns/JobCache.java

    Service Logic: src/main/java/com/jrs/service/JobService.java