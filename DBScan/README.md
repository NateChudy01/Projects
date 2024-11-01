1.) The code will read the first row of numbers in the data.txt file as Epsilon and Minimum Points meaning that you need to make sure that this is filled in correctly when testing a set of Points

2.) For our algorithm you can either input a unique set of data in data.txt or use a random set of data generated by the code and in order to do so you must use line 318 AND line 312 in the code. If you want random input set the boolean to true and edit the input in the randInput function, if you want a specific data set, set the boolean to false then input your data into data.txt

3.) In order to see the graph output, you must use gnuplot. If you are on mac you can use "brew install gnuplot" on command line, and for windows you need to download it. This section comes commented out my default, to use the graphing function you must uncomment lines 360 - 376. THIS IS ONLY FOR TESTING, MUST HAVE GNUPLOT INSTALLED ON COMPUTER (brew install gnuplot)

4.) We kept the functionality between single thread and parallelized multi threads. To switch between them you must change the boolean on line 313 to toggle between the two

Switch to the correct directory with both the main.cpp file and the data.txt file and use the following commands to compile and run the program

Compile: g++ -std=c++20 main.cpp -o dbscan
Run: ./dbscan

USE THE DBSCAN_PROJECT FOLDER TO RUN THE CODE