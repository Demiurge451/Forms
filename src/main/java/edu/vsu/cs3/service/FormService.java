package edu.vsu.cs3.service;


import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.repository.FormRepository;
import edu.vsu.cs3.specification.FormSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Form> getListOfForms(PageRequest pageRequest) {
        return formRepository.findAll(pageRequest).getContent();
    }

    public List<Form> getListOfForms(String title, PageRequest pageRequest) {
        return formRepository.findAll(Specification.where(FormSpecification.hasTitle(title)), pageRequest).getContent();
    }

    public Form findById(int id) {
        return formRepository.getReferenceById(id);
    }

    @Transactional
    public Form save(Form form) {
        return formRepository.save(form);
    }

    @Transactional
    public void delete(int id) {
        formRepository.delete(findById(id));
    }

    @Transactional
    public Form update(int id, Form updatedForm) {
        Form form = formRepository.getReferenceById(id);
        form.setTitle(updatedForm.getTitle());
        form.setForeword(updatedForm.getForeword());
        form.setUser(updatedForm.getUser());
        return formRepository.save(form);
    }
}
