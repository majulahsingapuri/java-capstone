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
