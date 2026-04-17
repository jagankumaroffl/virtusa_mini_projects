UPDATE Students s
JOIN (
    SELECT 
        StudentID,
        MAX(IssueDate) AS LastIssueDate
    FROM 
        IssuedBooks
    GROUP BY 
        StudentID, IssueDate
    HAVING 
        MAX(IssueDate) < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
) i ON s.StudentID = i.StudentID
SET 
    s.Name = 'Inactive'
WHERE 
    s.Name IS NOT NULL;
