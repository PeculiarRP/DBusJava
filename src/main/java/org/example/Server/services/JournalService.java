package org.example.Server.services;

import org.example.Server.dao.JournalDAO;
import org.example.Server.dao.SubjectDAO;
import org.example.Server.models.Journal;
import org.example.Server.models.Subject;

import java.util.*;
import java.util.stream.Collectors;

public class JournalService {
    private final JournalDAO journalDAO = new JournalDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();
    public String DeleteAllGradeByStudentId(String id){
        journalDAO.deleteAllJournalFromStudent(UUID.fromString(id));
        return "Successful";
    }
    public String UpdateGradeByStudent(String data){
        // Test this code fragment !!!!
        List<Journal> finalRes = new ArrayList<>();
        var dataSplit = data.split("~");
        UUID studentId = UUID.fromString(dataSplit[0]);
        var subjects = dataSplit[1].split(",");
        List<Journal> stJournals = Optional.ofNullable(journalDAO.getJournalsFromStudent(studentId)).orElse(new ArrayList<>());
        Arrays.stream(subjects)
                .map(s -> s.split(":"))
                .forEach(s -> {
                    Journal tmp = null;
                    if (!stJournals.isEmpty()) {
                        tmp = stJournals.stream()
                                .filter(j -> j.getObject().getId().equals(UUID.fromString(s[0])))
                                .findFirst().orElse(null);
                    }
                    if (tmp == null){
                        tmp = new Journal(UUID.randomUUID(),
                                studentId,
                                subjectDAO.getSubjectById(UUID.fromString(s[0])),
                                Integer.parseInt(s[1]));
                    }
                    else tmp.setGrade(Integer.parseInt(s[1]));
                    finalRes.add(tmp);
                });
        journalDAO.addJournal(finalRes);
        return "Successful";
    }
    public JournalService(){}
}
