#include <mpi.h>
#include <iostream>
#include <fstream>

#define MAX_NODES 16
#define TABLE_SIZE 10000
#define MAX_STR_LEN 256

using namespace std;

struct String {
    char data[MAX_STR_LEN];
    
    void set(const char* str) {
        int i = 0;
        while (str[i] && i < MAX_STR_LEN - 1) {
            data[i] = str[i];
            i++;
        }
        data[i] = '\0';
    }
    
    bool equals(const char* str) const {
        int i = 0;
        while (data[i] && str[i] && i < MAX_STR_LEN) {
            if (data[i] != str[i]) return false;
            i++;
        }
        return data[i] == str[i];
    }
};

struct Record {
    String text1;  // First text attribute
    String text2;  // Second text attribute
    int number;    // Numeric attribute
    bool used;     // Flag to indicate if slot is used
};

struct NodeStorage {
    Record records[TABLE_SIZE];
    int count;
    int nodeNum;
    
    NodeStorage() : count(0) {
        for (int i = 0; i < TABLE_SIZE; i++) {
            records[i].used = false;
        }
    }

    void setNodeNum(int num){
        nodeNum = num;
    }

    int getCount(){
        return count;
    }
    
    int calculateTargetNode(const char* text1, int numNodes) {
        unsigned long long hash = 14695981039346656037U;
        int i = 0;

        // FNV-1a hash algorithm
        while (text1[i]) {
            hash ^= text1[i];
            hash *= 1099511628211U;
            i++;
        }

        int targetNode = (hash % numNodes);
        if (targetNode == 0) {
            targetNode = numNodes - 1;
        }
        return targetNode;
    }

    bool insert(const char* text1, const char* text2, int num) {
        if (count >= TABLE_SIZE) return false;
        
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (!records[i].used) {
                records[i].text1.set(text1);
                records[i].text2.set(text2);
                records[i].number = num;
                records[i].used = true;
                count++;
                return true;
            }
        }
        return false;
    }

    void printAll() const {
        std::cout << "NodeStorage (" << nodeNum << ") Contents (" << count << " records):" << std::endl;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (records[i].used) {
                std::cout << "Record: ";
                std::cout << "Name = " << records[i].text1.data << ", ";
                std::cout << "Genre = " << records[i].text2.data << ", ";
                std::cout << "Year = " << records[i].number << std::endl;
            }
        }
    }

    void print(int node){
        if (node == nodeNum) {
            std::cout << "NodeStorage (" << node << ") Contents (" << count << " records):" << std::endl;
            for (int i = 0; i < TABLE_SIZE; i++) {
                if (records[i].used) {
                    std::cout << "Record: ";
                    std::cout << "Name = " << records[i].text1.data << ", ";
                    std::cout << "Genre = " << records[i].text2.data << ", ";
                    std::cout << "Year = " << records[i].number << std::endl;
                }
            }
        }
    }

    void printGenre(String genre) {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (records[i].used && records[i].text2.equals(genre.data)) {
                std::cout << "Record: ";
                std::cout << "Name = " << records[i].text1.data << ", ";
                std::cout << "Genre = " << records[i].text2.data << ", ";
                std::cout << "Year = " << records[i].number << std::endl;
            }
        }
    }

    void printYears(int year){
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (records[i].used && records[i].number == year) {
                std::cout << "Record: ";
                std::cout << "Name = " << records[i].text1.data << ", ";
                std::cout << "Genre = " << records[i].text2.data << ", ";
                std::cout << "Year = " << records[i].number << std::endl;
            }
        }
    }

    void printGenreAndYear(String genre, int year){
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (records[i].used && records[i].text2.equals(genre.data) && records[i].number == year) {
                std::cout << "Record: ";
                std::cout << "Name = " << records[i].text1.data << ", ";
                std::cout << "Genre = " << records[i].text2.data << ", ";
                std::cout << "Year = " << records[i].number << std::endl;
            }
        }
    }
};

enum QueryType {
    INSERT,
    SELECT,
    EXIT,
    UNKNOWN
};

