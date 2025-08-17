CREATE KEYSPACE IF NOT EXISTS demo_keyspace
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE IF NOT EXISTS demo_keyspace.books (
                                                   id uuid PRIMARY KEY,
                                                   title text,
                                                   author text,
                                                   published_year int
);
