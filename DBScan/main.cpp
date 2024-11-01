#include <iostream>
#include <vector>
#include <fstream>
#include <thread>
#include <chrono> // For the timer
#include <mutex>

using namespace std;
mutex mtx;

/**
 * This structure is to create a point that is input through the data.txt file
 */
struct Point
{
    double x, y;
    int clusterID;
    Point(double x_val, double y_val) : x(x_val), y(y_val), clusterID(-1) {}
};

/**
 * double distance(const Point &p1, const Point &p2)
 *
 * This method is to calculate the distance between point (x1, y1) and point (x2, y2)
 * which will be used during the dbscan algorithm
 *
 * @param p1 is the first point (x1, y1)
 * @param p2 is the second point (x2, y2)
 * @return the return type of this method is a double and it is the distance between
 *         the two points
 */
double distance(const Point &p1, const Point &p2)
{
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}

/**
 * vector<int> regionQuery(const vector<Point> &points, const Point &point, double eps)
 *
 * This method takes in the vector of all of the points while also taking in one point
 * to check and see if it has any neighbors within the vector provided and returns a
 * vector of all of the neighbors of that one point
 *
 * @param points is the vector of all of the points to check for neighbors
 * @param point is a singular point that we are using to check for all of its neighbors
 *              in points
 * @param eps is a double that represents the radius surrounding the point provided to
 *            to be able to mark a neighbor
 * @return the return type of this method is a vector which includes all of the points
 *         that is a neighbor to the point provided in an epsilon eps
 */
vector<int> regionQuery(const vector<Point> &points, const Point &point, double eps)
{
    vector<int> neighbors;
    for (int i = 0; i < points.size(); i++)
    {
        if (distance(point, points[i]) <= eps)
        {
            neighbors.push_back(i);
        }
    }
    return neighbors;
}

/**
 * void expandCluster(vector<Point> &points, int pointIdx, vector<int> &neighbors, int clusterID, double eps, int minPts)
 *
 * This method takes in information from the region query and checks all of the neighbors
 * of the neighboring points from the inital point input are able to be included in the cluser
 * and uses the region query method again to do this
 *
 * @param points is a vector of all of the points possible
 * @param pointIdx is an int of the index of the current point that was checked
 * @param neighbors is a vector of all of the neighboring points of the inital point
 * @param clusterID is an int for the current cluster, and if there are any other points that
 *                  need to be included it gets marked with that cluster ID
 * @param eps is a double of the radius that the algorithm uses to check if there are any
 *            neighbors at or under that distance
 * @param minPts is an int that defines of a point is in the cluster by the minimum number of
 *               points within epsilon distance from the point
 * @return there is no return type for this method
 */
void expandCluster(vector<Point> &points, int pointIdx, vector<int> &neighbors, int clusterID, double eps, int minPts)
{
    points[pointIdx].clusterID = clusterID;

    for (int i = 0; i < neighbors.size(); i++)
    {
        int neighborIdx = neighbors[i];

        if (points[neighborIdx].clusterID == -1)
        {
            points[neighborIdx].clusterID = clusterID;

            vector<int> newNeighbors = regionQuery(points, points[neighborIdx], eps);
            if (newNeighbors.size() >= minPts)
            {
                neighbors.insert(neighbors.end(), newNeighbors.begin(), newNeighbors.end());
            }
        }

        if (points[neighborIdx].clusterID == 0)
        {
            points[neighborIdx].clusterID = clusterID;
        }
    }
}