class Query {
public:
    QueryType type;
    String text1;
    String text2;
    int number;
    bool hasText1Condition;
    bool hasText2Condition;
    bool hasNumberCondition;
    
    static Query parse(const char* queryStr);
};

Query Query::parse(const char* queryStr) {
    Query q;
    q.type = UNKNOWN;
    q.hasText1Condition = false;
    q.hasText2Condition = false;
    q.hasNumberCondition = false;
    
    // Basic SQL parsing
    if (strncmp(queryStr, "INSERT", 6) == 0 || strncmp(queryStr, "insert", 6) == 0) {
        q.type = INSERT;
        const char* rest = queryStr + 6;
        
        // Skip whitespace
        while (*rest == ' ' || *rest == '\t') rest++;
        
        // Extract text1, text2, and number from the query
        int num = 0;
        sscanf(rest, "%s %s %d", q.text1.data, q.text2.data, &num);
        q.number = num;

        q.hasText1Condition = true;
        q.hasText2Condition = true;
        q.hasNumberCondition = true;
    }
    else if (strncmp(queryStr, "SELECT", 6) == 0 || strncmp(queryStr, "select", 6) == 0) {
        q.type = SELECT;

        const char* rest = queryStr + 6;

        // Skip whitespace
        while (*rest == ' ' || *rest == '\t') rest++;

        // Initialize variables
        int num = 0;
        char tempStr[50] = ""; // Temporary buffer for string parsing

        // Check if the input starts with a number
        if (sscanf(rest, "%d", &num) == 1) {
            // It's a number
            q.number = num;
            q.hasNumberCondition = true;
        } 
        // If not a number, check for a string
        else if (sscanf(rest, "%49s", tempStr) == 1) { // Parse the first string
            // It's a string
            strncpy(q.text2.data, tempStr, sizeof(q.text2.data) - 1);
            q.text2.data[sizeof(q.text2.data) - 1] = '\0';
            q.hasText2Condition = true;

            // Move pointer past the string
            rest += strlen(tempStr);
            while (*rest == ' ' || *rest == '\t') rest++;

            // Check if there's a second input that is a number
            if (sscanf(rest, "%d", &num) == 1) {
                q.number = num;
                q.hasNumberCondition = true;
            }
        }
    
    } else if (strncmp(queryStr, "EXIT", 4) == 0 || strncmp(queryStr, "exit", 4) == 0){
        q.type = EXIT;
    }
    
    return q;
}

static int calculateTargetNode(const char* text1, const char* text2, int year, int numNodes) {
    // Find the range of years in your dataset
    const int MIN_YEAR = 1970;  // Adjust based on your actual data
    const int MAX_YEAR = 2023;
    const int YEAR_SPAN = MAX_YEAR - MIN_YEAR + 1;
    
    // Calculate range size per node
    int rangePerNode = YEAR_SPAN / numNodes;
    if (rangePerNode == 0) rangePerNode = 1;  // Ensure we don't divide by zero
    
    // Calculate which node should handle this year
    int targetNode = (year - MIN_YEAR) / rangePerNode;
    
    // Ensure we stay within bounds
    if (targetNode >= numNodes) targetNode = numNodes - 1;
    if (targetNode < 0) targetNode = 0;
    
    return targetNode;
}

void readAndProcessFile(const char* filename, int world_size) {
    ifstream inputFile(filename);

    // Check if the file was opened successfully
    if (!inputFile.is_open()) {
        cout << "Error: Could not open the file!" << endl;
        return;
    }

    string line;
    
    // Read the file line by line until the end
    while (getline(inputFile, line)) {

        // Variables to store the parts of the line
        string title, genre, year;
        size_t startPos = 0;
        size_t commaPos;

        // Extract the title (first part) - Name
        commaPos = line.find(',', startPos);
        if (commaPos != string::npos) {
            title = line.substr(startPos, commaPos - startPos);
            startPos = commaPos + 1;
        }

        // Extract the genre (second part)
        commaPos = line.find(',', startPos);
        if (commaPos != string::npos) {
            genre = line.substr(startPos, commaPos - startPos);
            startPos = commaPos + 1;
        }

        // Extract the year (third part)
        year = line.substr(startPos);

        // Generate the query string for INSERT
        string queryStr = "INSERT " + title + " " + genre + " " + year;
        Query insertQuery = Query::parse(queryStr.c_str());

        // Determine the target node based on the title
        int targetNode = calculateTargetNode(insertQuery.text1.data, insertQuery.text2.data, insertQuery.number, world_size);

        // Send the query to the target node via MPI
        MPI_Send(&insertQuery, sizeof(Query), MPI_BYTE, targetNode, 0, MPI_COMM_WORLD);
    }

    inputFile.close();
}

