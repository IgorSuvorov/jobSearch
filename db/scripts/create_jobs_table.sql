CREATE TABLE job (
   id SERIAL PRIMARY KEY,
   title TEXT NOT NULL,
   company_name TEXT NOT NULL,
   city TEXT NOT NULL,
   description TEXT NOT NULL,
   skills TEXT[] NOT NULL
   date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