/**
 * void threadedDBSCAN(vector<Point> &points, int startIdx, int endIdx, double eps, int minPts, int &clusterID)
 * 
 * Same as the DBScan algorithm however it uses threads to then split up the work
 * 
 * @param points is a vector of all of the points
 * @param startIdx is an int and its the starting index of the threads
 * @param endIdx is an int and is  the ending point of the threads
 * @param eps is a double of the radius that the algorithm uses to check if there are any
 *            neighbors at or under that distance
 * @param minPts is an int that defines of a point is in the cluster by the minimum number of
 *               points within epsilon distance from the point
 * @return there is no return type for this method
*/
void threadedDBSCAN(vector<Point> &points, int startIdx, int endIdx, double eps, int minPts, int &clusterID)
{
    for (int i = startIdx; i < endIdx; i++)
    {
        if (points[i].clusterID != -1)
            continue;
 
        vector<int> neighbors = regionQuery(points, points[i], eps);
        if (neighbors.size() < minPts)
        {
            mtx.lock();
            points[i].clusterID = 0;
            mtx.unlock();
        }
        else
        {
            mtx.lock();
            clusterID++;
            mtx.unlock();
 
            expandCluster(points, i, neighbors, clusterID, eps, minPts);
        }
    }
}

/**
 * void tDBscan(vector<Point> &points, double eps, int minPts)
 * 
 * This method is an intermediate step to the threaded dbscan algorithm, It spawns a bunch of
 * threads and then runs the dbscan algorithm with each of them
 * 
 * @param points is a vector of all of the points
 * @param eps is a double of the radius that the algorithm uses to check if there are any
 *            neighbors at or under that distance
 * @param minPts is an int that defines of a point is in the cluster by the minimum number of
 *               points within epsilon distance from the point
 * @return there is no return type for this method
 * 
*/
void tDBscan(vector<Point> &points, double eps, int minPts)
{
    int clusterID = 0;
    int numThreads = thread::hardware_concurrency();
    int pointsPerThread = points.size() / numThreads;
 
    vector<thread> threads;
    for (int i = 0; i < numThreads; i++)
    {
        int startIdx = i * pointsPerThread;
        int endIdx = (i == numThreads - 1) ? points.size() : (i + 1) * pointsPerThread;
        threads.emplace_back(thread(threadedDBSCAN, ref(points), startIdx, endIdx, eps, minPts, ref(clusterID)));
    }
 
    for (auto &t : threads)
    {
        t.join();
    }
}


/**
 * void dbscan(vector<Point> &points, double eps, int minPts)
 *
 * This method operates at a higher level than the previous two methods, it checks if the
 * current point has been visited and if it hasnt been it will run a region query against
 * it finding its neighbors. Once the neighbors are found, it then runs the expand cluster
 * to find the neighbors of those neighbors to then find the cluster
 *
 * @param points is a vector of all of the points
 * @param eps is a double of the radius that the algorithm uses to check if there are any
 *            neighbors at or under that distance
 * @param minPts is an int that defines of a point is in the cluster by the minimum number of
 *               points within epsilon distance from the point
 * @return there is no return type for this method
 */

void dbscan(vector<Point> &points, double eps, int minPts)
{
    int clusterID = 0;

    for (int i = 0; i < points.size(); i++)
    {
        if (points[i].clusterID != -1)
        {
            continue;
        }
        vector<int> neighbors = regionQuery(points, points[i], eps);

        if (neighbors.size() < minPts)
        {
            points[i].clusterID = 0;
        }
        else
        {
            clusterID++;
            expandCluster(points, i, neighbors, clusterID, eps, minPts);
        }
    }
}

/**
 * bool compareByClusterID(const Point &a, const Point &b)
 *
 * This method allows for the points vector to be sorted by clusterID once it gets output to
 * output.txt
 *
 * @param a is a Point object that we want to compare to another Point by clusterID
 * @param b is a Point object that we are comparing to Point a
 * @return the return type is a boolean allowing for sorting
 */
bool compareByClusterID(const Point &a, const Point &b)
{
    return a.clusterID < b.clusterID;
}

/**
 * void exportPoints(vector<Point> &points)
 *
 * This method outputs all of the points provided into the file "output.txt"
 *
 * @param points is a vector of all of the Points that are going to be output
 * @return there is no return type
 */
