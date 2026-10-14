package com.example.fundamentals.model;



import java.time.LocalDate;

public class Book {
    private int id;
    private int studentId;
    private String bookName;
    private LocalDate createdAt;

    public Book(int studentId, String bookName) {
        setBookName(bookName);
        setStudentId(studentId);
        setCreatedAt(LocalDate.now());
    }

    public Book(int id, int studentId, String bookName, LocalDate createdAt) throws Exception{
       setId(id);
       setBookName(bookName);
       setStudentId(studentId);
       setCreatedAt(createdAt);

    }
    public Book(String text) {
        String[] split = text.split(",");

        setId(Integer.parseInt(split[0]));
        setStudentId(Integer.parseInt(split[1]));
        setBookName(split[2]);
        setCreatedAt(LocalDate.parse(split[3]));
    }
    @Override
    public String toString() {
        return id + "," + studentId + "," + bookName + "," + createdAt;
    }



    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) throws InvalidBookNameException {
        if(bookName.isEmpty() ||  bookName == null){
            throw new InvalidBookNameException();
        }

        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) throws InvalidIdException {

        if(id < 0 || id > 100000){
            throw new InvalidIdException();
        }
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId)throws InvalidStudentIdException {
        if(studentId < 0 || studentId > 100000){
            throw new InvalidStudentIdException();
        }
        this.studentId = studentId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt)throws InvalidCreatedAtException {
        if (createdAt == null || createdAt.isAfter(LocalDate.now())) {
            throw new InvalidCreatedAtException();
        }
        this.createdAt = createdAt;
    }

}



