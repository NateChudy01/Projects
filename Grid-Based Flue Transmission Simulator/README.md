# How To Run

#### When running this program there are multiple parameters you can change. In the run command below you can change different values which is shown below:

#### **** ./project2 Alpha Beta Omega runType imageScale numThreads ****

#### To understand these parameters make sure to look at lines 285 - 303 in the project2.cpp file. When running the program there will be two outputs, output.txt and simulation.ppm. You can view both of them to see the flu spread however in output.txt the infected people are represented by 1's and uninfected are represented by 0's. In the simulation.ppm, you need to download an extension that can view .ppm flies. The one I used on vscode was named "PBM/PPM/PGM Viewer for Visual Studio Code". The simulator outputs the time of each day and the options you can do are printed to the terminal

### Compile: g++-14 -fopenmp -o project2 project2.cpp
### Run: ./project2 0.01 0.5 20 parallel 175 12

#### *This is assuming you are using a Mac and running the program on VScode*
