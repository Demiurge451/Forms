package edu.vsu.cs3.service;


import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FormService {
    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getListOfForms() {
        return formRepository.findAll();
    }

    public Form findById(int id) {
        return formRepository.getReferenceById(id);
    }

    @Transactional
    public void save(Form form) {
        formRepository.save(form);
    }

    @Transactional
    public void delete(int id) {
        formRepository.delete(findById(id));
    }
}
