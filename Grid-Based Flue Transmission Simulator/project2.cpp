#include <omp.h>
#include <iostream>
#include <fstream>

// For timer, comment this out upon submission
#include <chrono>

using namespace std;

/**
 * When running the program with the image generator, the reccomended running command is:
 * ./project2 0.01 0.5 20 parallel 175 12
 *
 * Best SIZE is 200
 *
 * Check the output in the simulation.ppm file and output.txt file
 */

const int SIZE = 200;
unsigned int seed = 12345;
const int PIXEL_SIZE = 2;
const int IMAGE_SIZE = SIZE * PIXEL_SIZE;

unsigned int randGenerator()
{
    const unsigned long long a = 1664525;
    const unsigned long long c = 1013904223;
    const unsigned long long m = 4294967296ULL;

    seed = (static_cast<unsigned long long>(a) * seed + c) % m;
    return (seed % 100) + 1;
}

void exportToImage(int **currentDay, int scalePercent)
{
    ofstream outFile;
    string filename = "simulation.ppm";
    outFile.open(filename, ios::binary);

    // Calculate new dimensions based on scale percentage
    int scaledPixelSize = (PIXEL_SIZE * scalePercent) / 100;
    if (scaledPixelSize < 1)
        scaledPixelSize = 1; // Ensure minimum size of 1
    int scaledImageSize = SIZE * scaledPixelSize;

    if (outFile.is_open())
    {
        // PPM header with scaled dimensions
        outFile << "P3\n"
                << scaledImageSize << " " << scaledImageSize << "\n255\n";

        // Write pixel data
        for (int i = 0; i < SIZE; i++)
        {
            // Each cell is scaledPixelSize pixels tall
            for (int pixelRow = 0; pixelRow < scaledPixelSize; pixelRow++)
            {
                for (int j = 0; j < SIZE; j++)
                {
                    // Each cell is scaledPixelSize pixels wide
                    for (int pixelCol = 0; pixelCol < scaledPixelSize; pixelCol++)
                    {
                        if (currentDay[i][j] >= 1)
                        {
                            outFile << "255 0 0 ";
                        }
                        else
                        {
                            outFile << "0 0 255 ";
                        }
                    }
                }
                outFile << "\n";
            }
        }
        outFile.close();
    }
    else
    {
        cout << "Failed to create image file.\n";
    }
}

void exportToDayTXT(int **currentDay)
{
    ofstream outFile;
    outFile.open("output.txt");
    if (outFile.is_open())
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (currentDay[i][j] >= 1)
                {
                    outFile << "1 ";
                }
                else
                {
                    outFile << "0 ";
                }
            }
            outFile << "\n";
        }
        outFile.close();
    }
    else
    {
        cout << "Failed to open the file.\n";
    }
}

void decrementSickness(int **currentDay, int omega) // Not used
{
    for (int row = 0; row < SIZE; row++)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (currentDay[row][col] > 0)
            {
                currentDay[row][col] -= 1;
            }
        }
    }
}

void initializeFirstDay(int **currentDay, double alpha, int omega)
{
    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            int percent = (rand() % 100) + 1;
            if (percent <= (alpha * 100.0))
            {
                currentDay[i][j] = omega;
            }
            else
            {
                currentDay[i][j] = 0;
            }
        }
    }
}

string getUserInput(string prompt)
{
    string returnable;
    cout << prompt;
    cin >> returnable;
    return returnable;
}

void generateNextDay(int **currentDay, double beta, int omega)
{
    int **tempDay = new int *[SIZE];
    for (int i = 0; i < SIZE; i++)
    {
        tempDay[i] = new int[SIZE]{0}; // Initialize to zero
    }

    for (int row = 0; row < SIZE; row++)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (currentDay[row][col] > 0 && currentDay[row][col] <= omega)
            {
                if (col + 1 < SIZE && currentDay[row][col + 1] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row][col + 1] = omega;
                }
                if (col - 1 >= 0 && currentDay[row][col - 1] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row][col - 1] = omega;
                }
                if (row + 1 < SIZE && currentDay[row + 1][col] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row + 1][col] = omega;
                }
                if (row - 1 >= 0 && currentDay[row - 1][col] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row - 1][col] = omega;
                }
            }
        }
    }

    for (int row = 0; row < SIZE; row++)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (tempDay[row][col] > 0)
            {
                currentDay[row][col] = tempDay[row][col];
            }
            else if (currentDay[row][col] > 0)
            {
                currentDay[row][col] -= 1;
            }
        }
    }

    // Free memory for tempDay
    for (int i = 0; i < SIZE; i++)
    {
        delete[] tempDay[i];
    }
    delete[] tempDay;
}

