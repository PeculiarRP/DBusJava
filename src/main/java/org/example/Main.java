package org.example;
import org.example.Server.services.StudentService;


public class Main {
    public static void main(String[] args) {

        var server = new StudentService();
        var a = server.getStudentsByParam(":Pусский = 5", true);
        System.out.println(a);
//        var server = new JournalService();
//        var a = server.UpdateGradeByStudent("d55262b4-69c7-4f2d-9cd7-bb7eebcf6e36~92df887a-6b1a-4088-9fbc-38994ef27c0f:5,eacba2cf-e9fa-46bd-beee-d2a8a93f3f7c:1");
        //        server.getStudentByAllParam("D", "d", "1a");

//        var ser = new UserDAO();
//        ser.getUserByLogin("admin");

    }
}