package com.example.moreclasses;

public class Subject {

    private String subjectCode;
    private String subjectName;

    public Subject(String subjectCode, String subjectName)
    {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public Subject(Subject subject)
    {
        this.subjectCode = subject.subjectCode;
        this.subjectName = subject.subjectName;
    }

    public boolean equals(Subject subject)
    {

        return (this.subjectName.equals(subject.subjectName)
                && this.subjectCode.equals(subject.subjectCode));

    }

    public String getSubjectCode()
    {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String sujectName)
    {
        this.subjectName = sujectName;
    }

    @Override
    public String toString()
    {

        return "Subject Name: " + subjectName + "\n" +
                "Subject Code: " + subjectCode;

    }
}
