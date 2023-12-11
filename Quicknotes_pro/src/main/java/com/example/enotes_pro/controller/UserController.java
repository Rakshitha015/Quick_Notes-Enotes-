package com.example.enotes_pro.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.enotes_pro.entity.Notes;
import com.example.enotes_pro.entity.User;
import com.example.enotes_pro.repository.UserRepository;
import com.example.enotes_pro.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NotesService notesService;

    @ModelAttribute
    public User getUser(Principal p,Model m)
    {
        String email = p.getName();
        User user = userRepo.findByEmail(email); 
        m.addAttribute("user", user);
        return user;
    }


    @GetMapping("/addNotes")
    public String addNotes(){
        return "add_notes";
    }

    @GetMapping("/viewNotes")
    public String viewNotes(Model m,Principal p){
        User user = getUser(p, m);
        List<Notes> allNotes = notesService.getNotesByUser(user);
        m.addAttribute("notesList", allNotes);
        return "view_notes";
    }

    @GetMapping("/editNotes/{id}")
    public String editNotes(@PathVariable int id,Model m){
        Notes notes = notesService.getNotesById(id);
        m.addAttribute("editNotes", notes);
        return "edit_notes";
    }

    @GetMapping("deleteNotes/{id}")
    public String deleteNotes(@PathVariable int id,HttpSession session)
    {
        boolean notes = notesService.deleteNotes(id);
        if(notes)
        {
            session.setAttribute("msg", "delete success");
            
        }
        else
        {
            session.setAttribute("msg", "delete success");
               
        }
        return "redirect:/user/viewNotes";
        
    }

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m){

        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p, m));

        Notes saveNotes = notesService.savaNotes(notes);
        if(saveNotes != null)
        {
            session.setAttribute("msg", "Notes Saved Successfully");
        }
        else
        {
            session.setAttribute("msg", "something went wrong on server");
        }

        return "redirect:/user/addNotes";
}

    @PostMapping("/updateNotes")
    public String updateNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m){

        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p, m));

        Notes saveNotes = notesService.savaNotes(notes);
        if(saveNotes != null)
        {
            session.setAttribute("msg", "Notes updated Successfully");
        }
        else
        {
            session.setAttribute("msg", "something went wrong on server");
        }

        return "redirect:/user/viewNotes";
}
}
