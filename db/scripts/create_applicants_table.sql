CREATE TABLE applicant (
   id SERIAL PRIMARY KEY,
   first_name TEXT NOT NULL,
   last_name TEXT NOT NULL,
   city TEXT NOT NULL,
   skills TEXT[] NOT NULL,
   date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);