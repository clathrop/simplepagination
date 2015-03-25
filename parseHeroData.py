import csv, sqlite3

f = open('herodata.csv')
csv_f = csv.reader(f)
conn = sqlite3.connect('hero.db')

cur = conn.cursor()
cur.executescript("""
        DROP TABLE IF EXISTS users;
        CREATE TABLE users (first_name TEXT, last_name TEXT, user_name TEXT, email TEXT, age INTEGER);
        """) # checks to see if table exists and makes a fresh table.

#cur.execute("INSERT INTO sdkdata (user_id, event_count, event_name, event_time, os_name, sdk_version) VALUES (1, 2, 'eventName', 'eventTime 28', 'os name', 'sdk version');")
conn.commit()

d=1


for row in csv_f:
	cur.execute("INSERT INTO users (first_name, last_name, user_name, email, age) VALUES (?, ?, ?, ?, ?)", (row[0], row[1], row[2], row[3], row[4]))	
	
	#print row
conn.commit()
