# Capstone 2 Java Project

This is for the UOB TDP Capstone 2 project

## Running PostgreSQL in Docker

1. Install Docker for your system [here](http://www.docker.com)

2. Create a volume to persist the database between container restarts:

   ```bash
   docker volume create capstone2_db
   ```

3. Start the database container:

   ```bash
   docker run -d -v capstone2_db:/var/lib/postgresql/data -e POSTGRES_USER=capstone2 -e POSTGRES_PASSWORD=password -e POSTGRES_DB=capstone2 -p 5432:5432 --name capstone2_db postgres
   ```

4. Run the `create_db_via_django.sql` file to create the tables.

5. Run the `PopulateData.java` file to insert the sample data.

   > This file provides sample users and transaction data for the application user to have some dummy data to play around with.
   >
   > The users provided are as follows:
   >
   > - admin_1
   > - admin_2
   > - teller_1
   > - teller_2
   > - teller_3
   > - teller_4
   > - teller_5
   > - customer_1
   > - customer_2
   > - customer_3
   > - customer_4
   > - customer_5
   >
   > The default password for all the users is `password`.