int main(){
    MPI_Init(NULL, NULL);
    
    int world_size, world_rank;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
    
    if (world_size > MAX_NODES) {
        if (world_rank == 0) {
            std::cout << "Error: Maximum supported nodes is " << MAX_NODES << std::endl;
        }
        MPI_Finalize();
        return 1;
    }

    NodeStorage storage;
    storage.setNodeNum(world_rank);

    if (world_rank == 0) {

        readAndProcessFile("movies.txt", world_size);

        char queryBuffer[MAX_STR_LEN];

        while (true) {
            cout << endl;
            cout << "▼▼ SQL Below (type 'exit' to quit) ▼▼" << endl;
            cin.getline(queryBuffer, MAX_STR_LEN);
            cout << endl;

            Query query = Query::parse(queryBuffer);

            if (query.type == EXIT || query.type == UNKNOWN) {
                // Send the exit query to all worker nodes
                for (int i = 1; i < world_size; i++) {
                    MPI_Send(&query, sizeof(Query), MPI_BYTE, i, 0, MPI_COMM_WORLD);
                }
                break;  // Exit the loop and terminate the root node
            } 
            
            if (query.type == INSERT) {
                int targetNode = calculateTargetNode(query.text1.data, query.text2.data, query.number, world_size);
                cout << "Target Node: " << targetNode << endl;
                MPI_Send(&query, sizeof(Query), MPI_BYTE, targetNode, 0, MPI_COMM_WORLD);
                std::cout << "Record inserted to node " << targetNode << std::endl;
            } else if (query.type == SELECT) {
                for (int i = 1; i < world_size; i++) {
                    MPI_Send(&query, sizeof(Query), MPI_BYTE, i, 0, MPI_COMM_WORLD);
                }

                // Wait for all worker nodes to acknowledge
                for (int i = 1; i < world_size; i++) {
                    int ack;
                    MPI_Recv(&ack, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
                }
            }
        }

        // Sending a terminate signal to worker nodes after root node exits
        Query terminateQuery;
        terminateQuery.type = UNKNOWN;
        for (int i = 1; i < world_size; i++) {
            MPI_Send(&terminateQuery, sizeof(Query), MPI_BYTE, i, 0, MPI_COMM_WORLD);
        }

        // Wait for worker nodes to finish
        for (int i = 1; i < world_size; i++) {
            int ack;
            MPI_Recv(&ack, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        }

    } else {  // Worker Nodes
        while (true) {
            Query query;
            MPI_Status status;

            MPI_Recv(&query, sizeof(Query), MPI_BYTE, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);

            if (query.type == EXIT || query.type == UNKNOWN) {
                // Worker node breaks out of the loop if the exit signal is received
                break;
            } else if (query.type == INSERT) {
                storage.insert(query.text1.data, query.text2.data, query.number);
            } else if (query.type == SELECT) {
                if (query.hasNumberCondition && query.hasText2Condition) {
                    // Query is asking for both genre and year
                    storage.printGenreAndYear(query.text2, query.number);
                } else if (query.hasText2Condition) {
                    if(strcmp(query.text2.data, "*") == 0){
                        storage.printAll();
                    }else{
                        storage.printGenre(query.text2);
                    }
                } else if (query.hasNumberCondition) {
                    storage.printYears(query.number);
                }
                int ack = 1;
                MPI_Send(&ack, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
            }
        }

        // Notify root node that this worker is finished
        int ack = 1;
        MPI_Send(&ack, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

    MPI_Finalize();
    return 0;
}