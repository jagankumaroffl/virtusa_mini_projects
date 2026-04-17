SELECT 
    s.Name AS StudentName,
    b.Title AS BookTitle,
    i.IssueDate,
    i.ReturnDate
FROM 
    Students s
JOIN 
    IssuedBooks i ON s.StudentID = i.StudentID
JOIN 
    Books b ON i.BookID = b.BookID
WHERE 
    i.ReturnDate IS NULL AND (SYSDATE() - i.IssueDate) > INTERVAL 14 DAY;
