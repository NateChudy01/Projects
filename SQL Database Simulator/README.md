The SQL queries you are able to use are shown below:

    -insert MOVIE_NAME MOVIE_GENRE RELEASE_YEAR
    -select *
    -select GENRE
    -select RELEASE_YEAR
    -select GENRE RELEASE_YEAR

all of these will be input through the terminal where the promp takes
place. Some rules for these queries:

    -Do not use spaces in a movie or genre name when inserting or selecting, 
    if the movie or genrerequires multiple words use "_" instead of spaces
    -When writing the query, you must type the command in all lowercase
    or all uppercase (ex: insert or INSERT)
    -Make sure the genre you are typing to select is capitalized for the
    first letter
    -we have included a .txt file called movie.txt with 172 movies that 
    will be put into the database as the program is run. If you would like
    to add more you can add to it but keep the same input style
    (ex: Movie_name,Genre,Release_year)

**************************************************************************
compile: mpicc -o project3 project3.cpp -stdlib=libc++ -lstdc++ -std=c++20
run: mpirun -np NUM_NODES ./project3
**************************************************************************

replace the NUM_NODES with the number of nodes you want to run this
program with, *****MUST BE >1 NODE*****
