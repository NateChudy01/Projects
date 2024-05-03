use red_foxes_database;

CREATE VIEW CustomerInfo AS
SELECT
	c.CustomerID,
	cs.SSN AS CustomerSSN,
    s.Fname AS CustomerFirstName,
    s.Lname AS CustomerLastName,
    c.Occupation,
    lo.LoanID AS LoanID,
    l.Terms AS LoanTerms,
    le.LeaseID AS LeaseID,
    lea.SignDate AS LeaseSignDate
FROM Customer c
JOIN Customer_SSN cs on c.CustomerID = cs.CustomerID
JOIN SSN s on cs.SSN = s.SSN
JOIN Loan_SSN lo on cs.SSN = lo.SSN
JOIN Loan l on lo.LoanID = l.LoanNumber
JOIN Lease_SSN le on cs.SSN = le.HeadSigneeSSN
JOIN Lease lea on le.LeaseID = lea.LeaseNumber;
    
SELECT * FROM CustomerInfo;

CREATE VIEW EmployeeInfo AS
SELECT
	e.EmployeeID,
    es.SSN AS EmployeeSSN,
    s.Fname AS EmployeeFirstName,
    s.Lname AS EmployeeLastName,
    e.BirthDate,
    e.Occupation,
    o.Salary
FROM Employee e
JOIN Employee_SSN es on e.EmployeeID = es.EmployeeID
JOIN SSN s on es.SSN = s.SSN
JOIN Occupation o on e.Occupation = o.Occupation;

SELECT * FROM EmployeeInfo;
