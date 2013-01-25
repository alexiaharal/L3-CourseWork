DROP TABLE Attendance
go
DROP TABLE Dog
go
DROP TABLE Show
go
DROP TABLE Kennel
go
DROP TABLE Owner
go
DROP TABLE Breed
go
CREATE TABLE Breed (
breedname VARCHAR(64) NOT NULL,
PRIMARY KEY (breedname)
)
go
CREATE TABLE Owner(
ownerid int NOT NULL,
name VARCHAR(32),
phone VARCHAR(16),
PRIMARY KEY(ownerid)
)
go
CREATE TABLE Kennel(
kennelname VARCHAR(64) NOT NULL,
address VARCHAR(64),
phone VARCHAR(16),
PRIMARY KEY(kennelname)
)
go
CREATE TABLE Show(
showname VARCHAR(64) NOT NULL,
opendate VARCHAR(32) NOT NULL,
closedate VARCHAR(32),
PRIMARY KEY(showname, opendate)
)
go
CREATE TABLE Dog (
dogid int NOT NULL,
name VARCHAR(32),
ownerid int,
kennelname VARCHAR(64),
breedname VARCHAR(64),
mothername VARCHAR(32),
fathername VARCHAR(32),
PRIMARY KEY(dogid),
FOREIGN KEY(ownerid) REFERENCES Owner(ownerid),
FOREIGN KEY(kennelname) REFERENCES Kennel(kennelname),
FOREIGN KEY(breedname) REFERENCES Breed(breedname)
)
go
CREATE TABLE Attendance(
dogid int,
showname VARCHAR(64),
opendate VARCHAR(12),
place int,
PRIMARY KEY(showname, opendate, place),
FOREIGN KEY(dogid) REFERENCES Dog(dogid),
FOREIGN KEY(showname, opendate) REFERENCES Show(showname, opendate)
)