void generateNextDayOMP(int **currentDay, double beta, int omega, int numThreads)
{
    int **tempDay = new int *[SIZE];
    for (int i = 0; i < SIZE; i++)
    {
        tempDay[i] = new int[SIZE]{0}; // Initialize to zero
    }

    omp_set_num_threads(numThreads);

    #pragma omp parallel for
    for (int row = 0; row < SIZE; row++)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (currentDay[row][col] > 0 && currentDay[row][col] <= omega)
            {
                if (col + 1 < SIZE && currentDay[row][col + 1] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row][col + 1] = omega;
                }
                if (col - 1 >= 0 && currentDay[row][col - 1] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row][col - 1] = omega;
                }
                if (row + 1 < SIZE && currentDay[row + 1][col] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row + 1][col] = omega;
                }
                if (row - 1 >= 0 && currentDay[row - 1][col] == 0 && (rand() % 100) < (beta * 100))
                {
                    tempDay[row - 1][col] = omega;
                }
            }
        }
    }

    #pragma omp parallel for collapse(2)
    for (int row = 0; row < SIZE; row++)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (tempDay[row][col] > 0)
            {
                currentDay[row][col] = tempDay[row][col];
            }
            else
            {
                if (currentDay[row][col] > 0)
                {
                    currentDay[row][col] -= 1;
                }
            }
        }
    }

    for (int i = 0; i < SIZE; i++)
    {
        delete[] tempDay[i];
    }
    delete[] tempDay;
}

int main(int argc, char *argv[])
{
    double alpha = 0.5;
    double beta = 0.5;
    int omega = 7;
    int scalePercent = 100;
    string runType = "serial";
    int numThreads = 1;

    if (argc > 1)
    {
        // Where 0<α<1, is initial ratio representing folks that are sick on day 0
        alpha = atof(argv[1]);

        // Where 0<β<1 represents likelihood of getting infected by a nearby sick person
        // or similarly likelihood of infecting one at the end of a day
        beta = atof(argv[2]);

        // Where Ω is a positive integer represents how many days a person stays sick
        // until they recover
        omega = atoi(argv[3]);

        // This will specify what the run type is, either parallel of serial
        runType = argv[4];

        // This will specify the zoom of the .ppm file generated
        scalePercent = atoi(argv[5]);

        // This line is for testing purposes to alter the number of threads used
        numThreads = atoi(argv[6]);
    }

    if (scalePercent <= 0)
    {
        scalePercent = 100;
    }

    // Dynamically allocate currentDay array
    int **currentDay = new int *[SIZE];
    for (int i = 0; i < SIZE; i++)
    {
        currentDay[i] = new int[SIZE];
    }

    // Initialize the first day
    auto start = chrono::high_resolution_clock::now(); // Timer

    initializeFirstDay(currentDay, alpha, omega);

    auto end = chrono::high_resolution_clock::now();
    chrono::duration<double> duration = end - start;
    cout << "********** DAY 1; TIME: " << duration.count() << " SECONDS **********" << endl;

    exportToDayTXT(currentDay);
    exportToImage(currentDay, scalePercent);

    int dayNumber = 1;
    string input = "";
    while (true)
    {
        input = getUserInput("N: Next Day\nX: End\nInput: ");
        if (input == "N" || input == "n")
        {
            dayNumber++;

            start = chrono::high_resolution_clock::now();

            if (runType == "parallel")
            {
                generateNextDayOMP(currentDay, beta, omega, numThreads);
            }
            else
            {
                generateNextDay(currentDay, beta, omega);
            }
            
            end = chrono::high_resolution_clock::now();
            duration = end - start;
            cout << "********** DAY " << dayNumber << "; TIME: " << duration.count() << " SECONDS **********" << endl;

            exportToDayTXT(currentDay);

            if (SIZE < 250)
            {
                exportToImage(currentDay, scalePercent);
            }
        }
        else if (input == "X" || input == "x")
        {
            break;
        }
        else
        {
            cout << "Incorrect Input" << endl;
        }
    }

    // Free memory for currentDay
    for (int i = 0; i < SIZE; i++)
    {
        delete[] currentDay[i];
    }
    delete[] currentDay;
}
