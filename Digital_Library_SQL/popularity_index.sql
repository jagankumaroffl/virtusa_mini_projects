SELECT 
    Category,
    COUNT(*) AS TotalBorrowed
FROM 
    Books b
JOIN 
    IssuedBooks i ON b.BookID = i.BookID
GROUP BY 
    Category
ORDER BY 
    TotalBorrowed DESC;
