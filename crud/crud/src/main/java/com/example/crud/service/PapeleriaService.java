package com.example.crud.service;

import model.Papeleria;
import com.example.crud.model.custom.CountCategoria;
import model.custom.DescriptionAmount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PapeleriaService {

    @Autowired
    private PapeleriaRepository papeleriaRepository;

    public List<Papeleria> getAll(){
        return papeleriaRepository.getAll();
    }

    public Optional<Papeleria> getPapeleria(int id){
        return papeleriaRepository.getPapeleria(id);
    }

    public Papeleria save(Papeleria p){
        if(p.getId()==null){
            return papeleriaRepository.save(p);
        }else{
            Optional<Papeleria> paux=papeleriaRepository.getPapeleria(p.getId());
            if(paux.isEmpty()){
                return papeleriaRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Papeleria update(Papeleria c){
        if(c.getId()!=null){
            Optional<Papeleria>g=papeleriaRepository.getPapeleria(c.getId());
            if(!g.isEmpty()){
                if(c.getName()!=null){
                    g.get().setName(c.getName());
                }
                if(c.getDescription()!=null){
                    g.get().setDescription(c.getDescription());
                }
                if(c.getPrice()!=null){
                    g.get().setPrice(c.getPrice());
                }
                return papeleriaRepository.save(g.get());
            }
        }
        return c;

    }

    public boolean deleteCategory(int id){
        Optional<Papeleria> c=getPapeleria(id);
        if(!c.isEmpty()){
            papeleriaRepository.delete(c.get());
            return true;
        }
        return false;

    }

    public List<CountCategoria> getTopCategorias(){
        return papeleriaRepository.getTopCategorias();
    }

    public DescriptionAmount getStatusReport(){
        List<Papeleria> completed=papeleriaRepository.getPapeleriasByDescription("completed");
        List<Papeleria> cancelled=papeleriaRepository.getPapeleriasByDescription("cancelled");

        DescriptionAmount descAmt=new DescriptionAmount(completed.size(),cancelled.size());
        return descAmt;
    }
    public List<Papeleria> getPapeleriaPeriod(String d1, String d2){

        // yyyy-MM-dd
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try {
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        }catch (ParseException e) {
        }
        if(dateOne.before(dateTwo)){
            return papeleriaRepository.getPapeleriaPeriod(dateOne,dateTwo);
        }else{
            return new ArrayList<>();
        }
    }

    private static class PapeleriaRepository {

        public PapeleriaRepository() {
        }

        private List<Papeleria> getAll() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Optional<Papeleria> getPapeleria(int id) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Papeleria save(Papeleria p) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private List<CountCategoria> getTopCategorias() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private List<Papeleria> getPapeleriasByDescription(String completed) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void delete(Papeleria get) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private List<Papeleria> getPapeleriaPeriod(Date dateOne, Date dateTwo) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
