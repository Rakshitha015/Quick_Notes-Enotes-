package com.example.enotes_pro.service;


import java.util.List;

import com.example.enotes_pro.entity.Notes;
import com.example.enotes_pro.entity.User;

public interface NotesService {



    public Notes savaNotes(Notes notes);

    public Notes getNotesById(int id);

    public List<Notes> getNotesByUser(User user);

    public Notes updateNotes(Notes notes);

    public boolean deleteNotes(int id);
}
