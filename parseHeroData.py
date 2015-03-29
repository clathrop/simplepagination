import csv, sqlite3

f = open('herodata.csv')
csv_f = csv.reader(f)
conn = sqlite3.connect('hero.db')

cur = conn.cursor()
cur.executescript("""
        DROP TABLE IF EXISTS users;
        CREATE TABLE users (user_id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, user_name TEXT, email TEXT, age INTEGER);
        """) # checks to see if table exists and makes a fresh table.

conn.commit()

d=1


for row in csv_f:
	cur.execute("INSERT INTO users (user_id, first_name, last_name, user_name, email, age) VALUES (?, ?, ?, ?, ?, ?)", (None, row[0], row[1], row[2], row[3], row[4]))	
	
	#print row
conn.commit()