void exportPoints(vector<Point> &points)
{
    ofstream outFile;
    outFile.open("output.txt");

    if (outFile.is_open())
    {
        sort(points.begin(), points.end(), compareByClusterID);
        for (const auto &point : points)
        {
            outFile << point.x << " " << point.y << " " << point.clusterID << endl;
        }
        outFile.close();
    }
    else
    {
        cout << "Failed to open the file.\n";
    }
}

/**
 * void randInput(int numPoints, int maxValue, double eps, int minPts)
 *
 * This method is created strictly for testing the dbscan algorithm by generating 
 * random list of points to try the algorithm
 * 
 * @param numPoints is an int and is the number of points that will be used to graph
 * @param maxValue is an int and it the maximum value that any X or Y value can have
 * @param eps is a double and is the desired epsilon value for the algorithm
 * @param minPts is an int and is the minimum points that are required to call points
 *               a cluster
 * @return there is no return value for this method
 */
void randInput(int numPoints, int maxValue, double eps, int minPts)
{
    ofstream outFile;
    outFile.open("data.txt");
    srand(static_cast<unsigned>(time(nullptr)));

    outFile << eps << " " << minPts << endl;

    if (outFile.is_open())
    {
        for (int i = 0; i < numPoints; i++)
        {
            double x = static_cast<double>(rand()) / RAND_MAX * maxValue;
            double y = static_cast<double>(rand()) / RAND_MAX * maxValue;
            outFile << x << " " << y << endl;
        }
    }
    else
    {
        cout << "Failed to open the file.\n";
    }
}

/**
 * int main()
 *
 * This is the main method that reads in the data, creates the points from the text file,
 * runs the dbscan algorithm, and then outputs the points to the output.txt file
 */
int main()
{
    bool test = false;
    bool parallel = true;
    auto start = chrono::high_resolution_clock::now(); // Start timer

    if (test)
    {
        randInput(500, 10, .5, 2);
    }

    double eps = 1.5;
    int minPts = 2;

    string input;
    ifstream MyReadFile("data.txt");
    vector<Point> points;

    int count = 0;
    while (getline(MyReadFile, input))
    {
        if (count == 0)
        {
            int num = input.find(" ");
            eps = stod(input.substr(0, num));
            minPts = stoi(input.substr(num + 1, input.length()));
            count++;
        }
        else
        {
            int num = input.find(" ");
            double x = stod(input.substr(0, num));
            double y = stod(input.substr(num + 1, input.length()));
            points.insert(points.begin(), Point(x, y));
            count++;
        }
    }
    if(parallel){
        tDBscan(points, eps, minPts);
    }else{
        dbscan(points, eps, minPts);
    }
    exportPoints(points);

    auto end = chrono::high_resolution_clock::now(); // End timer
    chrono::duration<double> duration = end - start;
    cout << "Execution time: " << duration.count() << " seconds" << endl;

    // This section below is here to graph the points from the DBSCAN algorithm
    // to show the results, can be commented if you do not have gnuplot

    
    /*
    ofstream gnuplotScript("plot_dynamic_ids.gp");

    gnuplotScript << "stats \"output.txt\" using 3 nooutput\n";
    gnuplotScript << "set palette defined (0 '#0000FF', 0.25 '#00FF00', 0.5 '#FFFF00', 0.75 '#FFA500', 1 '#FF0000')\n";
    gnuplotScript << "set colorbox\n";
    gnuplotScript << "set title \"Points Highlighted by Dynamic IDs\"\n";
    gnuplotScript << "set xlabel \"X-axis\"\n";
    gnuplotScript << "set ylabel \"Y-axis\"\n";
    gnuplotScript << "set grid\n";
    gnuplotScript << "plot 'output.txt' using 1:2:(($3 == 0) ? 0 : $3) notitle with points pt 7 lc variable\n";
    gnuplotScript << "pause -1\n";

    gnuplotScript.close();

    system("gnuplot plot_dynamic_ids.gp");
    */

    return 0;
}
