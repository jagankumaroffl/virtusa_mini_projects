DELETE FROM Students WHERE StudentID IN (
    SELECT s.StudentID
    FROM Students s
    JOIN IssuedBooks i ON s.StudentID = i.StudentID
    GROUP BY s.StudentID, i.IssueDate
    HAVING MAX(i.IssueDate) < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
);
