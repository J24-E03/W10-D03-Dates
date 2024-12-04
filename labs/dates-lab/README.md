# **Library Management System with Date and Time - Lab Instructions**

## **Objective**  
Enhance your **Library Management System** by incorporating Java’s `java.time` package to handle borrow dates, due dates, and return dates. This lab will deepen your understanding of working with **dates and time**, while reinforcing concepts of **object-oriented programming** and **file handling**.

---

## **Instructions**

### **1. Update the `Book` Class**
- Add the following attributes to the `Book` class:
  - `borrowDate` (of type `LocalDate`)
  - `dueDate` (of type `LocalDate`)
  - `returnDate` (of type `LocalDate`)

---

### **2. Record Borrow Date and Due Date**
- **Enhance the `borrowBookByTitle(String title)` method:**
  - When a book is borrowed, set the `borrowDate` to the current date.
  - Calculate the `dueDate` to be 14 days from the borrow date.
  - Display the borrow date and due date to the user.

---

### **3. Check for Overdue Books**
- **Create a method `checkOverdueBooks()`:**
  - This method should loop through the `books` ArrayList.
  - Identify books where the `dueDate` is before the current date and `returnDate` is `null`.
  - Display all overdue books along with how many days they are overdue.

---

### **4. Record Return Date and Calculate Fine**
- **Enhance the `returnBook(String title)` method:**
  - Set the `returnDate` to the current date.
  - If the book is overdue, calculate a fine (e.g., $1 per day overdue).
  - Display the return status and fine, if applicable.

---

### **5. Extend Due Date**
- **Create a method `extendDueDate(String title, int additionalDays)`:**
  - Allow users to extend the due date for a borrowed book by a specified number of days.
  - Update the `dueDate` and display the new due date.

---

## **Additional Challenges**

### **6. Filter Books by Borrow Date Range**
- **Create a method `filterBooksByBorrowDate(LocalDate startDate, LocalDate endDate)`:**
  - Display all books borrowed within the specified date range.

---

### **7. Notify About Due Books**
- **Create a method `notifyDueBooks()`:**
  - This method should display books that are due within the next 3 days.

---

This lab will give you practical experience working with Java’s `java.time` package while enhancing your Library Management System. Good luck and happy coding! 🎉
